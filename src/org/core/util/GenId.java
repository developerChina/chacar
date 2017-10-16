package org.core.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class GenId {
	/**
	 * 产生一个32位的UUID
	 * 
	 * @return
	 */
	public static String UUID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replace("-", "");
	}

	/**
	 * 获取32位GUID
	 * 
	 * @return
	 */
	public static String GUID() {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			UUID uuid = UUID.randomUUID();
			String guidStr = uuid.toString();
			md.update(guidStr.getBytes(), 0, guidStr.length());
			return new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			return null;
		}
	}
	public static void main(String[] args) {
        System.out.println(UUID());
        System.out.println(GUID());
	}
}
