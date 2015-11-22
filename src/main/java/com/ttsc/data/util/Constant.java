package com.ttsc.data.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author arno.jiang
 *
 */
public class Constant {
	public static final String USER_LOGIN_INFO = "USER_LOGIN_INFO";
	public static final String VALID_CODE_INFO = "VALID_CODE_INFO";
	
	public static final String TASK_MAIN_FILE = "task_main_file";
	public static final String TASK_ADDITIONAL_FILE = "task_additional_file";
	
	private static Map<String,String> platMap = new HashMap<String,String>();
	static{
		platMap.put("1", "淘宝/天猫");
		platMap.put("2", "京东");
	}
	
	public static String getPlatName(String platId){
		return platMap.get(platId);
	}
}
