package com.cognizant.TestExample;

import org.json.simple.JSONObject;

import com.cognizant.ConstantFile.Url_constant;
import com.cognizant.Persistence.RestResponse;
import com.cognizant.Util.ReuasableUtil;
import com.cognizant.WSS.Api_Registration_Login;

public class PayAsaGuest {
	private Api_Registration_Login http_Methods = new Api_Registration_Login();
	private static JSONObject object; 
	public RestResponse details_lite(String url)
	{
		String payload  = "{\"account-number\":\"8311696048\",\"postcode\":\"\"}"; 
		RestResponse response = http_Methods.postRequest(url+"/bin/payments/details-lite", payload); 
		object = ReuasableUtil.stringtojson(response.getResponseBody()); 		
		return response; 
	}
	public RestResponse details(String url)
	{
		String payload="{\"id\":\""+ object.get("id")+"\",\"accountInfo\":" + object.get("accountInfo")+",\"paymentAmount\":\"40.00\",\"paymentLite\":true}"; 
		RestResponse response = http_Methods.postRequest(url+"/bin/payments/details", payload); 
		System.out.println(response.getResponseCode());
		return response; 
	}

}
