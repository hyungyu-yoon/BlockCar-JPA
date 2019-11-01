package com.bollock.blockcar.fabric;

import java.util.Properties;

import com.bollock.blockcar.util.CommonUtil;

public class FabricUtil {
	public static Properties getPropertiesWith(String filename) {
		Properties properties = new Properties();
		properties.put("pemBytes", CommonUtil.readString(filename).getBytes());
		properties.setProperty("sslProvider", "openSSL");
		properties.setProperty("negotiationType", "TLS");
		return properties;
	}
}
