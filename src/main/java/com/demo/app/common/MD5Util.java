package com.demo.app.common;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * MD5加解密
 */
public class MD5Util {

	/**
	 * Used by the hash method.
	 */
	private static MessageDigest digest = null;

	public synchronized static final byte[] hash(String data) {
		if (digest == null) {
			try {
				digest = MessageDigest.getInstance("MD5");
			} catch (NoSuchAlgorithmException nsae) {
				System.err.println("Failed to load the MD5 MessageDigest. ");
				nsae.printStackTrace();
			}
		}
		// Now, compute hash.
		// digest.update(data.getBytes());
		// return toHex(digest.digest());
		return digest.digest(data.getBytes());
	}

	public static final String toHex(byte hash[]) {
		StringBuffer buf = new StringBuffer(hash.length * 2);
		int i;

		for (i = 0; i < hash.length; i++) {
			if (((int) hash[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) hash[i] & 0xff, 16));
		}
		return buf.toString();
	}

	public static final String encoderStr(String str) {
		if (str == null || str.equals("")) {
			return null;
		}
		BASE64Encoder encoder = new BASE64Encoder();
		String result = encoder.encodeBuffer(hash(str)).trim();
		// result = result.substring(0, result.indexOf("\r\n"));
		return result;
	}

//	public  static void main(String args[]){
////ISGMyneATSuhkiwz4BURBQ==
//		System.out.println(hash("123"));
//		System.out.println(encoderStr("123"));
//		System.out.println(toHex(hash("666666")));
//	}
}
