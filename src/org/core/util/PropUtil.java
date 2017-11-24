package org.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropUtil {

	/**
	 * 根据路劲获取配置文件
	 * @param path
	 * @return
	 */
	public static Properties getPropertiesFile(String path){
		Properties prop = new Properties();  
		try {
			InputStream inStream = PropUtil.class.getClassLoader().getResourceAsStream(path);
			prop.load(inStream);
		} catch (IOException e) {
			e.printStackTrace();
		}  
		return prop;
	}
	/**
	 * 获取sys.properties配置文件
	 * @return
	 */
	public static Properties getSys(){
		Properties prop=getPropertiesFile("sys.properties");
		return prop;
	}
	
	/**
	 * 获取sys.properties配置文件的值
	 * @return
	 */
	public static String getSysValue(String key){
		return getSys().getProperty(key);
	}
	 
	public static void main(String[] args) {
		System.out.println(getSysValue("imgurl"));
	}
}
