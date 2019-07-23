package com.cognizant.ApiAutomation_2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import com.cognizant.ConstantFile.Url_constant;
import com.cognizant.Persistence.RestResponse;
import com.cognizant.TestExample.PayAsaGuest;


public class ExampleTest {
	 PayAsaGuest http_Methods ; 
	 String url = null; 
	@Before
	 public void Set_Up()
	{
		url = Url_constant.stw_preproid_64;
		http_Methods = new PayAsaGuest();	
		//http_Methods.Login(); 
	}
	@Test
	public void PayAsaGuest_details_lite()
	{
		RestResponse test_response = http_Methods.details_lite(url); 
		assertEquals(200,test_response.getResponseCode()); 		
	}
	@Test 
	public void PayAsaGuest_details()
	{
		RestResponse test_response = http_Methods.details(url);
		assertEquals(200,test_response.getResponseCode()); 
	}
	
	
	

}
