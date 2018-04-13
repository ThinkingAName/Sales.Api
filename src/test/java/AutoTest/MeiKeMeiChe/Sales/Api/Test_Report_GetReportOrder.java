package AutoTest.MeiKeMeiChe.Sales.Api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils.Null;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.CommonUtils.APIResponse;
import com.CommonUtils.Common;
import com.GetJDBCData.GetDBData;

import net.sf.json.JSON;
import net.sf.json.JSONObject;
import scala.inline;

public class Test_Report_GetReportOrder {
	public String httpResult= null,data= null;
	String apiname="Report/GetReportOrder";
	public String AllServiceCar= null,AbnormalCar=null,ComplaintCar=null,NormalCar=null,OvertimeCar=null;
	public String StartDate = null,EndDate=null,UserId=null,DepartId=null,Leader=null;
	public String sqlAllServiceCar= null,sqlAbnormalCar=null,sqlComplaintCar=null,sqlNormalCar=null,sqlOvertimeCar=null;
	
	@Test(dataProvider = "MyDataProvider", dataProviderClass = com.CommonUtils.GetTestData.MyDataProvider.class)
     public void getToday(String json) throws IOException{
		 StartDate = Common.getJsonValue(json, "StartDate");
		 EndDate=Common.getJsonValue(json, "EndDate");
		 UserId=Common.getJsonValue(json, "UserId");
		// DepartId=Common.getJsonValue(json, "DepartId");
		 
		 // 日期操作
		  Date date = new Date();
		  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
		  Calendar cal = Calendar.getInstance();
		  	try {
				cal.setTime(format.parse(EndDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   cal.add(Calendar.DATE,1);
		   EndDate =format.format(cal.getTime());
		   System.out.println(EndDate);
		   
////		  			try {
////						date = format.parse(StartDate);
////					} catch (ParseException e) {
////						// TODO Auto-generated catch block
////						e.printStackTrace();
////					}
//		  			
//				  	cal.add(cal.DATE, 1);
//		  			String str = format.format(date);
//		  	//	Date date =	format.format(EndDate);
//				
		 Leader = UserId;
		 //穿件json 对象传值
//		 JSONObject jsonObject = new JSONObject();
//		 jsonObject.put("StartDate","2018-02-01");
//		 jsonObject.put("EndDate","2018-02-28");
//		 jsonObject.put("UserId","a9f61645-4a03-42de-8ca0-a787012a3389");
//		 String json=jsonObject.toString();
		 TestSubordinateSUM(apiname, json);
		 if(DepartId==null){
				  sqlAllServiceCar ="select count(distinct(id)) as sum from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>='"+StartDate+"'and cc.OperatDate<'"+EndDate+"'and cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"')and cc.OperateType=5) as salorder";
				  sqlAbnormalCar = "select count(distinct(id)) as sum from (select bb.* from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>='" + StartDate  + "'and cc.OperatDate<'" + EndDate + "'and cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"')and cc.OperateType=5) as salorder";
				  sqlOvertimeCar = "select count(distinct(id)) from(select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>25 and  aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>='" + StartDate + "'and cc.OperatDate<'" + EndDate + "'and cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"')and cc.OperateType=5) as salorder";
				  sqlNormalCar = "select count(distinct(id)) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime) > 10 and  aa.State in (3,4,5) and aa.Status=1  and cc.OperatDate>='" + StartDate + "'and cc.OperatDate<'" + EndDate + "'and cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"')and cc.OperateType=5) as salorder";
				  sqlComplaintCar = "select count(distinct(id)) from (select aa.Id  from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id inner join T_Aft_Feedback as dd on aa.Id=dd.ObjectId where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and aa.State in (3,4,5) and aa.Status=1 and aa.Id in (select ObjectId  from T_Aft_Feedback ) and cc.OperatDate>='" + StartDate + "'and cc.OperatDate<'" + EndDate + "'and cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"')and cc.OperateType=5) as salorder";
		         //String json="{"StartDate":"2018-02-01","EndDate":"2018-02-28","UserId":"a9f61645-4a03-42de-8ca0-a787012a3389"}";
		         //cityCode="101251201";
				  System.out.println(sqlAbnormalCar);
				  
				  
				  
				  
//				  sqlAllServiceCar ="select count(distinct(id)) as sum from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389')and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C')and aa.State in (3,4,5) and aa.Status=1 and  cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//				  sqlAbnormalCar = "select count(distinct(id)) as sum from (select bb.* from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//				  sqlOvertimeCar = "select count(distinct(id)) from(select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>25 and  aa.State in (3,4,5) and aa.Status=1  and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder ";
//				  sqlNormalCar = "select count(distinct(id)) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 and  aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//				  sqlComplaintCar = "select count(distinct(id)) from (select aa.Id  from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id inner join T_Aft_Feedback as dd on aa.Id=dd.ObjectId where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and aa.State in (3,4,5) and aa.Status=1 and aa.Id in (select ObjectId  from T_Aft_Feedback ) and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//		       
		 }else{ 
			  sqlAllServiceCar ="select count(distinct(id)) as sum from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C')and aa.State in (3,4,5) and aa.Status=1  and  cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + "and cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like '%" + DepartId + "%')) and cc.OperateType=5) as salorder";
			  sqlAbnormalCar = "select count(distinct(id)) as sum from (select bb.* from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate +  "and cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like '%" + DepartId + "%'))and cc.OperateType=5) as salorder";
			  sqlOvertimeCar = "select count(distinct(id)) from(select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>25 and  aa.State in (3,4,5) and aa.Status=1  and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + "and cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like '%" + DepartId + "%'))and cc.OperateType=5) as salorder";
			  sqlNormalCar = "select count(distinct(id)) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 and  aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + "and cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like '%" + DepartId + "%'))and cc.OperateType=5) as salorder";
			  sqlComplaintCar = "select count(distinct(id)) from (select aa.Id  from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id inner join T_Aft_Feedback as dd on aa.Id=dd.ObjectId where bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and aa.State in (3,4,5) and aa.Status=1 and aa.Id in (select ObjectId  from T_Aft_Feedback ) and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + "and cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like '%" + DepartId + "%'))and cc.OperateType=5) as salorder";
	        }
		 //服务车辆总数
		 	System.out.print("总服务车辆：");
		 	checked(AllServiceCar, sqlAllServiceCar);
		
		//异常车辆总数
			System.out.print("总异常车辆：");
		 	checked(AbnormalCar, sqlAbnormalCar);
		//投诉车辆总数
			System.out.print("总投诉车辆：");
		 	checked(ComplaintCar, sqlComplaintCar);
		//正常车辆总数
			System.out.print("总正常车辆：");
		 	checked(NormalCar, sqlNormalCar);
		//超时车辆总数
			System.out.print("总超时车辆：");
		 	checked(OvertimeCar, sqlOvertimeCar);

	 
		 
