package org.albianj.security;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * DES安全编码组件
 * 
 * <pre>
 * 支持 DES、DESede(TripleDES,就是3DES)、AES、Blowfish、RC2、RC4(ARCFOUR)
 * DES          		key size must be equal to 56
 * DESede(TripleDES) 	key size must be equal to 112 or 168
 * AES          		key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
 * Blowfish     		key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
 * RC2          		key size must be between 40 and 1024 bits
 * RC4(ARCFOUR) 		key size must be between 40 and 1024 bits
 * 具体内容 需要关注 JDK Document http://.../docs/technotes/guides/security/SunProviders.html
 * </pre>
 */
public abstract class DESCoder extends Coder
{
	/**
	 * ALGORITHM 算法 <br>
	 * 可替换为以下任意一种算法，同时key值的size相应改变。
	 * 
	 * <pre>
	 * DES          		key size must be equal to 56
	 * DESede(TripleDES) 	key size must be equal to 112 or 168
	 * AES          		key size must be equal to 128, 192 or 256,but 192 and 256 bits may not be available
	 * Blowfish     		key size must be multiple of 8, and can only range from 32 to 448 (inclusive)
	 * RC2          		key size must be between 40 and 1024 bits
	 * RC4(ARCFOUR) 		key size must be between 40 and 1024 bits
	 * </pre>
	 * 
	 * 在Key toKey(byte[] key)方法中使用下述代码
	 * <code>SecretKey secretKey = new SecretKeySpec(key, ALGORITHM);</code> 替换
	 * <code>
	 * DESKeySpec dks = new DESKeySpec(key);
	 * SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
	 * SecretKey secretKey = keyFactory.generateSecret(dks);
	 * </code>
	 */
	// public static final String ALGORITHM = "DES";

	private static final String DEFAULT_DES_KEY = "!@#$%^&*_ALBIAN_DES_*&^%$#@!";

	@SuppressWarnings("unused")
	private static Key toKey(byte[] key) throws Exception
	{
		return toKey(DESStyle.DES, key);
	}

	private static Key toKey(DESStyle style, byte[] key) throws Exception
	{
		SecretKey secretKey = null;
		if (style == DESStyle.DES)
		{
			DESKeySpec dks = new DESKeySpec(key);
			SecretKeyFactory keyFactory = SecretKeyFactory
					.getInstance(StyleMapping.toDESStyleString(style));
			secretKey = keyFactory.generateSecret(dks);
		}
		else
		{
			// 当使用其他对称加密算法时，如AES、Blowfish等算法时，用下述代码替换上述三行代码
			secretKey = new SecretKeySpec(key,
					StyleMapping.toDESStyleString(style));
		}

		return secretKey;
	}

	public static byte[] decrypt(DESStyle style, String key, byte[] data)
			throws Exception
	{
		Key k = toKey(style, decryptBASE64(key));
		Cipher cipher = Cipher
				.getInstance(StyleMapping.toDESStyleString(style));
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	public static byte[] decrypt(String key, byte[] data) throws Exception
	{
		return decrypt(DESStyle.DES, key, data);
	}

	public static byte[] decrypt(byte[] data) throws Exception
	{
		return decrypt(DESStyle.DES, DEFAULT_DES_KEY, data);
	}

	public static String decrypt(DESStyle style, String key, String data)
			throws Exception
	{
		Key k = toKey(style, decryptBASE64(key));
		Cipher cipher = Cipher
				.getInstance(StyleMapping.toDESStyleString(style));
		cipher.init(Cipher.DECRYPT_MODE, k);
		return encryptBASE64(cipher.doFinal(decryptBASE64(data)));
	}

	public static String decrypt(String key, String data) throws Exception
	{
		return decrypt(DESStyle.DES, key, data);
	}

	public static String decrypt(String data) throws Exception
	{
		return decrypt(DESStyle.DES, DEFAULT_DES_KEY, data);
	}

	/**
	 * 加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encrypt(DESStyle style, String key, byte[] data)
			throws Exception
	{
		Key k = toKey(style, decryptBASE64(key));
		Cipher cipher = Cipher
				.getInstance(StyleMapping.toDESStyleString(style));
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	public static byte[] encrypt(String key, byte[] data) throws Exception
	{
		return encrypt(DESStyle.DES, key, data);
	}

	public static byte[] encrypt(byte[] data) throws Exception
	{
		return encrypt(DESStyle.DES, DEFAULT_DES_KEY, data);
	}

	public static String encrypt(DESStyle style, String key, String data)
			throws Exception
	{
		Key k = toKey(style, decryptBASE64(key));
		Cipher cipher = Cipher
				.getInstance(StyleMapping.toDESStyleString(style));
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return encryptBASE64(cipher.doFinal(decryptBASE64(data)));
	}

	public static String encrypt(String key, String data) throws Exception
	{
		return encrypt(DESStyle.DES, key, data);
	}

	public static String encrypt(String data) throws Exception
	{
		return encrypt(DESStyle.DES, DEFAULT_DES_KEY, data);
	}

	public static String initKey() throws Exception
	{
		return initKey(null);
	}

	public static String initKey(String seed) throws Exception
	{
		return initKey(DESStyle.DES,seed);
	}

	public static String initKey(DESStyle style, String seed) throws Exception
	{
		SecureRandom secureRandom = null;
		secureRandom = null == seed ? new SecureRandom() : new SecureRandom(
				decryptBASE64(seed));

		KeyGenerator kg = KeyGenerator.getInstance(StyleMapping
				.toDESStyleString(style));
		kg.init(secureRandom);
		SecretKey secretKey = kg.generateKey();
		return encryptBASE64(secretKey.getEncoded());
	}
}
