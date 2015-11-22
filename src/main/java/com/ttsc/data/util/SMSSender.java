package com.ttsc.data.util;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**  
 * SMSSender
 * @author stone.zhu
 *
 */
public class SMSSender {
	
	private static Logger logger = LoggerFactory.getLogger(SMSSender.class);
	// 目标url
	private static String URL = "http://118.145.18.236:9999/sms.aspx";
	
	private static String ENCODED ="UTF-8";
	
	/**
	 * 组装数据
	 * @param num
	 * @return
	 */
	public static Map<String,String> sendMailProperty(String num,String phone){
		Map<String,String> map = new HashMap<String,String>();
		map.put("action", "send");
		map.put("userid", "1672");
		map.put("account", "luojy1688");
		map.put("password", "luojianyuan11");
		map.put("mobile", phone);
		map.put("content", "【天天生财】 您本次验证码为"+num+",感谢您的使用！");
		map.put("sendTime", "");
		map.put("extno","");
		return map;
	}
	/**
	 * post的方式请求资源服务器的数据
	 * 
	 * @param url
	 *            url路径
	 * @param param
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static void urlPost(String url, Map param) throws Exception {
		JSONObject array = new JSONObject();
		HttpClient htpClient = new HttpClient();  
		try {
			long startTime = System.currentTimeMillis();
			NameValuePair[] nvps = new NameValuePair[param.size()];
			Iterator ite = param.entrySet().iterator();
			int i = 0;
			while (ite.hasNext()) {
				Entry en = (Entry) ite.next();
				String key = en.getKey().toString();
				String value = en.getValue().toString();
				NameValuePair nvp = new NameValuePair(key, value);
				nvps[i] =nvp;
				i++;
			}
			PostMethod  httpPost = new PostMethod(URL+url);
			httpPost.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,ENCODED);
			httpPost.addParameters(nvps);
		    int statusCode = htpClient.executeMethod(httpPost);
		    byte[] responseBody = httpPost.getResponseBody();
		    String response = new String(responseBody,ENCODED);
		    if(statusCode != HttpStatus.SC_OK && statusCode != HttpStatus.SC_NO_CONTENT){
		    	logger.info("response is:"+response);
				throw new Exception("api 报错;response is:"+response);
			}
		    long endTime = System.currentTimeMillis();
		    logger.info("url is:" + url + ";param" + param + ";cost time:" + (endTime - startTime));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}