//		 //服务车辆总数
//		 String sum = GetDBData.getDBSUM(sqlAllServiceCar);
//		 Assert.assertEquals(AllServiceCar, GetDBData.getDBSUM(sqlAllServiceCar), "failed test");
//		 if(AllServiceCar.equalsIgnoreCase(sum)){
//			 System.out.println(sum);
//			  System.out.println("服务车辆总数pass");
//		 }
//		 else System.out.println("数据库查询总服务车辆数为：" + GetDBData.getDBSUM(sqlAllServiceCar) + "接口返回总服务车辆数为：" + AllServiceCar);
//		//异常车辆总数
//		 if(AbnormalCar.equalsIgnoreCase(GetDBData.getDBSUM(sqlAbnormalCar))){
//			 System.out.println("异常服务车辆总数pass");
//		 }
//		 else System.out.println("数据库查询异常服务车辆数为：" + GetDBData.getDBSUM(sqlAbnormalCar) + "接口返回异常服务车辆数为：" + AbnormalCar);
//		//投诉车辆总数
//		 if(ComplaintCar.equalsIgnoreCase(GetDBData.getDBSUM(sqlComplaintCar))){
//			 System.out.println("投诉服务车辆总数pass");
//		 }
//		 else System.out.println("数据库查询投诉服务车辆数为：" + GetDBData.getDBSUM(sqlComplaintCar) + "接口返回投诉服务车辆数为：" + ComplaintCar);
//		 
//		//正常车辆总数
//		 if(NormalCar.equalsIgnoreCase(GetDBData.getDBSUM(sqlNormalCar))){
//			 System.out.println("正常服务车辆总数pass");
//		 }
//		 else System.out.println("数据库查询正常服务车辆数为：" + GetDBData.getDBSUM(sqlNormalCar) + "接口返回正常服务车辆数为：" + NormalCar);
//		 
//		//超时车辆总数
//		 
//		 if(OvertimeCar.equalsIgnoreCase(GetDBData.getDBSUM(sqlOvertimeCar))){
//			 System.out.println("超时服务车辆总数pass");
//		 }
//		 else System.out.println("数据库查询超时服务车辆数为：" + GetDBData.getDBSUM(sqlOvertimeCar) + "接口返回超时服务车辆数为：" + OvertimeCar);
 }
	 
	 
	 
	 
	 
	 
	 
	 
	public void TestSubordinateSUM(String apiname,String json) throws IOException {
		APIResponse APIResponse = new APIResponse();
		//获取接口返回参数
		httpResult=APIResponse.getHttpRespone(apiname, json);
		System.out.println(httpResult);
		//获取接口参数中字段值
		data = Common.getJsonValue(httpResult, "Data");
		AllServiceCar = Common.getJsonValue(data, "AllServiceCar");
		AbnormalCar = Common.getJsonValue(data, "AbnormalCar");
		ComplaintCar = Common.getJsonValue(data, "ComplaintCar");
		NormalCar = Common.getJsonValue(data, "NormalCar");
		OvertimeCar = Common.getJsonValue(data, "OvertimeCar");
		System.out.println("服务车辆总数：" + AllServiceCar + "异常车辆总数：" + AbnormalCar + "投诉车辆总数：" + ComplaintCar + "正常车辆总数：" + NormalCar + "超时车辆总数：" + OvertimeCar );
	
//		String sqlAllServiceCar="select count(distinct(id)) as sum from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Idwhere cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389')and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C')and aa.State in (3,4,5) and aa.Status=1and  cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate<'2018-03-01 00:00:00.000') as salorder group by Id";	
		//		String sqlname="Report/GetEmpSignByDepartIdUserId";
//		JSONString josn="{"StartDate":"2018-02-01","EndDate":"2018-02-28","UserId":"a9f61645-4a03-42de-8ca0-a787012a3389"}";
//		JSONObject josn={"StartDate":"2018-02-01","EndDate":"2018-02-28","UserId":"a9f61645-4a03-42de-8ca0-a787012a3389"};
//		
		
	}
	public void checked(String apistring,String sqlstring){
		
		 String sum = GetDBData.getDBSUM(sqlstring);
		 if(apistring.equalsIgnoreCase(sum)){
			  System.out.println("pass");
		 }
		 else
			 System.out.println(" 接口返回数据为：" + apistring + "数据库返回为：" + sum);

	}
		
}
