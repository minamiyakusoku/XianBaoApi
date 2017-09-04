package com.stxb.ifs.encode.impl;

import com.stxb.ifs.encode.Encode;

public class NoneEncode implements Encode{

	@Override
	public String encode(String value) throws Exception {
		return value;
	}

	@Override
	public String decode(String ciphertext) throws Exception {
		return ciphertext;
	}

}
