package com.bollock.blockcar.fabric;

import org.hyperledger.fabric.sdk.*;
import org.hyperledger.fabric.sdk.BlockEvent.TransactionEvent;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.bollock.blockcar.car.Car;
import com.bollock.blockcar.car.Record;

import java.io.*;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

@Repository
public class FabricRepositoryImpl implements IFabricRepository {
	private static final Logger logger = LoggerFactory.getLogger(FabricRepositoryImpl.class);

	private HFClient hfClient;
	private Channel channel;
	/**
	 * 패브릭 네트워크를 이용하기 위한 정보
	 */
	@Value("${fabric.ca-server.url}")
	private String CA_SERVER_URL;

	@Value("${fabric.ca-server.admin.name}")
	private String CA_SERVER_ADMIN_NAME;

	@Value("${fabric.ca-server.pem.file}")
	private String CA_SERVER_PEM_FILE;

	@Value("${fabric.org.name}")
	private String ORG_NAME;

	@Value("${fabric.org.msp.name}")
	private String ORG_MSP_NAME;

	@Value("${fabric.org.admin.name}")
	private String ORG_ADMIN_NAME;

	@Value("${fabric.peer.name}")
	private String PEER_NAME;

	@Value("${fabric.peer.url}")
	private String PEER_URL;

	@Value("${fabric.peer.pem.file}")
	private String PEER_PEM_FILE;

	@Value("${fabric.orderer.name}")
	private String ORDERER_NAME;

	@Value("${fabric.orderer.url}")
	private String ORDERER_URL;

	@Value("${fabric.orderer.pem.file}")
	private String ORDERER_PEM_FILE;

	@Value("${fabric.org.user.name}")
	private String USER_NAME;

	@Value("${fabric.org.user.secret}")
	private String USER_SECRET;

	@Value("${fabric.channel.name001}")
	private String CHANNEL_001_NAME;

	@Value("${fabric.channel.name002}")
	private String CHANNEL_002_NAME;

	private String chaincode_Name = "car_002_Management";

