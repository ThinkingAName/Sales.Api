package com.CommonUtils;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

 public class Common {
     public static String getJsonValue(String JsonString, String JsonId) {
         String JsonValue = "";
        //trim()去掉字符串首尾的空格
         if (JsonString == null || JsonString.trim().length() < 1) {
             return null;
         }
         try {
        	
             JSONObject obj1 = JSONObject.fromObject(JsonString);
             if (obj1.containsKey(JsonId)) {
            	 JsonValue = obj1.getString(JsonId);
 			}
             else {
				return null;
			}
             
         } catch (JSONException e) {
             e.printStackTrace();
         }
         return JsonValue;
     }
 }