package com.cognizant.TestExample;

import org.json.simple.JSONObject;

import com.cognizant.ConstantFile.Credential_Constant;
import com.cognizant.Persistence.RestResponse;
import com.cognizant.Util.ReuasableUtil;
import com.cognizant.WSS.Api_Registration_Login;

public class PaymentPlans_DD {
	private Api_Registration_Login http_Methods; 
	private static JSONObject object; 
	
	public PaymentPlans_DD()
	{
		http_Methods = new Api_Registration_Login();
		http_Methods.Login(); 
		
	}
	public RestResponse details(String url)
	{
		RestResponse response =http_Methods.getRequest(url+ "/bin/directdebits/details?_=1563532840925"); 
		object  = ReuasableUtil.stringtojson(response.getResponseBody()); 
		
		
		
		return response; 
	}
	public RestResponse directdebits(String url)
	{
		
		String payload = "{\"links\":[],\"id\":\""+ object.get("id")+"\",\"accountInfo\":"+object.get("accountInfo")+",\"paymentInfo\":{\"paymentDay\":\"8\",\"paymentMethod\":\"DIRECT_DEBIT\",\"paymentFrequency\":\"MONTHLY\",\"startDate\":\"\"},\"bankDetails\":"+object.get("bankDetails")+",\"action\":\"CREATE\",\"numberOfDefaultedPlans\":0,\"isPaymentPlanCancelled\":false,\"paymentPlanPermissions\":{\"canUserCreate\":{\"isAllowed\":true,\"displayMessage\":\"\"},\"canUserAmend\":{\"isAllowed\":false,\"displayMessage\":\"\"},\"canUserCancel\":{\"isAllowed\":false,\"displayMessage\":\"\"},\"canUserView\":{\"isAllowed\":false,\"displayMessage\":\"\"},\"canUserChangeBankDetail\":{\"isAllowed\":false,\"displayMessage\":\"DM16.10\",\"date\":\"\"},\"canUserChangePaymentDate\":{\"isAllowed\":true,\"displayMessage\":\"\"},\"canUserChangePaymentAmount\":{\"isAllowed\":true,\"displayMessage\":\"\"},\"canUserChangePaymentMethod\":{\"isAllowed\":true,\"displayMessage\":\"\"}},\"userCanCreateDirectDebitPlan\":{\"allowDirectDebitPlans\":true,\"displayMessage\":\"\"},\"conditionalPaymentPlan\":{\"paymentPlanIsConditional\":false,\"displayMessage\":\"\"},\"userMustPayMinBalance\":"+ object.get("userMustPayMinBalance")+",\"accountCondition\":{\"accountHasCARMS\":false,\"displayMessage\":\"\"},\"specialConditions\":{\"demandDirectDebitMessage\":\"\"},\"PaymentDetails\":{},\"formattedPaymentFrequency\":\"Monthly\",\"formattedPaymentMethod\":\"Direct Debit\",\"formattedBankAccountNumber\":\"****5678\",\"formattedSortcode\":\"** ** 34\",\"paymentDay\":null,\"startDate\":null,\"formattedPaymentDate\":\"8th\",\"monthlyPaymentAmount\":null}";
		RestResponse response =http_Methods.putRequest(url+ "/bin/directdebits",payload ); 
		object.clear();
		object  = ReuasableUtil.stringtojson(response.getResponseBody()); 
		System.out.println(object.get("bankDetails"));
		return null; 
	}
	public RestResponse Bank (String url)
	{
		/// need to fix the json 
		String payload = " {\"links\":[],\"id\":\""+ object.get("id")+"\",\"accountInfo\":"+ object.get("accountInfo")+",\"paymentInfo\":"+ object.get("paymentInfo")+",\"bankDetails\": "+ Credential_Constant.bank_details+",\"action\":\"CREATE\",\"numberOfDefaultedPlans\":0,\"isPaymentPlanCancelled\":false,\"paymentPlanPermissions\":{\"canUserCreate\":{\"isAllowed\":true,\"displayMessage\":\"\"},\"canUserAmend\":{\"isAllowed\":false,\"displayMessage\":\"\"},\"canUserCancel\":{\"isAllowed\":false,\"displayMessage\":\"\"},\"canUserView\":{\"isAllowed\":false,\"displayMessage\":\"\"},\"canUserChangeBankDetail\":{\"isAllowed\":false,\"displayMessage\":\"DM16.10\",\"date\":\"\"},\"canUserChangePaymentDate\":{\"isAllowed\":true,\"displayMessage\":\"\"},\"canUserChangePaymentAmount\":{\"isAllowed\":true,\"displayMessage\":\"\"},\"canUserChangePaymentMethod\":{\"isAllowed\":true,\"displayMessage\":\"\"}},\"userCanCreateDirectDebitPlan\":{\"allowDirectDebitPlans\":true,\"displayMessage\":\"\"},\"conditionalPaymentPlan\":{\"paymentPlanIsConditional\":false,\"displayMessage\":\"\"},\"userMustPayMinBalance\":{\"payMinimiumBalance\":false,\"displayMessage\":\"\",\"minBalanceToPay\":0,\"maxBalanceToPay\":16.6},\"accountCondition\":{\"accountHasCARMS\":false,\"displayMessage\":\"\"},\"specialConditions\":{\"demandDirectDebitMessage\":\"\"},\"PaymentDetails\":{},\"formattedPaymentFrequency\":\"Monthly\",\"formattedPaymentMethod\":\"Direct Debit\",\"formattedBankAccountNumber\":\"****5678\",\"formattedSortcode\":\"** ** 34\",\"paymentDay\":null,\"startDate\":null,\"formattedPaymentDate\":\"8th\",\"monthlyPaymentAmount\":\"17.82\",\"paymentScheduleOption\":\"accept\"}";
		RestResponse response = http_Methods.putRequest(url+"bin/directdebits/bank",payload);
		object.clear();
		object  = ReuasableUtil.stringtojson(response.getResponseBody()); 
		System.out.println(object.toJSONString());
		return response; 
		
	}
	public RestResponse post_Directdebits ()
	{
		return null; 
	}
	
}