	private void loadChannel002() {

		CryptoSuite cryptoSuite = null;
		HFCAClient caClient = null;
		try {
			cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
			caClient = HFCAClient.createNewInstance(CA_SERVER_URL, FabricUtil.getPropertiesWith(CA_SERVER_PEM_FILE));
			caClient.setCryptoSuite(cryptoSuite);
			Enrollment adminEnroll = caClient.enroll("admin", "adminpw");
			FabricUser adminUser = new FabricUser(ORG_ADMIN_NAME, ORG_NAME, adminEnroll, ORG_MSP_NAME);

			hfClient = HFClient.createNewInstance();
			hfClient.setCryptoSuite(cryptoSuite);

			hfClient.setUserContext(adminUser);

			Orderer orderer = hfClient.newOrderer(ORDERER_NAME, ORDERER_URL,
					FabricUtil.getPropertiesWith(ORDERER_PEM_FILE));
			Peer peer = hfClient.newPeer(PEER_NAME, PEER_URL, FabricUtil.getPropertiesWith(PEER_PEM_FILE));
			channel = hfClient.newChannel(CHANNEL_002_NAME);
			channel.addOrderer(orderer);
			channel.addPeer(peer);
			channel.initialize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Record getRecord(JsonObject rec) {
		Record record = new Record(rec.getString("carSerialNumber"), rec.getString("carData"), rec.getString("createdAt"), rec.getString("expiredAt"));
		logger.info("CarSerial : carSerialNumber " + rec.getString("carSerialNumber") +" , carNumber :"+ rec.getString("carNumber") +", createdAt : "+ rec.getString("createdAt") + " expiredAt " + rec.getString("expiredAt"));
		return record;
	}



	@Override
	public boolean registerAccident(String carSerial, String carData) {
		if (this.hfClient == null || this.channel == null)
			loadChannel002();
		ChaincodeID id = ChaincodeID.newBuilder().setName(chaincode_Name).build();
		TransactionProposalRequest tpr = hfClient.newTransactionProposalRequest();
		tpr.setChaincodeID(id);
		tpr.setFcn("registerAccident");
		String[] args = { carSerial, carData };
		tpr.setArgs(args);
		Collection<ProposalResponse> res;
		try {
			res = channel.sendTransactionProposal(tpr);
			List<ProposalResponse> invalid = res.stream().filter(r -> r.isInvalid()).collect(Collectors.toList());
			if (!invalid.isEmpty()) {
				invalid.forEach(response -> {
					logger.info(response.getMessage());
				});
			}
			CompletableFuture<TransactionEvent> cf = channel.sendTransaction(res);
			TransactionEvent block = cf.get();
			for (ProposalResponse response : res) {
				logger.info(new String(response.getChaincodeActionResponsePayload()));
			}
			// display response
			if (res.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean registerMaintenance(String carSerial, String carData) {
		if (this.hfClient == null || this.channel == null)
			loadChannel002();
		ChaincodeID id = ChaincodeID.newBuilder().setName(chaincode_Name).build();
		TransactionProposalRequest tpr = hfClient.newTransactionProposalRequest();
		tpr.setChaincodeID(id);
		tpr.setFcn("registerMaintenance");
		String[] args = { carSerial };
		tpr.setArgs(args);
		Collection<ProposalResponse> res;
		try {
			res = channel.sendTransactionProposal(tpr);
			List<ProposalResponse> invalid = res.stream().filter(r -> r.isInvalid()).collect(Collectors.toList());
			if (!invalid.isEmpty()) {
				invalid.forEach(response -> {
					logger.info(response.getMessage());
				});
			}
			CompletableFuture<TransactionEvent> cf = channel.sendTransaction(res);
			TransactionEvent block = cf.get();
			for (ProposalResponse response : res) {
				logger.info(new String(response.getChaincodeActionResponsePayload()));
			}
			// display response
			if (res.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean registerUpdatedOwner(String carSerial, String carData) {
		if (this.hfClient == null || this.channel == null)
			loadChannel002();
		ChaincodeID id = ChaincodeID.newBuilder().setName(chaincode_Name).build();
		TransactionProposalRequest tpr = hfClient.newTransactionProposalRequest();
		tpr.setChaincodeID(id);
		tpr.setFcn("RegisterUpdatedOwner");
		String[] args = { carSerial };
		tpr.setArgs(args);
		Collection<ProposalResponse> res;
		try {
			res = channel.sendTransactionProposal(tpr);
			List<ProposalResponse> invalid = res.stream().filter(r -> r.isInvalid()).collect(Collectors.toList());
			if (!invalid.isEmpty()) {
				invalid.forEach(response -> {
					logger.info(response.getMessage());
				});
			}
			CompletableFuture<TransactionEvent> cf = channel.sendTransaction(res);
			TransactionEvent block = cf.get();
			for (ProposalResponse response : res) {
				logger.info(new String(response.getChaincodeActionResponsePayload()));
			}
			// display response
			if (res.size() >= 1) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Record> getAccidentHistory(String carSerial) {
		if (this.hfClient == null || this.channel == null)
			loadChannel002();
		ChaincodeID id = ChaincodeID.newBuilder().setName(chaincode_Name).build();
		QueryByChaincodeRequest qpr = hfClient.newQueryProposalRequest();
		qpr.setChaincodeID(id);
		qpr.setFcn("getAccidentHistory");
		qpr.setArgs(new String[] { String.valueOf(carSerial) });
		Collection<ProposalResponse> res;
		try {
			res = channel.queryByChaincode(qpr);
			List<ProposalResponse> invalid = res.stream().filter(r -> r.isInvalid()).collect(Collectors.toList());
			if (!invalid.isEmpty()) {
				invalid.forEach(response -> {
					logger.info(response.getMessage());
				});
			}
			List<Record> records = new ArrayList<>();
			for (ProposalResponse response : res) {
				logger.info(new String(response.getChaincodeActionResponsePayload()));
				JsonReader parser = Json
						.createReader(new ByteArrayInputStream(response.getChaincodeActionResponsePayload()));
				for (JsonValue record : parser.readArray()) {
					Record rec = getRecord((JsonObject) record);
					records.add(rec);
				}
			}
			return records;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Record> getMaintenanceHistory(String carSerial) {
		if (this.hfClient == null || this.channel == null)
			loadChannel002();
		ChaincodeID id = ChaincodeID.newBuilder().setName(chaincode_Name).build();
		QueryByChaincodeRequest qpr = hfClient.newQueryProposalRequest();
		qpr.setChaincodeID(id);
		qpr.setFcn("getMaintenanceHistory");
		qpr.setArgs(new String[] { String.valueOf(carSerial) });
		Collection<ProposalResponse> res;
		try {
			res = channel.queryByChaincode(qpr);
			List<ProposalResponse> invalid = res.stream().filter(r -> r.isInvalid()).collect(Collectors.toList());
			if (!invalid.isEmpty()) {
				invalid.forEach(response -> {
					logger.info(response.getMessage());
				});
			}
			List<Record> records = new ArrayList<>();
			for (ProposalResponse response : res) {
				logger.info(new String(response.getChaincodeActionResponsePayload()));
				JsonReader parser = Json
						.createReader(new ByteArrayInputStream(response.getChaincodeActionResponsePayload()));
				for (JsonValue record : parser.readArray()) {
					Record rec = getRecord((JsonObject) record);
					records.add(rec);
				}
			}
			return records;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Record> getOwnerHistory(String carSerial) {
		if (this.hfClient == null || this.channel == null)
			loadChannel002();
		ChaincodeID id = ChaincodeID.newBuilder().setName(chaincode_Name).build();
		QueryByChaincodeRequest qpr = hfClient.newQueryProposalRequest();
		qpr.setChaincodeID(id);
		qpr.setFcn("getOwnerHistory");
		qpr.setArgs(new String[] { String.valueOf(carSerial) });
		Collection<ProposalResponse> res;
		try {
			res = channel.queryByChaincode(qpr);
			List<ProposalResponse> invalid = res.stream().filter(r -> r.isInvalid()).collect(Collectors.toList());
			if (!invalid.isEmpty()) {
				invalid.forEach(response -> {
					logger.info(response.getMessage());
				});
			}
			List<Record> records = new ArrayList<>();
			for (ProposalResponse response : res) {
				logger.info(new String(response.getChaincodeActionResponsePayload()));
				JsonReader parser = Json
						.createReader(new ByteArrayInputStream(response.getChaincodeActionResponsePayload()));
				for (JsonValue record : parser.readArray()) {
					Record rec = getRecord((JsonObject) record);
					records.add(rec);
				}
			}
			return records;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Record queryOwner(String carSerial) {
		if (this.hfClient == null || this.channel == null)
			loadChannel002();
		ChaincodeID id = ChaincodeID.newBuilder().setName(chaincode_Name).build();
		QueryByChaincodeRequest qpr = hfClient.newQueryProposalRequest();
		qpr.setChaincodeID(id);
		qpr.setFcn("queryOwner");
		qpr.setArgs(new String[] {carSerial});
		Collection<ProposalResponse> res;
		try {
			res = channel.queryByChaincode(qpr);
			List<ProposalResponse> invalid = res.stream().filter(r -> r.isInvalid()).collect(Collectors.toList());
			if (!invalid.isEmpty()) {
			    invalid.forEach(response -> {
			    	logger.info(response.getMessage());
			    });
			}
			Record rec = null;
			for(ProposalResponse response : res) {
				logger.info(new String(response.getChaincodeActionResponsePayload()));
				JsonReader parser = Json.createReader(new ByteArrayInputStream(response.getChaincodeActionResponsePayload()));
				rec = getRecord(parser.readObject());
			}
			return rec;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
