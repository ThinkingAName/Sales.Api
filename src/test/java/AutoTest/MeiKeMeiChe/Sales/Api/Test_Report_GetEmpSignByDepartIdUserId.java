package AutoTest.MeiKeMeiChe.Sales.Api;

import net.sf.json.JSONObject;

public class Test_Report_GetEmpSignByDepartIdUserId {
	
	public void TestSubordinateSUM(){
		String sql="select distinct(id) from (select aa.Id from T_Sal_Order as aa inner join T_Sal_OrderDetails as bb on aa.Id=bb.OrderId inner join T_Sal_Process as cc on cc.OrderDetailsId=bb.Idwhere cc.EmpId in (select ID from T_EMP_Employee where Leader='A9F61645-4A03-42DE-8CA0-A787012A3389')and bb.ProId in ( select id from T_Stock_Product where BasiceProductId='59A451E6-A500-4FA2-B18B-1F343BCB445C')and aa.State in (3,4,5) and aa.Status=1and  cc.OperatDate>='2018-02-01 00:00:00.000' and cc.OperatDate<'2018-03-01 00:00:00.000') as salorder group by Id";
		String sqlname="Report/GetEmpSignByDepartIdUserId";
		JSONString josn="{"StartDate":"2018-02-01","EndDate":"2018-02-28","UserId":"a9f61645-4a03-42de-8ca0-a787012a3389"}";
		JSONObject josn={"StartDate":"2018-02-01","EndDate":"2018-02-28","UserId":"a9f61645-4a03-42de-8ca0-a787012a3389"};
		
		
	}
	
	
	
	
}
