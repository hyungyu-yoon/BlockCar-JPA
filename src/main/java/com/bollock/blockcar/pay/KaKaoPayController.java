package com.bollock.blockcar.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import io.swagger.annotations.Api;

@CrossOrigin("*")
@RestController
@RequestMapping("/kakao")
@Api(value = "Kakao", description = "Kakao Control")
public class KaKaoPayController {
	 
	@Autowired
    private KakaoService kakaopay;
    
	@GetMapping("/kakaoPay")
	public void kakaoPayGet() {
		
	}
	
    @PostMapping("/kakaoPay")
    public String kakaoPay(@RequestBody KakaoPayInfo info) {
        String str = kakaopay.kakaoPayReady(info);
        System.out.println("결제 url : " + str);
        return str;
    }
    
    @GetMapping("/kakaoPaySuccess")
    public KakaoPayApprovalVO kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model) {
        System.out.println("결제 Toekn : " + pg_token);
//        model.addAttribute("info", kakaopay.kakaoPayInfo(pg_token));
        KakaoPayApprovalVO kakaoinfo = kakaopay.kakaoPayInfo(pg_token);
        System.out.println(kakaoinfo.toString());
        return kakaoinfo;
    }
    
    @GetMapping("kakaoPayCancel")
    public String kakaoPayCancel() {
    	return "cancel";
    }
    
    
    @GetMapping("kakaoPaySuccessFail")
    public String kakaoPayFail() {
    	return "fail";
    }
    
}