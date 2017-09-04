package com.stxb.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class updateDb {
	public static void main(String[] args) throws IOException {
		String path = "E:/akku/XianBao/src/main/resources/dataSource.properties";

		  //读取配置文件
	      FileInputStream inputStream = new FileInputStream(path);
	      Properties prop = new Properties();
	      // 加载
	      prop.load(inputStream);
	      // 获取
	      System.out.println(prop.getProperty("local.driverClassName"));
	      // 设置
	      prop.setProperty("local.username", "akku");
	      // 写到配置文件
	      FileOutputStream outputStream = new FileOutputStream(path);
	      prop.store(outputStream, "update message");
	      outputStream.close();
	}
}
