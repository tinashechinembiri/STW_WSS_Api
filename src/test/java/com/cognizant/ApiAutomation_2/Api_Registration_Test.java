package com.cognizant.ApiAutomation_2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.cognizant.ConstantFile.Url_constant;
import com.cognizant.Persistence.RestResponse;
import com.cognizant.TestExample.Api_registration;

public class Api_Registration_Test {

	Api_registration http_Methods; 
	String url; 
	@Before
	public void setup()
	{
		url = Url_constant.stw_preproid;
		http_Methods = new Api_registration();	
	}
	
	@Test
	public void registration_1()
	{
		RestResponse test_response = http_Methods.register_account(url);
		assertEquals(200,test_response.getResponseCode()); 
		
	}
	@Test
	public void registration_2()
	{
		RestResponse test_response = http_Methods.register_Lastname(url); 
		assertEquals(200,test_response.getResponseCode()); 
		
	}
	@Test
	public void registration_3()
	{
		RestResponse test_response = http_Methods.register(url); 
		assertEquals(200,test_response.getResponseCode()); 
		
	}
}
