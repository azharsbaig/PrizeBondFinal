package com.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseConfig {
public static  String getconfig(String key) throws IOException {		
		
		Properties pro = new Properties();// how to read config /note pad or text file?
				
		String path ="./config.properties";
		FileInputStream fis = new FileInputStream(path);		
		//connection of both
		pro.load(fis);		
		
		return pro.get(key).toString();
	}
//	public static void main(String[] args) throws Throwable {
//		System.out.println(BaseConfig.getconfig("URL"));	
//	}

}