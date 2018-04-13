package AutoTest.MeiKeMeiChe.Sales.Api;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;

import org.testng.annotations.Test;
import com.CommonUtils.APIResponse;
import com.CommonUtils.Common;
import com.CommonUtils.UserCalendar;
import com.CommonUtils.GetTestData.ExcelDataPath;
import com.GetJDBCData.GetDBData;


import Auto.Test.MeiKeMeiChe.ApiClass.StatisticsShopDetialsList;
import net.sf.json.JSONArray;

public class Test_Statistics_GetRevokeShopDeposit {
	
	//设置读取数据源的路径
	String path = ExcelDataPath.setExcelPath("E:\\我的文档\\javaweb\\Sales.Api\\testData\\02test.xls");
	String sheet = ExcelDataPath.setExcelSheet("sheet5");
	String apiname="Statistics/GetRevokeShopDeposit";
	
	  	 String shopName;             //店铺名称
		 String dispatchNum;			 //外派人数
		 String revokeDate;			 //撤店日期
		 String shopLeader;			 //负责人
		 String depositTotal;		 //押金合计
	
	public String apiResult= null,data= null;
	public String monthLast = null,endDate=null,startDate=null,empId=null,type=null,date=null;
	public String sqltype1= null,sqltype2=null,sqltype3=null,sqltype4=null;
	ArrayList<StatisticsShopDetialsList> apiResultList = new ArrayList<StatisticsShopDetialsList>();
	//ArrayList<ResultList> dbResultList = new ArrayList<ResultList>();
	
