package com.cognizant.Persistence;

public class CsvDatapersistence {
	
	
private String url ; 
	private String http_method;
	private String json_String;
	
	public CsvDatapersistence()
	{
		
	}
	public CsvDatapersistence(String url, String http_method, String json_string) {
		super();
		this.url = url;
		
		this.http_method = http_method;
		this.json_String = json_string; 
	}

	

	public String getJson_String() {
		return json_String;
	}
	public void setJson_String(String json_String) {
		this.json_String = json_String;
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public String getHttp_method() {
		return http_method;
	}

	public void setHttp_method(String http_method) {
		this.http_method = http_method;
	} 
	
	
	

}
