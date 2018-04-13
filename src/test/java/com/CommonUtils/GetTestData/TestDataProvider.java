package com.CommonUtils.GetTestData;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

import org.testng.annotations.*;
import org.testng.ITestContext;

/**
* @ClassName: MyDataProvider
* @Description: Demo the mixing of DataProvider and Parameters
* @author: qinjun
*/
public class TestDataProvider {

    /**
    * @Title: runTest
    * @Description:Run the test with DataProvider and Parameters
    * @return: void
    */
    @Test(dataProvider = "MyDataProvider", dataProviderClass = com.CommonUtils.GetTestData.MyDataProvider.class)
    public void runTest(ITestContext context ,String id, String name, String input, String expected) {
        String testId = context.getCurrentXmlTest().getParameter("test_id");
        if (testId.equalsIgnoreCase(id)) {
            System.out.println("test id:["+testId+"]");
            System.out.println("Got one test data.");
            System.out.println("id:["+id+"]");
            System.out.println("name:["+name+"]");
            System.out.println("input:["+input+"]");
            System.out.println("expected:["+expected+"]");
        }
        else
        	System.out.println("未配置testng。xml");
    }
}
