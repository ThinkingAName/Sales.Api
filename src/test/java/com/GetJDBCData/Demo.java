package com.GetJDBCData;
import java.lang.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;  
public class Demo {

      
  
        public static void main(String[] args) { 
//             String str1 = "tutorials point", str2 = "http://";
//             CharSequence cs1 = "int"; 
//             // string contains the specified sequence of char values 
//            boolean retval = str1.contains("tutorials point1"); 
//            System.out.println("Method returns : " + retval);  
//            // string does not contain the specified sequence of char value 
//              retval = str2.contains("_");                                  
//            System.out.println("Methods returns: " + retval); 
        	//String sql="select count(distinct(id)) as sum from (select bb.* from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 and aa.State in (3,4,5) and aa.Status=1 and  cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate<'2018-03-01 00:00:00.000') as salorder";;
   		 String sql = "select count(distinct(id)) as sum from (select bb.* from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Id where cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389') and bb.ProId in (select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C') and DATEDIFF(MI,aa.ConstructionTime,aa.EndConstructionTime)<=10 and aa.State in (3,4,5) and aa.Status=1 and  cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate<'2018-03-01 00:00:00.000') as salorder";

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
 	        	 System.out.println(sum);
 	        	 System.out.println(rs.getString(1));
 	        	  
 	          }
 	          
 	        } catch (Exception e) {
 	            e.printStackTrace();
 	        }finally{
 	            DBUtils.closeAll(rs, stmt, conn);
 	        }
 	       System.out.println(sum);
 	     // return sum;
 	    }
        	
         }
    

