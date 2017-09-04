package com.stxb.ifs.encode.impl;

import com.stxb.ifs.encode.Encode;
import com.stxb.utils.encode.DES3;

public class Des3CBCEncode implements Encode{
	String KEY;
	String IV;
	public Des3CBCEncode(String key,String iv){
		this.KEY=key;
		this.IV=iv;
	}
	@Override
	public String encode(String value) throws Exception {
		return new String(DES3.des3EncodeCBC(KEY.getBytes(), IV.getBytes(), value.getBytes()),"UTF-8");
	}
	@Override
	public String decode(String ciphertext) throws Exception {
		return new String(DES3.des3DecodeCBC(KEY.getBytes(), IV.getBytes(), ciphertext.getBytes()),"UTF-8");
	}

}
