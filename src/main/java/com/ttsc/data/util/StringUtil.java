package com.ttsc.data.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/** 
     * 手机号验证 
     *  
     * @param  str 
     * @return 验证通过返回true 
     */  
    public static boolean isMobile(String str) { 
    	if(str == null){
    		return false;
    	}
        Pattern p = null;  
        Matcher m = null;  
        boolean b = false;   
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号  
        m = p.matcher(str);  
        b = m.matches();   
        return b;  
    }  
    
    /**
     * 生成随机验证码
     * @param size 验证码位数
     * @param type 0纯数字、1字母跟数字
     * @return
     */
    public static String buildValidateCode(int size,int type){
    	char[] codeSequence1 = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
    	char[] codeSequence2 = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };  
    	StringBuffer strRand = new StringBuffer();
    	Random random = new Random();  
    	for (int i = 0; i < size; i++) {  
    		 if(type ==0){
    			 strRand.append(codeSequence1[random.nextInt(10)]);
    		 }else{
    			 strRand.append(codeSequence2[random.nextInt(36)]);
    		 }
    	}
    	return strRand.toString();
    }

    public static void main(String[] args){
    	String phone = "1341234567898";
    	System.out.println(StringUtil.buildValidateCode(6,0));
    }
}
