package com.cognizant.TestExample;

import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.cognizant.ConstantFile.Credential_Constant;
import com.cognizant.Persistence.RestResponse;
import com.cognizant.Util.ReuasableUtil;
import com.cognizant.WSS.Api_Registration_Login;

public class Api_registration {
	private Api_Registration_Login http_Methods; 
	private static JSONObject object; 
	public Api_registration()
	{
		
	}
	public RestResponse register_account(String url)
	{
		http_Methods = new Api_Registration_Login();
		String word = "WR14 2BH"; 
		String account = "8100076287";
		String payload = "{\"account\":\""+account+"\",\"postcode\":\""+word+"\",\"step\":\"0\"}"; 
		RestResponse response =  this.http_Methods.postRequest(url+"/bin/register",payload );
		object = ReuasableUtil.stringtojson(response.getResponseBody()); 
		return response; 
	}
	public RestResponse register_Lastname(String url)
	{
		http_Methods = new Api_Registration_Login();
		String payload = "{\"businessName\":null,\"legalEntity\":null,\"account\":\""+object.get("account") +"\",\"postcode\":\""+ object.get("postcode")+"\",\"isBusiness\":false,\"isMigrated\":false,\"username\":\""+ object.get("username")+"\",\"step\":\"1\",\"partialRegistration\":false,\"brand\":\"SEVERN_TRENT\",\"surname\":\"AAAAAA\"}"; 
		RestResponse response =  this.http_Methods.postRequest(url+"/bin/register",payload );
		object.clear();
		object = ReuasableUtil.stringtojson(response.getResponseBody()); 
		return response; 
	}
	public RestResponse register(String url)
	{
		http_Methods = new Api_Registration_Login();
		JSONArray objectArray = (JSONArray) object.get("leList"); 
		String payload = "{\"businessName\":null,\"legalEntity\":\"Mr COLLIN AAAAAA\",\"account\":\""+ object.get("account")+"\",\"postcode\":\""+object.get("postcode")+"\",\"isBusiness\":false,\"isMigrated\":false,\"username\":\"0\",\"step\":\"2\",\"partialRegistration\":false,\"brand\":\"SEVERN_TRENT\",\"surname\":\"AAAAAA\",\"leSelected\":"+objectArray.get(0) +",\"email\":\""+ReuasableUtil.random_email_generator(object.get("account").toString())+"\",\"confirm-email\":\"\",\"tel\":\""+"0"+ object.get("account")+"\",\"legalEntityUsername\":\"\",\"password\":\""+Credential_Constant.password+"\"}";
		System.out.println(payload);
		RestResponse response =  this.http_Methods.postRequest(url+"/bin/register",payload); 		
		return response; 
	}

}
