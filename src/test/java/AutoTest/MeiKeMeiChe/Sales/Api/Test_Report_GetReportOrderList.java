package AutoTest.MeiKeMeiChe.Sales.Api;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.testng.annotations.Test;
import com.CommonUtils.APIResponse;
import com.CommonUtils.Common;
import com.CommonUtils.GetTestData.ExcelDataPath;
import com.GetJDBCData.GetDBData;

import Auto.Test.MeiKeMeiChe.ApiClass.ReportOrderList;
import net.sf.json.JSONArray;
public class Test_Report_GetReportOrderList {
	
	//设置读取数据源的路径
	String path = ExcelDataPath.setExcelPath("E:\\我的文档\\javaweb\\Sales.Api\\testData\\02test.xls");
	String sheet = ExcelDataPath.setExcelSheet("sheet3");
	
	public String apiResult= null,data= null;
	String apiname="Report/GetReportOrderList";
	public String StartDate = null,EndDate=null,UserId=null,Leader=null,DepartId=null,Type=null;
	public String sqltype1= null,sqltype2=null,sqltype3=null,sqltype4=null;
	ArrayList<ReportOrderList> apiReportOrderList = new ArrayList<ReportOrderList>();
	//ArrayList<ReportOrderList> dbReportOrderList = new ArrayList<ReportOrderList>();
	