	@Test(dataProvider = "MyDataProvider", dataProviderClass = com.CommonUtils.GetTestData.MyDataProvider.class)
     public void getParameterFromJson(String json) throws IOException{
			date = Common.getJsonValue(json, "Date");
			empId=Common.getJsonValue(json, "EmpId");	 
			type=Common.getJsonValue(json, "Type");
			//获取数据库开始和结束时间
		UserCalendar userdate = new UserCalendar();
			startDate =  userdate.getMonthFirst(date);
		    endDate = userdate.getMonthLast(date);
		 //  ArrayList<ResultList> apiResultList = new ArrayList<ResultList>();
		   	apiResultList = getApiResultList(apiname, json);
//		   	 for (int i = 0; i < apiResultList.size(); i++) {
//				System.out.println(apiResultList.get(i).getShopName());
//				System.out.println(apiResultList.get(i).getDepositTotal());
//			}
//		   	
		   	
		   //	System.out.println(EndDate);
		   	//虚拟管理员查询
		 if(empId.equalsIgnoreCase("00341d62-c944-449a-a651-a7df018482f6")){
			 if (Integer.valueOf(type).intValue()==0) {
				 //撤销应退押金店铺
					sqltype1 = "select aa.name,aa.deposit,aa.Removedate,bb.Name,cc.num from "
								+ "(select * from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2  and Removedate>='"+ startDate +"' and Removedate<'" + endDate +"'"
								+ ") as aa inner join T_EMP_Employee as bb on aa.ManagerId=bb.id"
								+ " left join (select COUNT(ID)as num,ShopId from  T_EDI_ShopEmpDeposit group by ShopId ) as cc on aa.id=cc.shopid";
			 	System.out.println("撤销应退押金店铺：");
			 	checked(sqltype1);
			 	}
			 else if (Integer.valueOf(type).intValue()==2) {
				//撤销已退押金店铺
				 sqltype2="select aa.name,aa.deposit,aa.Removedate,bb.Name,cc.num from "
							+ "(select * from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=2  and Removedate>='"+ startDate +"' and Removedate<'" + endDate +"'"
							+ ") as aa inner join T_EMP_Employee as bb on aa.ManagerId=bb.id"
							+ " left join (select COUNT(ID)as num,ShopId from  T_EDI_ShopEmpDeposit group by ShopId ) as cc on aa.id=cc.shopid";

				System.out.println("撤销已退押金店铺：");
				checked(sqltype2);
				}
			 else if (Integer.valueOf(type).intValue()==1) {
				 //撤销未退押金店铺
				 sqltype3="select aa.name,aa.deposit,aa.Removedate,bb.Name,cc.num from "
							+ "(select * from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=0  and Removedate>='"+ startDate +"' and Removedate<'" + endDate +"'"
							+ ") as aa inner join T_EMP_Employee as bb on aa.ManagerId=bb.id"
							+ " left join (select COUNT(ID)as num,ShopId from  T_EDI_ShopEmpDeposit group by ShopId ) as cc on aa.id=cc.shopid";
				System.out.println("撤销未退押金店铺：");
				checked(sqltype3);
				}

		 }
		 
		 //非虚拟管理员
		 else{ 
			 if (Integer.valueOf(type).intValue()==0) {
				 //撤销应退押金店铺
					sqltype1 = "select aa.name,aa.deposit,aa.Removedate,bb.Name,cc.num from "
								+ "(select * from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=1 and Removedate>='"+ startDate +"' and Removedate<'" + endDate +"'"
								+ ") as aa inner join T_EMP_Employee as bb on aa.ManagerId=bb.id"
								+ " left join (select COUNT(ID)as num,ShopId from  T_EDI_ShopEmpDeposit group by ShopId ) as cc on aa.id=cc.shopid"
								+ " where aa.ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee "
								+ " where Leader='"+ empId + "') or ManagerId='" + empId +"') ";
			 	System.out.println("撤销应退押金店铺：");
			 	checked(sqltype1);
			 	}
			 else if (Integer.valueOf(type).intValue()==2) {
				//撤销已退押金店铺
				 sqltype2="select aa.name,aa.deposit,aa.Removedate,bb.Name,cc.num from "
							+ "(select * from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=2  and Removedate>='"+ startDate +"' and Removedate<'" + endDate +"'"
							+ ") as aa inner join T_EMP_Employee as bb on aa.ManagerId=bb.id"
							+ " left join (select COUNT(ID)as num,ShopId from  T_EDI_ShopEmpDeposit group by ShopId ) as cc on aa.id=cc.shopid"
							+ " where aa.ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee "
							+ " where Leader='"+ empId + "') or ManagerId='" + empId +"') ";

				System.out.println("撤销已退押金店铺：");
				checked(sqltype2);
				}
			 else if (Integer.valueOf(type).intValue()==1) {
				 //撤销未退押金店铺
				 sqltype3="select aa.name,aa.deposit,aa.Removedate,bb.Name,cc.num from "
							+ "(select * from t_edi_shop where TYPE=3 and MarkId='20151004134109855859' and Status=2 and DepositStatus=0  and Removedate>='"+ startDate +"' and Removedate<'" + endDate +"'"
							+ ") as aa inner join T_EMP_Employee as bb on aa.ManagerId=bb.id"
							+ " left join (select COUNT(ID)as num,ShopId from  T_EDI_ShopEmpDeposit group by ShopId ) as cc on aa.id=cc.shopid"
							+ " where aa.ID in(select ID from  T_EDI_Shop where ManagerId in (select ID from T_EMP_Employee "
							+ " where Leader='"+ empId + "') or ManagerId='" + empId +"') ";
				System.out.println("撤销未退押金店铺：");
				checked(sqltype3);
				}
		 }
		 //获取数据库返回数据
		 }
	 //获取接口返回的数据，存储为ReprotOrderList 对象
	public ArrayList<StatisticsShopDetialsList> getApiResultList(String apiname,String json) throws IOException {
		//创建请求实例
		APIResponse APIResponse = new APIResponse();
		//获取接口返回参数
		apiResult=APIResponse.getHttpRespone(apiname, json);
		//System.out.println(apiResult);
		//获取接口参数中字段值
		data = Common.getJsonValue(apiResult, "Data");
		JSONArray jsonArray = JSONArray.fromObject(data);
		
		ArrayList<StatisticsShopDetialsList> apiResponseL = new ArrayList<StatisticsShopDetialsList>();
		 for(int i=0;i<jsonArray.size();i++){
			 StatisticsShopDetialsList apiResponseO = new StatisticsShopDetialsList(); 
			 apiResponseO.setShopName(jsonArray.getJSONObject(i).getString("ShopName"));  		 //店铺名称
			 apiResponseO.setDispatchNum(jsonArray.getJSONObject(i).getString("DispatchNum")); 	//外派人数 
			 apiResponseO.setRevokeDate(jsonArray.getJSONObject(i).getString("RevokeDate"));	//撤店日期
			 apiResponseO.setShopLeader(jsonArray.getJSONObject(i).getString("ShopLeader"));   //负责人
			 apiResponseO.setDepositTotal(jsonArray.getJSONObject(i).getString("DepositTotal"));    //押金合计
			 
			 
			 apiResponseL.add(apiResponseO);
		 }
		return apiResponseL;	
		}
	
		
	
