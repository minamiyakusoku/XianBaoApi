package com.stxb.ifs.encode.impl;

import com.stxb.ifs.encode.Encode;
import com.stxb.utils.encode.DES3;

public class Des3ECBEncode implements Encode{
	String KEY;
	
	public Des3ECBEncode(String key){this.KEY=key;}
	
	@Override
	public String encode(String value) throws Exception {
		return DES3.des3Encode(KEY, value);
	}

	@Override
	public String decode(String ciphertext) throws Exception {
		return DES3.des3Dncode(KEY, ciphertext);
	}

}
