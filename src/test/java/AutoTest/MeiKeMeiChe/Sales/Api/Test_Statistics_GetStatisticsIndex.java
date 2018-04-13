package AutoTest.MeiKeMeiChe.Sales.Api;

import java.io.IOException;
import java.text.DecimalFormat;
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

import net.sf.json.JSONArray;

//测财务统计，新增合作店，撤店数据
public class Test_Statistics_GetStatisticsIndex {

	//设置读取数据源的路径
		String path = ExcelDataPath.setExcelPath("E:\\我的文档\\javaweb\\Sales.Api\\testData\\02test.xls");
		String sheet = ExcelDataPath.setExcelSheet("Sheet4");
		
		public String apiResult= null,data= null;
		String apiname="Statistics/GetStatisticsIndex";
		public String empid = null, date = null;
		public String StartDate = null,EndDate=null;
		
		String NewShop;							//新增合作店
		String ShouldDepositNum;				//应收押金数	
		String ShouldDeposit;					//应收押金	
		String ActualDepositNum;				//实收押金数	
		String ActualDeposit;					//实收押金	
		String UnCollectDepositNum;				//未收押金数
		String UnCollectDeposit;				//未收押金	
		String RevokeShop;						//撤销合作店	
		String ShouldReturnDepositNum;			//应退押金数	
		String ShouldReturnDeposit;				//应退押金	
		String ActualReturnDepositNum;			//实退押金数	
		String ActualReturnDeposit;				//实退押金
		String UnCollectReturnDepositNum;		//未退押金数	
		String UnCollectReturnDeposit;          //未退押金
		
		String sqlNewShop;							//新增合作店
		String sqlShouldDepositNum;					//应收押金数	
		String sqlShouldDeposit;					//应收押金	
		String sqlActualDepositNum;					//实收押金数	
		String sqlActualDeposit;					//实收押金	
		String sqlUnCollectDepositNum;				//未收押金数
		String sqlUnCollectDeposit;					//未收押金	
		String sqlRevokeShop;						//撤销合作店	
		String sqlShouldReturnDepositNum;			//应退押金数	
		String sqlShouldReturnDeposit;				//应退押金	
		String sqlActualReturnDepositNum;			//实退押金数	
		String sqlActualReturnDeposit;				//实退押金
		String sqlUnCollectReturnDepositNum;		//未退押金数	
		String sqlUnCollectReturnDeposit;          //未退押金
		
		
		public String sqltype1= null,sqltype2=null,sqltype3=null,sqltype4=null;
		//ArrayList<ReportOrderList> apiReportOrderList = new ArrayList<ReportOrderList>();
		//ArrayList<ReportOrderList> dbReportOrderList = new ArrayList<ReportOrderList>();
		
