package com.stxb.utils.encode;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class DES3 {
	public static final String DES3_KEY="akkunokawaiikotoridayoniconiconi";
	public static final String DES3_IV="akkuakku";
	/**
	 * ECB加密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            明文
	 * @return 密文
	 * @throws Exception
	 */
	public static String des3Encode(String key, String data) throws Exception {
		byte[] BKey = key.getBytes("UTF-8");
		String decodeData = new BASE64Encoder().encode(BKey);
		// base加密
		byte[] keyD = new BASE64Decoder().decodeBuffer(decodeData);
		byte[] dataB = data.getBytes("UTF-8");
		// ECB加密
		byte[] strB = des3EncodeECB(keyD, dataB);
		String strM = new BASE64Encoder().encode(strB);
		return strM;
	}

	/**
	 * ECB解密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            要解密的String
	 * @return 明文
	 * @throws Exception
	 */
	public static String des3Dncode(String key, String dataM) throws Exception {
		byte[] BKey = key.getBytes("UTF-8");
		String decodeData = new BASE64Encoder().encode(BKey);
		// base加密
		byte[] keyD = new BASE64Decoder().decodeBuffer(decodeData);
		byte[] dataB = new BASE64Decoder().decodeBuffer(dataM);
		// ECB解密
		byte[] strB = ees3DecodeECB(keyD, dataB);
		String str = new String(strB, "UTF-8");
		return str;
	}

	/**
	 * ECB加密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static byte[] des3EncodeECB(byte[] key, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * ECB解密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static byte[] ees3DecodeECB(byte[] key, byte[] data)
			throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/ECB/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * CBC加密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data)
			throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * CBC解密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data)
			throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance("desede" + "/CBC/PKCS5Padding");
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}
	
	public static void main(String[] args) throws Exception {
		String key = "a01e1f4471g59eb8d7f776fg";
		System.out.println(key.length());
		String idcard = "370881198805265316xx";
		System.out.println("加密前："+ idcard);
		String ecb = des3Encode(key,idcard);
		System.out.println("ECB加密后："+ ecb);
		System.out.println("ECB解密后："+ des3Dncode(key,ecb));
		
//		byte[] ecbx = des3EncodeECB(key, idcard.getBytes());
// 		System.out.println("ECBX加密后："+new String(ecbx,"utf-8"));
// 		System.out.println("ECBX解密后："+new String(ees3DecodeECB(key,ecbx),"utf-8"));
// 		byte[] cbc = des3EncodeCBC(key, DES3_IV.getBytes(), idcard.getBytes());
// 		System.out.println("CBC加密后："+new String(cbc,"utf-8"));
// 		System.out.println("CBC解密后："+new String(des3DecodeCBC(key, DES3_IV.getBytes(), cbc),"utf-8"));
		
	}
	
}