	//获取数据库返回的数据，存储为StatisticsShopDetialsList对象，并用接口返回的数据进行比较，
	public void checked(String sqltype){
			
			ArrayList<StatisticsShopDetialsList> dbResultListL = new ArrayList<StatisticsShopDetialsList>();
			dbResultListL = GetDBData.getStatistics_GetRevokeShopDepositByDB(sqltype);

			//System.out.println("接口人数为："+apiResultList.size()+"数据库人数为："+ dbResultListL.size());
			if(dbResultListL.size()<=apiResultList.size()){
				//将接口返回数据和数据库返回数据进行遍历对比，打印，不相同的数据。
				for (int i = 0; i < apiResultList.size(); i++) {
					
					String api = apiResultList.get(i).getDepositTotal();
					 for (int j = 0; j < dbResultListL.size(); j++) {
						// System.out.println(dbResultListL.size());
						String db =  dbResultListL.get(j).getDepositTotal();
						
						if(apiResultList.get(i).getShopName().equalsIgnoreCase(dbResultListL.get(j).getShopName())){
							
							 if(api==null){
									api = "0.00";
								 }
								 else{}
							 if(db==null||db.equalsIgnoreCase("0.00")||db.equalsIgnoreCase("0")){
									db = "0.00";
								 }
								 else{
									 //设置分为符，创建实例
									 DecimalFormat df2 = new DecimalFormat("#,###.00");
									 //System.out.println(Double.parseDouble(sum));//sum转化为double类型的
									 db = df2.format(Double.parseDouble(db));	 
								}
				
							if (api.equalsIgnoreCase(db)){
								System.out.println("pass");
								break;
							}
							else {
								
							System.out.println("接口返回店铺："+ apiResultList.get(i).getShopName() + "撤店日期："+ apiResultList.get(i).getRevokeDate() +"负责人："+ apiResultList.get(i).getShopLeader() +"押金合计："+ apiResultList.get(i).getDepositTotal());
							System.out.println("数据库返回店铺："+ dbResultListL.get(j).getShopName() + "撤店日期："+ dbResultListL.get(j).getRevokeDate() +"负责人："+ dbResultListL.get(j).getShopLeader() +"押金合计："+ dbResultListL.get(j).getDepositTotal());
							break;
							}
						}
						else if(j==(dbResultListL.size()-1)){
							System.out.println("未找到在数据库中找到改人员："+ apiResultList.get(i));
							//System.out.println("未找到在数据库中找到改人员："+ apiResultList.get(i).getName()+ apiResultList.get(i).getEmpId() +"车辆数："+ apiResultList.get(i).getSum());
						}
							
							
						
					 }
						//System.out.println("未找到在数据库中找到改人员："+ apiResultList.get(i).getEmpId() +"车辆数："+ apiResultList.get(i).getSum());
 
				}
				
			}
		else {//当数据库返回数量大于接口返回数量，打印数据库多的数据
			for (int i = 0; i < dbResultListL.size(); i++) {
				 for (int j = 0; j < apiResultList.size(); j++) {
					if(dbResultListL.get(i).getShopName().equalsIgnoreCase(apiResultList.get(j).getShopName())){
						
						if (dbResultListL.get(i).getDepositTotal().equalsIgnoreCase(apiResultList.get(j).getDepositTotal())){
							System.out.println("pass");
							break;
						}
						else {
							System.out.println(dbResultListL.get(i));
							System.out.println(apiResultList.get(j));	
//						System.out.println("数据库返回人员："+ dbResultListL.get(i).getName() + dbResultListL.get(i).getEmpId() +"车辆数："+ dbResultListL.get(i).getSum());
//						System.out.println("接口返回人员："+ apiResultList.get(j).getName() + apiResultList.get(j).getEmpId() +"车辆数："+ apiResultList.get(j).getSum());
						break;
						}
					}
					else if(j==(dbResultListL.size()-1)){
						System.out.println("未找到在接口中找到改人员："+ dbResultListL.get(i));
					}
						
						
					
				 }
					//System.out.println("未找到在数据库中找到改人员："+ apiResultList.get(i).getEmpId() +"车辆数："+ apiResultList.get(i).getSum());

			}
			
		}
	}
	
}