		@Test(dataProvider = "MyDataProvider", dataProviderClass = com.CommonUtils.GetTestData.MyDataProvider.class)
	     public void getReportOrderList(String json) throws IOException{
			 date = Common.getJsonValue(json, "Date");
			 empid=Common.getJsonValue(json, "EmpId");
			 getAPIResult(apiname, json);
			 
			
			 
			 // 更改sql的日期和接口相同
			 // 	Date date = new Date();
			 //创建日期格式对象
			  SimpleDateFormat formatOld=new SimpleDateFormat("yyyy-MM");
			  SimpleDateFormat formatNew = new SimpleDateFormat("yyyy-MM-dd 00:00:00:000 ");
//			  获取当前系统时间日历时间，通过日历时间对，日历进行加减运算；
			  //
			  Calendar cal = Calendar.getInstance();
			  	try {
					cal.setTime(formatOld.parse(date));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			   cal.set(Calendar.DAY_OF_MONTH,1);
			   StartDate =formatNew.format(cal.getTime());
			   cal.add(Calendar.MONTH, 1);
			   EndDate =formatNew.format(cal.getTime());
			   //System.out.println(EndDate);
			  if(empid.equalsIgnoreCase("00341d62-c944-449a-a651-a7df018482f6")) {
				  //虚拟管理查询所有
			    sqlNewShop="select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1  and AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate  + "'";							//新增合作店
			   
			    sqlShouldDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1  and "
					   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
					   + "'";				//应收押金数	
				 sqlShouldDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "' ";					//应收押金	
				 sqlActualDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=1  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'";				//实收押金数	
				 sqlActualDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=1  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'";					//实收押金	
				 sqlUnCollectDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=0  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'";				//未收押金数
				 sqlUnCollectDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=0 and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'";				//未收押金
				
				
				 sqlRevokeShop = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'";						//撤销合作店	
				 sqlShouldReturnDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'";			//应退押金数	
				 sqlShouldReturnDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'";				//应退押金	
				 sqlActualReturnDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=2 and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'";			//实退押金数	
				 sqlActualReturnDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'";			//实退押金
				 sqlUnCollectReturnDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=1  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'";		//未退押金数	
				 sqlUnCollectReturnDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=1  and "
						   + "Removedate>='" + StartDate + "'and Removedate<'"+ EndDate 
						   + "'";          //未退押金
			   
			  }
			  else{
				   sqlNewShop="select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee "
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";							//新增合作店
			   
			    sqlShouldDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1  and "
					   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
					   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee "
					   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";				//应收押金数	
				 sqlShouldDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";					//应收押金	
				 sqlActualDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=1  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";				//实收押金数	
				 sqlActualDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=1  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";					//实收押金	
				 sqlUnCollectDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=0  and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";				//未收押金数
				 sqlUnCollectDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=1 and DepositStatus=0 and "
						   + "AuditDate>='" + StartDate + "' and AuditDate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";				//未收押金
				
				
				 sqlRevokeShop = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";						//撤销合作店	
				 sqlShouldReturnDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";			//应退押金数	
				 sqlShouldReturnDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"')";				//应退押金	
				 sqlActualReturnDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=2 and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";			//实退押金数	
				 sqlActualReturnDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=2  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";			//实退押金
				 sqlUnCollectReturnDepositNum = "select count(id)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=1  and "
						   + "Removedate>='" + StartDate + "' and Removedate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";		//未退押金数	
				 sqlUnCollectReturnDeposit = "select SUM(Deposit)  from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=1  and "
						   + "Removedate>='" + StartDate + "'and Removedate<'"+ EndDate 
						   + "'and ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee"
						   + " where Leader='"+ empid + "') or ManagerId='" + empid +"') ";          //未退押金
				  
			  }
				 //新增合作
				 	System.out.print("新增合作：");
				 	checked(NewShop, sqlNewShop);
				//应收押金数
					System.out.print("应收押金数：");
				 	checked(ShouldDepositNum, sqlShouldDepositNum);
				//应收押金
					System.out.print("应收押金：");
				 	checked(ShouldDeposit, sqlShouldDeposit);
				//实收押金数
					System.out.print("实收押金数：");
				 	checked(ActualDepositNum, sqlActualDepositNum);
				//实收押金
					System.out.print("实收押金：");
				 	checked(ActualDeposit, sqlActualDeposit);
			
				//未收押金数
				 	System.out.print("未收押金数：");
				 	checked(UnCollectDepositNum, sqlUnCollectDepositNum);
				//未收押金
					System.out.print("未收押金：");
				 	checked(UnCollectDeposit, sqlUnCollectDeposit);
				//撤销合作店
					System.out.print("撤销合作店：");
				 	checked(RevokeShop, sqlRevokeShop);
				//应退押金数
					System.out.print("应退押金数：");
				 	checked(ShouldReturnDepositNum, sqlShouldReturnDepositNum);
				//应退押金
					System.out.print("应退押金：");
				 	checked(ShouldReturnDeposit, sqlShouldReturnDeposit);
				 	
				
				//实退押金数	
				 	System.out.print("实退押金数: ");
				 	checked(ActualReturnDepositNum, sqlActualReturnDepositNum);
				//实退押金
					System.out.print("实退押金：");
				 	checked(ActualReturnDeposit, sqlActualReturnDeposit);
				//未退押金数
					System.out.print("未退押金数：");
				 	checked( UnCollectReturnDepositNum, sqlUnCollectReturnDepositNum);
				//未退押金
					System.out.print("未退押金：");
				 	checked( UnCollectReturnDeposit, sqlUnCollectReturnDeposit);
	
			 
			 
	}
		 //获取接口返回的数据，存储为ReprotOrderList 对象
		public void getAPIResult(String apiname,String json) throws IOException {
			APIResponse APIResponse = new APIResponse();
			//获取接口返回参数
			apiResult=APIResponse.getHttpRespone(apiname, json);
			System.out.println(apiResult);
			//获取接口参数中字段值
			data = Common.getJsonValue(apiResult, "Data");
			System.out.println(data);
			
			
			 NewShop = Common.getJsonValue(data, "NewShop");										//新增合作
			 ShouldDepositNum = Common.getJsonValue(data, "ShouldDepositNum");						//应收押金数	
			 ShouldDeposit = Common.getJsonValue(data, "ShouldDeposit");							//应收押金	
			 ActualDepositNum = Common.getJsonValue(data, "ActualDepositNum");						//实收押金数	
			 ActualDeposit = Common.getJsonValue(data, "ActualDeposit");							//实收押金	
			 UnCollectDepositNum = Common.getJsonValue(data, "UnCollectDepositNum");				//未收押金数
			 UnCollectDeposit = Common.getJsonValue(data, "UnCollectDeposit");						//未收押金	
			 RevokeShop = Common.getJsonValue(data, "RevokeShop");									//撤销合作店	
			 ShouldReturnDepositNum = Common.getJsonValue(data, "ShouldReturnDepositNum");			//应退押金数	
			 ShouldReturnDeposit = Common.getJsonValue(data, "ShouldReturnDeposit");				//应退押金	
			 ActualReturnDepositNum = Common.getJsonValue(data, "ActualReturnDepositNum");			//实退押金数	
			 ActualReturnDeposit = Common.getJsonValue(data, "ActualReturnDeposit");				//实退押金
			 UnCollectReturnDepositNum = Common.getJsonValue(data, "UnCollectReturnDepositNum");	//未退押金数	
			 UnCollectReturnDeposit = Common.getJsonValue(data, "UnCollectReturnDeposit");          //未退押金
		} 
		
		//获取数据库返回的数据，存储为ReportOrderList对象，并用接口返回的数据进行比较，
		public void checked(String apistring,String sqlstring){
			
			//获取sql 返回值；
			 String sum = GetDBData.getDBSUM(sqlstring);
			//String sum = "0.00";
			// System.out.println("数据库" +sum);
			 if(sum==null||sum.equalsIgnoreCase("0")){
				sum = "0.00";
			 }
			 else{
				 //设置分为符，创建实例
				 DecimalFormat df2 = new DecimalFormat("#,###.00");
				 //System.out.println(Double.parseDouble(sum));//sum转化为double类型的
				 sum = df2.format(Double.parseDouble(sum));	 
			}
			
			 if(apistring.equalsIgnoreCase(sum)){
				  System.out.println("pass");
			 }
			 else
				 System.out.println(" 接口返回数据为：" + apistring + "数据库返回为：" + sum);

		}
	}