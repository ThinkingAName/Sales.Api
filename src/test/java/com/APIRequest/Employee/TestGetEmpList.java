package com.APIRequest.Employee;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.testng.annotations.DataProvider;
import org.testng.annotations.*;
import org.testng.ITestContext;

import com.CommonUtils.APIResponse;
import com.CommonUtils.URLConnection;

public class TestGetEmpList {
	public String httpResult= null, weatherinfo= null, city=null,expect_city = null;
	APIResponse APIResponse = new APIResponse();
	
  @Test(dataProvider = "MyDataProvider",dataProviderClass = com.CommonUtils.GetTestData.MyDataProvider.class)
   public void result(String apiname,String json) throws IOException {
//	  		System.out.println(apiname);
//	  		System.out.println(json);
   	         //url=("http://192.168.1.211:8088/MKMC.SalesApi/Employee/GetEmpList");
   	        httpResult=APIResponse.getHttpRespone( apiname, json);
   	        System.out.println(httpResult);
   	     }
}