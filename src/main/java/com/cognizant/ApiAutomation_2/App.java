package com.cognizant.ApiAutomation_2;

import com.cognizant.ConstantFile.Url_constant;
import com.cognizant.TestExample.Api_registration;
import com.cognizant.TestExample.PayAsaGuest;
import com.cognizant.TestExample.PaymentPlans_DD;
import com.cognizant.WSS.Api_Registration_Login;

/*
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	PaymentPlans_DD test = new PaymentPlans_DD();  
    	test.details(Url_constant.stw_preproid_64); 
    	test.directdebits(Url_constant.stw_preproid_64);
    	
    	test.Bank(Url_constant.stw_preproid_64); 
    	
    //	test1.details_lite(); 
    //	test1.details(); 
    }
}
