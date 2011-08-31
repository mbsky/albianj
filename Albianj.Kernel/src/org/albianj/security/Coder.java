package org.albianj.security;

import java.security.MessageDigest;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public abstract class Coder
{
	private static final String DEFAULT_SHA_KEY = "!@#$%^&*_ALBIAN_SHA_*&^%$#@!";
	private static final String DEFAULT_MD5_KEY = "!@#$%^&*_ALBIAN_MD5_*&^%$#@!";

	public static byte[] decryptBASE64(String key) throws Exception
	{
		return Base64.decodeBase64(key);
	}

	public static String encryptBASE64(byte[] key) throws Exception
	{
		return Base64.encodeBase64String(key);
	}

	public static byte[] encryptMD5(byte[] data) throws Exception
	{
		return encryptMD5(DEFAULT_MD5_KEY, data);
	}

	public static byte[] encryptMD5(String key, byte[] data) throws Exception
	{
		MessageDigest md5 = MessageDigest.getInstance(key);
		md5.update(data);
		return md5.digest();
	}

	public static byte[] encryptSHA(byte[] data) throws Exception
	{
		return encryptSHA(DEFAULT_SHA_KEY, data);
	}

	public static byte[] encryptSHA(String key, byte[] data) throws Exception
	{
		MessageDigest sha = MessageDigest.getInstance(key);
		sha.update(data);
		return sha.digest();
	}

	public static String encryptMD5(String data) throws Exception
	{
		return encryptBASE64(encryptMD5(decryptBASE64(data)));
	}

	public static String encryptSHA(String data) throws Exception
	{
		return encryptBASE64(encryptSHA(decryptBASE64(data)));
	}

	public static String initMacKey() throws Exception
	{
		return initMacKey(MACStyle.MD5);
	}
	
	public static String initMacKey(MACStyle style) throws Exception
	{
		return initMacKey(StyleMapping.toMACStyleString(style));
	}
	
	protected static String initMacKey(String key) throws Exception
	{
		KeyGenerator keyGenerator = KeyGenerator.getInstance(key);
		SecretKey secretKey = keyGenerator.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}
	
	
	public static String encryptHMAC(String key,MACStyle style,byte[] data) throws Exception
	{
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), StyleMapping.toMACStyleString(style));
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return encryptBASE64(mac.doFinal(data));
	}
	
	public static String encryptHMAC(String key,MACStyle style,String data) throws Exception
	{
		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), StyleMapping.toMACStyleString(style));
		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
		mac.init(secretKey);
		return encryptBASE64(mac.doFinal(decryptBASE64(data)));
	}
	
	public static String encryptHMAC(String key,byte[] data) throws Exception
	{
		return encryptHMAC(key,MACStyle.MD5,data);
	}
	
	public static String encryptHMAC(String key,String data) throws Exception
	{
		return encryptHMAC(key,MACStyle.MD5,data);
	}
	
//	public static byte[] encryptHMAC(String key,String macStyle,byte[] data) throws Exception
//	{
//		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), macStyle);
//		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//		mac.init(secretKey);
//		return mac.doFinal(data);
//
//	}
	
//	public static byte[] encryptHMAC(String key,byte[] data) throws Exception
//	{
//		SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
//		Mac mac = Mac.getInstance(secretKey.getAlgorithm());
//		mac.init(secretKey);
//		return mac.doFinal(data);
//
//	}
}