	@Test(dataProvider = "MyDataProvider", dataProviderClass = com.CommonUtils.GetTestData.MyDataProvider.class)
     public void getReportOrderList(String json) throws IOException{
		 StartDate = Common.getJsonValue(json, "StartDate");
		 EndDate=Common.getJsonValue(json, "EndDate");
		 UserId=Common.getJsonValue(json, "UserId");	 
		 System.out.println(Leader);
		 Type=Common.getJsonValue(json, "Type");
		 DepartId=Common.getJsonValue(json, "DepartId");
		
		 
		 // 更改sql的日期和接口相同
		  	Date date = new Date();
		 //创建日期格式对象
		  SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//		  获取当前系统时间日历时间，通过日历时间对，日历进行加减运算；
		  //
		  Calendar cal = Calendar.getInstance();
		  	try {
				cal.setTime(format.parse(EndDate));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   cal.add(Calendar.DATE,1);
		   EndDate =format.format(cal.getTime());
		   //System.out.println(EndDate);
		   
		 //  ArrayList<ReportOrderList> apiReportOrderList = new ArrayList<ReportOrderList>();
		   	apiReportOrderList = testGetReportOrderList(apiname, json);
//		   	 for (int i = 0; i < apiReportOrderList.size(); i++) {
//				System.out.println(apiReportOrderList.get(i).getEmpId());
//				System.out.println(apiReportOrderList.get(i).getSum());
//			}
//		   	
		   	
		   //	System.out.println(EndDate);
		 if(DepartId==null){
			 Leader=UserId;
			 if (Integer.valueOf(Type).intValue()==1) {
				 sqltype1="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " where cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"') " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
				 
			//异常人员
			 	System.out.println("异常人员：");
			 	checked(sqltype1);
			 	}
			 else if (Integer.valueOf(Type).intValue()==2) {
				 sqltype2="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " where cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"') " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
			//正常人员
				System.out.println("正常人员：");
				checked(sqltype2);
				}
			 else if (Integer.valueOf(Type).intValue()==3) {
				 sqltype3="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " inner join T_Aft_Feedback as dd on aa.Id=dd.ObjectId  " + 
						    " where cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"') " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
			//投诉人员
				System.out.println("投诉人员：");
				checked(sqltype3);
				}
			 else if (Integer.valueOf(Type).intValue()==4) {
				 sqltype4="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " where cc.EmpId in (select ID from T_EMP_Employee where Leader='"+Leader+"') " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>25 " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
			//超时人员
				System.out.println("超时人员：");
				checked(sqltype4);
				}
		
				 // System.out.println(sqltype1);
				  
		  
				  
				  
//				  sqlAllServiceCar ="select count(distinct(id)) as sum from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389')and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C')and aa.State in (3,4,5) and aa.Status=1 and  cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//				  sqlAbnormalCar = "select count(distinct(id)) as sum from (select bb.* from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//				  sqlOvertimeCar = "select count(distinct(id)) from(select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>25 and  aa.State in (3,4,5) and aa.Status=1  and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder ";
//				  sqlNormalCar = "select count(distinct(id)) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 and  aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//				  sqlComplaintCar = "select count(distinct(id)) from (select aa.Id  from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id inner join T_Aft_Feedback as dd on aa.Id=dd.ObjectId where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and aa.State in (3,4,5) and aa.Status=1 and aa.Id in (select ObjectId  from T_Aft_Feedback ) and cc.OperatDate>=" + StartDate + "and cc.OperatDate<=" + EndDate + ") as salorder";
//		       
		 }else{ 
			 if (Integer.valueOf(Type).intValue()==1) {
				 sqltype1="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " where cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like" + "'%"+DepartId+"%')) " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
				 
			//异常人员
			 	System.out.println("异常人员：");
			 	checked(sqltype1);
			 	}
			 else if (Integer.valueOf(Type).intValue()==2) {
				 sqltype2="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " where cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like" + "'%"+DepartId+"%')) " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
			
			 
			//正常人员
				System.out.println("正常人员：");
				checked(sqltype2);
				}
			 else if (Integer.valueOf(Type).intValue()==3) {
				 sqltype3="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " inner join T_Aft_Feedback as dd on aa.Id=dd.ObjectId  " + 
						    " where cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like" + "'%"+DepartId+"%')) " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
			
			//投诉人员
				System.out.println("投诉人员：");
				checked(sqltype3);
				}
			 else if (Integer.valueOf(Type).intValue()==4) {
				 sqltype4="select COUNT(id) sum,name,EmpId from " +
						    "(select aa.*,emp.Name,emp.Id EmpId from T_Sal_Order aa " +
						    " inner join T_Sal_OrderDetails bb on aa.Id=bb.OrderId " +
						    " inner join T_Sal_Process cc on cc.OrderDetailsId=bb.Id " +
						    " inner join T_EMP_Employee emp on cc.EmpId=emp.Id " +
						    " where cc.EmpId in (select id from T_EMP_Employee where DeptId in (select ID from T_Work_Department where Treeids like" + "'%"+DepartId+"%')) " +
						    " and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') " +
						    " and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>25 " +
						    " and  aa.State in (3,4,5) and aa.Status=1" +
						    " and cc.OperatDate>='"+StartDate+"' and cc.OperatDate< '"+EndDate+"' and cc.OperateType=5)"
						    + "as salorder group by EmpId,name  order by sum desc";
			
				 
			//超时人员
				System.out.println("超时人员：");
				checked(sqltype4);
				}
		
				 // System.out.println(sqltype1);
		 }
		 //获取数据库返回数据
		
	
		 
		 
}
	 //获取接口返回的数据，存储为ReprotOrderList 对象
	public ArrayList<ReportOrderList> testGetReportOrderList(String apiname,String json) throws IOException {
		APIResponse APIResponse = new APIResponse();
		//获取接口返回参数
		apiResult=APIResponse.getHttpRespone(apiname, json);
		//System.out.println(apiResult);
		//获取接口参数中字段值
		data = Common.getJsonValue(apiResult, "Data");
		JSONArray jsonArray = JSONArray.fromObject(data);
		
		ArrayList<ReportOrderList> reportOrderListL = new ArrayList<ReportOrderList>();
		 for(int i=0;i<jsonArray.size();i++){
			 ReportOrderList reportOrderListO = new ReportOrderList();
			  reportOrderListO.setSum(jsonArray.getJSONObject(i).getString("CarNum"));
			 reportOrderListO.setEmpId(jsonArray.getJSONObject(i).getString("EmpId"));
			 reportOrderListO.setName(jsonArray.getJSONObject(i).getString("EmpName"));
			 reportOrderListL.add(reportOrderListO);
		 }
		return reportOrderListL;	
	} 
	
	//获取数据库返回的数据，存储为ReportOrderList对象，并用接口返回的数据进行比较，
	public void checked(String sqltype){
			
			ArrayList<ReportOrderList> dbReportOrderListL = new ArrayList<ReportOrderList>();
			dbReportOrderListL = GetDBData.getDBSUMandEmpid(sqltype);
//				for (int i = 0; i < dbReportOrderListL.size(); i++) {
//					System.out.println(dbReportOrderListL.get(i).getEmpId());
//					System.out.println(dbReportOrderListL.get(i).getSum());
//				}
			System.out.println("接口人数为："+apiReportOrderList.size()+"数据库人数为："+ dbReportOrderListL.size());
			if(dbReportOrderListL.size()<=apiReportOrderList.size()){
				//将接口返回数据和数据库返回数据进行遍历对比，打印，不相同的数据。
				for (int i = 0; i < apiReportOrderList.size(); i++) {
					 for (int j = 0; j < dbReportOrderListL.size(); j++) {
						// System.out.println(dbReportOrderListL.size());
						if(apiReportOrderList.get(i).getEmpId().equalsIgnoreCase(dbReportOrderListL.get(j).getEmpId())){
							
							if (apiReportOrderList.get(i).getSum().equalsIgnoreCase(dbReportOrderListL.get(j).getSum())){
								System.out.println("pass");
								break;
							}
							else {
							System.out.println("接口返回人员："+ apiReportOrderList.get(i).getName() + apiReportOrderList.get(i).getEmpId() +"车辆数："+ apiReportOrderList.get(i).getSum());
							System.out.println("数据库返回人员："+ dbReportOrderListL.get(j).getName() + dbReportOrderListL.get(j).getEmpId() +"车辆数："+ dbReportOrderListL.get(j).getSum());
							break;
							}
						}
						else if(j==(dbReportOrderListL.size()-1)){
							System.out.println("未找到在数据库中找到改人员："+ apiReportOrderList.get(i).getName()+ apiReportOrderList.get(i).getEmpId() +"车辆数："+ apiReportOrderList.get(i).getSum());
						}
							
							
						
					 }
						//System.out.println("未找到在数据库中找到改人员："+ apiReportOrderList.get(i).getEmpId() +"车辆数："+ apiReportOrderList.get(i).getSum());
 
				}
				
			}
		else {//当数据库返回数量大于接口返回数量，打印数据库多的数据
			for (int i = 0; i < dbReportOrderListL.size(); i++) {
				 for (int j = 0; j < apiReportOrderList.size(); j++) {
					if(dbReportOrderListL.get(i).getEmpId().equalsIgnoreCase(apiReportOrderList.get(j).getEmpId())){
						
						if (dbReportOrderListL.get(i).getSum().equalsIgnoreCase(apiReportOrderList.get(j).getSum())){
							System.out.println("pass");
							break;
						}
						else {
						System.out.println("数据库返回人员："+ dbReportOrderListL.get(i).getName() + dbReportOrderListL.get(i).getEmpId() +"车辆数："+ dbReportOrderListL.get(i).getSum());
						System.out.println("接口返回人员："+ apiReportOrderList.get(j).getName() + apiReportOrderList.get(j).getEmpId() +"车辆数："+ apiReportOrderList.get(j).getSum());
						break;
						}
					}
					else if(j==(dbReportOrderListL.size()-1)){
						System.out.println("未找到在接口中找到改人员："+ dbReportOrderListL.get(i).getName()+ dbReportOrderListL.get(i).getEmpId() +"车辆数："+ dbReportOrderListL.get(i).getSum());
					}
						
						
					
				 }
					//System.out.println("未找到在数据库中找到改人员："+ apiReportOrderList.get(i).getEmpId() +"车辆数："+ apiReportOrderList.get(i).getSum());

			}
			
		}
	}
	
}

