package com.jxufe.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.tomcat.util.codec.binary.Base64;


public class MD5Utils {
	public static String md5(String message) {

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte md5[] = md.digest(message.getBytes());

			Base64 encoder = new Base64();
			return encoder.encodeToString(md5);
			} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
