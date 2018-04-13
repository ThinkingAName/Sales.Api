package com.CommonUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class APIResponse {
//    private String url="http://192.168.1.211:8088";
//    public String geturl() {
//    	return url;
//    }

   public String getHttpRespone(String apiname,String  json) throws IOException {
   	         String line = "";
   	         String httpResults = "";
   	         String url="http://192.168.1.211:8088"  + "/MKMC.SalesApi/" + apiname;
   	        // System.out.println(url);
   	         try {
   	             HttpURLConnection connection = URLConnection.postConnection(url);
   	             // 建立实际的连接
   	             //connection.connect();
	   	          //conn.setRequestProperty("accept","*/*")此处为暴力方法设置接受所有类型，以此来防范返回415;
	   	          connection.setRequestProperty("accept","application/json");
	   	            // 往服务器里面发送数据
	   	            if (json != null) {
	   	                byte[] writebytes = json.getBytes();
	   	                // 设置文件长度
	   	                connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
	   	                OutputStream outwritestream = connection.getOutputStream();
	   	                outwritestream.write(json.getBytes());
	   	                outwritestream.flush();
	   	                outwritestream.close();
	   	                //Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode());
	   	            }
	   	            //开始发送请求
   	             BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
   	             while ((line = reader.readLine()) != null) {
   	                 httpResults = httpResults + line.toString();
   	             }
   	             reader.close();
   	             // 断开连接
   	             connection.disconnect();
   	         } catch (Exception e) {
   	             e.printStackTrace();
   	         }
   	         return httpResults;
   	     }
   
   public String getWebHttpRespone(String apiname,String  json) throws IOException {
	         String line = "";
	         String httpResults = "";
	         //String url="http://192.168.1.211:8088"  + "/MKMC.SalesApi/" + apiname;
	        // System.out.println(url);
	         try {
	             HttpURLConnection connection = URLConnection.postConnection(apiname);
	             // 建立实际的连接
	             //connection.connect();
 	          connection.setRequestProperty("accept","*/*");//此处为暴力方法设置接受所有类型，以此来防范返回415;
 	          //connection.setRequestProperty("accept","application/json");
 	            // 往服务器里面发送数据
 	            if (json != null) {
 	                byte[] writebytes = json.getBytes();
 	                // 设置文件长度
 	                connection.setRequestProperty("Content-Length", String.valueOf(writebytes.length));
 	                OutputStream outwritestream = connection.getOutputStream();
 	                outwritestream.write(json.getBytes());
 	                outwritestream.flush();
 	                outwritestream.close();
 	                //Log.d("hlhupload", "doJsonPost: conn"+conn.getResponseCode());
 	            }
	             BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),"utf-8"));
	             while ((line = reader.readLine()) != null) {
	                 httpResults = httpResults + line.toString();
	             }
	             reader.close();
	             // 断开连接
	             connection.disconnect();
	         } catch (Exception e) {
	             e.printStackTrace();
	         }
	         return httpResults;
	     }
   
}