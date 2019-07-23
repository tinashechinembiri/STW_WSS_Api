package com.cognizant.WSS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpCoreContext;

import com.cognizant.ConstantFile.Credential_Constant;
import com.cognizant.ConstantFile.Url_constant;
import com.cognizant.Persistence.RestResponse;
import com.cognizant.Util.ReuasableUtil;

public class Api_Registration_Login {
	/*******************************************************8
	 * https://gist.github.com/justinedelson/10878245
	 * *********************************************************8
	 * */
	private HttpClient client; 
	Date expires = null ; 
	String cooki_1 = ""; 
	HashMap<String, String> data_Header_1 = new  HashMap<String, String>();
	HttpCoreContext localContext ;
    BasicCookieStore cookieStore; 
    HttpClientContext httpClientContext;  	
    String main_url = Url_constant.stw_preproid_64; 
    
    
	public Api_Registration_Login()
	{
		
			cookieStore	 = new BasicCookieStore();
			localContext= new HttpCoreContext();
			
			httpClientContext = HttpClientContext.create();
			
			httpClientContext.setCookieStore(cookieStore);
		    this.client = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).setDefaultRequestConfig(RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD).build()).setDefaultCookieStore(cookieStore).build();
		    
		  //  Login();
	}
	
	public RestResponse postRequest(String url)
	{
		HttpPost post = new  HttpPost(url); 
		RestResponse restresponse = null ;
		HttpClientContext httpClientContext = new HttpClientContext(); 
		ReuasableUtil reuse = new ReuasableUtil();
	
		CloseableHttpResponse response = null;
	
		 try {
			 post.setHeader("Accept", "application/json");
			 post.setHeader("Content-type", "application/json"); 
			 response = 	(CloseableHttpResponse) this.client.execute(post, httpClientContext); 
						
			if (response.getStatusLine().getStatusCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			
			
			restresponse = reuse.Add_data_to_pogo(response); 
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 finally {
			    try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		return restresponse; 
	}
	
	public RestResponse postRequest(String  url, String payload )
	{
		HttpPost post = new  HttpPost(url); 
		RestResponse restresponse = new RestResponse (); 		
		ReuasableUtil reuse = new ReuasableUtil();
		CloseableHttpResponse response = null;
	
	
		 try {
			 
			StringEntity entity = new StringEntity(payload);
		
			post.setEntity(entity);
			post.setHeader("Accept", "application/json");
			post.setHeader("Content-type", "application/json"); 
			response = 	(CloseableHttpResponse) this.client.execute(post,httpClientContext); 
				System.out.println(response.getStatusLine().getStatusCode());		
			if (response.getStatusLine().getStatusCode() != 200)
			{
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatusLine().getStatusCode());
			}
			
			restresponse = reuse.Add_data_to_pogo(response); 
			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 finally {
			    try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		
		return restresponse; 
	}
	
	public RestResponse Login()
	{
		 
		HttpPost post = new  HttpPost(main_url+"/content/stw/en/homepage/login/j_security_check");
		StringBuffer responseString = new StringBuffer();
		HttpResponse response;
		RestResponse restresponse = new RestResponse (); 
		
		 List<NameValuePair> namevaluepair = new ArrayList<NameValuePair>();
		 namevaluepair.add(new BasicNameValuePair("j_username", Credential_Constant.username));
		 namevaluepair.add(new BasicNameValuePair("j_password",Credential_Constant.password));
	        try {
	        	 post.setEntity(new UrlEncodedFormEntity(namevaluepair)); 
	        	
	             response = this.client.execute(post,localContext);     
	          // Print out the response message
	    		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
				String line = ""; 
				while((line = rd.readLine())!= null)
				{
					responseString.append(line);
				}
				
				restresponse.setResponseCode(response.getStatusLine().getStatusCode());
				restresponse.setResponseBody(responseString.toString());
				restresponse.setResponseMessage(response.getStatusLine().getReasonPhrase());
				Header[] add_headers = response.getAllHeaders();
			
				for(Header h :add_headers )
				{
					data_Header_1.put(h.getName(), h.getValue());	
				}
				restresponse.setHeaders(data_Header_1);
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		
		return restresponse; 
	}

	public RestResponse getRequest(String url)
	{
		StringBuilder str = new StringBuilder();
		
		System.out.println(str.toString());
		HttpGet request = new HttpGet(url);
		request.setHeader("Accept", "application/json");
		request.setHeader("Content-type", "application/json"); 
		RestResponse restresponse = null;  
		CloseableHttpResponse response = null;
		ReuasableUtil reuse = new ReuasableUtil();
		
		try {	
			response = (CloseableHttpResponse) this.client.execute(request);
				
			restresponse = reuse.Add_data_to_pogo(response); 		
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		 finally {
			    try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		return restresponse; 
	}
	public RestResponse getRequest(String url, String payload)
	{
		ReuasableUtil reuse = new ReuasableUtil();
		HttpGet request = new HttpGet(url);
		HttpResponse response;
		RestResponse rest_api_repsone;

		try {
		//	StringEntity entity = new StringEntity(payload);
	
			response = this.client.execute(request, localContext);
			 	
			rest_api_repsone = reuse.Add_data_to_pogo(response); 
			return rest_api_repsone; 
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
		return null; 
	}
	
	public RestResponse putRequest (String url, String payload )
	{
		 HttpPut httpPut = new HttpPut(url);
		 RestResponse restresponse = null; 
		 CloseableHttpResponse  response = null;
		 ReuasableUtil reuse = new ReuasableUtil();	
		
		 try {
			 
			httpPut.setEntity(new StringEntity(payload));
			 

			response = (CloseableHttpResponse) this.client.execute(httpPut);
			restresponse = reuse.Add_data_to_pogo(response); 
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
		
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		 finally {
			    try {
					response.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		return restresponse; 
	}
}
