package com.GetJDBCData;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Auto.Test.MeiKeMeiChe.ApiClass.ReportOrderList;
import Auto.Test.MeiKeMeiChe.ApiClass.StatisticsShopDetialsList;

 
 
 
public class GetDBData {

	 public static String getDBSUM(String sql){
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        String sum = null;
	        //String sql = "select distinct(id) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate <'2018-03-01 00:00:00.000') as salorder";
	        try {
	            conn = DBUtils.getConnection();
	            stmt =  conn.createStatement();
	            rs = stmt.executeQuery(sql);
	          while(rs.next()){
	        	  sum=rs.getString(1);
	        	  //System.out.println(sum);
	          }
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//	        finally{
//	            DBUtils.closeAll(rs, stmt, conn);
//	        }
	        
	       // System.out.println(sum);
	      return sum;
	    }
	 //获取数据库中查询数据 GetReportOrderList
	 public static ArrayList<ReportOrderList> getDBSUMandEmpid(String sql){
	        Connection conn = null;
	        Statement stmt = null;
	        ResultSet rs = null;
	        ArrayList<ReportOrderList> dbReportOrderListsL = new ArrayList<ReportOrderList>();
	        //String sql = "select distinct(id) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate <'2018-03-01 00:00:00.000') as salorder";
	        try {
	            conn = DBUtils.getConnection();
	            stmt =  conn.createStatement();
	            rs = stmt.executeQuery(sql);
	          while(rs.next()){
	        	  ReportOrderList dbReportOrderListO = new ReportOrderList();
	        	  dbReportOrderListO.setSum(rs.getString(1));
	        	  dbReportOrderListO.setName(rs.getString(2));
	        	  dbReportOrderListO.setEmpId(rs.getString(3));
	        	  dbReportOrderListsL.add(dbReportOrderListO);
	        	  //System.out.println(sum);
	        	 
	          }
	          
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
//	        finally{
//	            DBUtils.closeAll(rs, stmt, conn);
//	        }
	        
	       // System.out.println(sum);
	      return  dbReportOrderListsL;
	    }
	
	//获取数据库中查询数据 GetReportOrderList
		 public static ArrayList<StatisticsShopDetialsList> getStatistics_GetRevokeShopDepositByDB(String sql){
		        Connection conn = null;
		        Statement stmt = null;
		        ResultSet rs = null;
		        ArrayList<StatisticsShopDetialsList> dbResultListL = new ArrayList<StatisticsShopDetialsList>();
		        //String sql = "select distinct(id) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate <'2018-03-01 00:00:00.000') as salorder";
		        try {
		            conn = DBUtils.getConnection();
		            stmt =  conn.createStatement();
		            rs = stmt.executeQuery(sql);
		          while(rs.next()){
		        	  StatisticsShopDetialsList dbResultListO = new StatisticsShopDetialsList();
		        	  dbResultListO.setShopName(rs.getString(1));
		        	  dbResultListO.setDepositTotal(rs.getString(2));
		        	  dbResultListO.setRevokeDate(rs.getString(3));
		        	  dbResultListO.setShopLeader(rs.getString(4));
		        	  dbResultListO.setDispatchNum(rs.getString(5));
		        	  dbResultListL.add(dbResultListO);
		        	  //System.out.println(sum);
		        	 
		          }
		          
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
//		        finally{
//		            DBUtils.closeAll(rs, stmt, conn);
//		        }
		        
		       // System.out.println(sum);
		      return  dbResultListL;
		    }
	 
	 
	
    public static void testSelect(String sql){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<User> list = new ArrayList<User>(); 
        //String sql = "select distinct(id) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=25 and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)>10 and aa.State in (3,4,5) and aa.Status=1 and cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate <'2018-03-01 00:00:00.000') as salorder";
        try {
            conn = DBUtils.getConnection();
            stmt =  conn.createStatement();
            rs = stmt.executeQuery(sql);
           int i = 0;
            while(rs.next()){
            	i++;
                User u = new User();
                u.setId(rs.getString(1));
                u.setNUM(i);
//                u.setPassword(rs.getString(3));
//                u.setEmail(rs.getString(4));
//                u.setBirthday(rs.getDate(5));
                list.add(u);
            }
            
            for (User user : list) {
                System.out.println(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            DBUtils.closeAll(rs, stmt, conn);
        }
      
    }
     
}
