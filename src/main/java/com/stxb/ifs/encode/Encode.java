package com.stxb.ifs.encode;

/**
 * 通用加密解密接口
 * @author akku
 *
 */
public interface Encode {
	/**
	 * 加密
	 * @param value 原文
	 * @return 密文
	 * @throws Exception
	 */
	String encode(String value) throws Exception;
	/**
	 * 解密
	 * @param ciphertext 密文
	 * @return 原文
	 * @throws Exception
	 */
	String decode(String ciphertext) throws Exception;
}
