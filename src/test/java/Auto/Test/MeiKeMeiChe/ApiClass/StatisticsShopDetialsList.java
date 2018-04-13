package Auto.Test.MeiKeMeiChe.ApiClass;

public class StatisticsShopDetialsList {
	    private String shopName;             //店铺名称
		private String dispatchNum;			 //外派人数
		private String revokeDate;			 //撤店日期
		private String shopLeader;			 //负责人
		private String depositTotal;		 //押金合计

		public void setShopName(String shopName){
			this.shopName = shopName;
		}
		public String getShopName(){
			return shopName;
		}
		public void setDispatchNum(String dispatchNum) {
		        this.dispatchNum = dispatchNum;
		    }
		public String getDispatchNum() {
		        return dispatchNum;
		    }
		public void setRevokeDate(String revokeDate) {
	        this.revokeDate = revokeDate;
	    }
		public String getRevokeDate() {
	        return revokeDate;
	    }  
		public void setShopLeader(String shopLeader) {
	        this.shopLeader = shopLeader;
	    }
		public String getShopLeader() {
	        return shopLeader;
	    }  
		public void setDepositTotal(String depositTotal) {
	        this.depositTotal = depositTotal;
	    }
		public String getDepositTotal() {
	        return depositTotal;
	    }  

}
