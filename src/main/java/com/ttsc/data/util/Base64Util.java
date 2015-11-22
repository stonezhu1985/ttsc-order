package com.ttsc.data.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;

public class Base64Util {

	/**
	 * base64字符串转为文件存储
	 * @param imgFilePath
	 * @param baseStr
	 * @return
	 */
	public static boolean generateImage(String savePath,String fileName, String baseStr) { // 对字节数组字符串进行Base64解码并生成图片
		if (baseStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(baseStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			File file = new File(savePath);
            if(!file.exists()){
            	file.mkdirs();
            }
            
			OutputStream out = new FileOutputStream(savePath+savePath);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
