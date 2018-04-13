package manageAPI;

import java.net.HttpURLConnection;

import com.CommonUtils.APIResponse;
import com.CommonUtils.URLConnection;

public class Test_Comm_GetProductByShop {
	String url="http://192.168.1.211:8091/Commission/GetProductByShop";
	String httpresult=null;
	String request=null;
	public void checked(){
	httpresult = APIResponse.getWebHttpRespone(url,request);
	}
}
