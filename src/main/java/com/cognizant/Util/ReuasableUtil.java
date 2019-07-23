package com.cognizant.Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cognizant.Persistence.CsvDatapersistence;
import com.cognizant.Persistence.RestResponse;

public class ReuasableUtil {

	public static String random_email_generator(String acoount_number)
	{
		String email_char = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		
        StringBuilder stringbuilder = new StringBuilder();
        Random rnd = new Random();
        
        while(stringbuilder.length()< 6)
        {
        	int temp = (int)(rnd.nextFloat()* email_char.length()); 
        	stringbuilder.append(email_char.charAt(temp)); 
        }
        

		return stringbuilder.toString()+"+"+acoount_number+ "@gmail.com" ; 
	}
	
	public static JSONObject  stringtojson(String rest_Body)
	{
		JSONParser parser = new JSONParser();
		
		JSONObject object = null;
		try {
			object = (JSONObject) parser.parse(rest_Body.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return  object;
		
	}
	public List<CsvDatapersistence> csvReader (String file)
	{
		 List<CsvDatapersistence> lines = new ArrayList<CsvDatapersistence>();
		
		try {
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        
	       
	        String line ;
	        while ((line = br.readLine()) != null )
	        {
	        		
	        		String [] fields = line.split(","); 	 /// need to split the data from the csv 
	        			
	        		String url = fields[0]; 
	        		
	        		String url_Method = fields[1];
	        		String json_string = fields[2];
	        	
	        		CsvDatapersistence data = new CsvDatapersistence(url, url_Method, json_string);
	        		
	        		lines.add(data); 
	        }
	        br.close();
	        
	        
	    }
	    catch (Exception e )
	    {
	        e.printStackTrace();
	        
	    }
		
		
		
		return lines; 
	}
	
	public RestResponse Add_data_to_pogo(HttpResponse response)
	{
		StringBuffer responseString = new StringBuffer();
		HashMap<String, String> data_Header = new  HashMap<String, String>();
		RestResponse restresponse = new RestResponse (); 
		BufferedReader rd;
		try {
			rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		
		String line = ""; 
		while((line = rd.readLine())!= null)
		{
			responseString.append(line);
		}
		
		System.out.println(responseString.toString());
		restresponse.setResponseCode(response.getStatusLine().getStatusCode());
		restresponse.setResponseBody(responseString.toString());
		restresponse.setResponseMessage(response.getStatusLine().getReasonPhrase());
		Header[] add_headers = response.getAllHeaders();
		for(Header h :add_headers )
		{
			data_Header.put(h.getName(), h.getValue()); 
		}
		
		restresponse.setHeaders(data_Header);
		} catch (UnsupportedOperationException | IOException e) {
			e.printStackTrace();
		}
		
		return restresponse; 
	}
}
