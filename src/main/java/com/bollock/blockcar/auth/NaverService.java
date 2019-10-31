package com.bollock.blockcar.auth;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Service;


@Service
public class NaverService {
	public Boolean requestSms(SmsRequest smsRequest) {
		String hostNameUrl = "https://sens.apigw.ntruss.com"; // 호스트 URL
		String requestUrl = "/sms/v2/services/"; // 요청 URL
		String requestUrlType = "/messages"; // 요청 URL
		String accessKey = "bUqjYBq3yiXHPmXJk6rr"; // 네이버 클라우드 플랫폼 회원에게 발급되는 개인 인증키
		String secretKey = "UxCwPzXA9z37pAxQZyU5tG8WW9cYURy5P0hQUwjq"; // 2차 인증을 위해 서비스마다 할당되는 service secret
		String serviceId = "ncp:sms:kr:256997335938:blockcar"; // 프로젝트에 할당된 SMS 서비스 ID
		String method = "POST"; // 요청 method
		String timestamp = Long.toString(System.currentTimeMillis()); // current timestamp (epoch)
		requestUrl += serviceId + requestUrlType;
		String apiUrl = hostNameUrl + requestUrl;

		// JSON 을 활용한 body data 생성

		JSONObject bodyJson = new JSONObject();
		JSONObject toJson = new JSONObject();
		JSONArray toArr = new JSONArray();

		try {
			toJson.put("subject", ""); // 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
			toJson.put("content", ""); // 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
			toJson.put("to",smsRequest.getTo()); // 수신번호 목록 * 최대 50개까지 한번에 전송할 수 있습니다.
			toArr.put(toJson);

			bodyJson.put("type", "sms"); // 메시지 Type (sms | lms)
			bodyJson.put("contentType", "COMM"); // 메시지 내용 Type (AD | COMM) * AD: 광고용, COMM: 일반용 (default: COMM) * 광고용
													// 메시지 발송 시 불법 스팸 방지를 위한 정보통신망법 (제 50조)가 적용됩니다.
			bodyJson.put("countryCode", "82"); // 국가 전화번호
			bodyJson.put("from", "01063087955"); // 발신번호 * 사전에 인증/등록된 번호만 사용할 수 있습니다.
			bodyJson.put("subject", ""); // 메시지 제목 * LMS Type에서만 사용할 수 있습니다.
			bodyJson.put("content", "인증번호를 입력하세요 " + smsRequest.getContent()); // 메시지 내용 * Type별로 최대 byte 제한이 다릅니다.* SMS: 80byte / LMS: 2000byte
			bodyJson.put("messages", toArr);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		String body = bodyJson.toString();

		// String으로 body data 생성
//		String body = "{\r\n" + 
//			"  \"type\": \"메시지 Type\",\r\n" + 		
//			"  \"contentType\": \"메시지 내용 Type\",\r\n" + 
//			"  \"countryCode\": \"국가 전화번호\",\r\n" + 
//			"  \"from\": \"발신번호\",\r\n" + 
//			"  \"subject\": \"메시지 제목\",\r\n" +
//			"  \"content\": \"메시지 내용\",\r\n" + 
//			"  \"messages\": [\r\n" + 
//			"    {"  +
//			"  		\"subject\": \"메시지 제목\",\r\n" +
//			"  		\"content\": \"메시지 내용\",\r\n" + 
//			"  		\"to\": \"수신번호\"\r\n" + 
//			"		}\r\n" + 
//			"  ]\r\n" +
//			"}";

		try {

			URL url = new URL(apiUrl);

			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setUseCaches(false);
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestProperty("content-type", "application/json");
			con.setRequestProperty("x-ncp-apigw-timestamp", timestamp);
			con.setRequestProperty("x-ncp-iam-access-key", accessKey);
			con.setRequestProperty("x-ncp-apigw-signature-v2",
					makeSignature(requestUrl, timestamp, method, accessKey, secretKey));
			con.setRequestMethod(method);
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());

			wr.write(body.getBytes());
			wr.flush();
			wr.close();

			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.println(responseCode);
			if (responseCode == 202) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}

			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();

			System.out.println(response.toString());
			return true;
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}

	public static String makeSignature(String url, String timestamp, String method, String accessKey, String secretKey)
			throws NoSuchAlgorithmException, InvalidKeyException {
		String space = " "; // one space
		String newLine = "\n"; // new line

		String message = new StringBuilder().append(method).append(space).append(url).append(newLine).append(timestamp)
				.append(newLine).append(accessKey).toString();

		SecretKeySpec signingKey;
		String encodeBase64String;
		try {

			signingKey = new SecretKeySpec(secretKey.getBytes("UTF-8"), "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
			encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);
		} catch (UnsupportedEncodingException e) {
			encodeBase64String = e.toString();
		}

		return encodeBase64String;
	}
}
