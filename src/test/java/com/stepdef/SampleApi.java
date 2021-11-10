package com.stepdef;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import com.config.ConfigFiles;
import com.functionalLib.CommonUtils;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class SampleApi extends CommonUtils {
	int responseCode ;
	String responseBody;
	ConfigFiles files = new ConfigFiles();
	
	
	@When("User enters the get request Url")
	public void user_enters_the_get_request_Url() throws ClientProtocolException, IOException {
		propFiles();
		String url = uri + getReq;
		System.out.println(url);
		CloseableHttpResponse httpResponse = getRequest(url);
		responseCode = httpResponse.getStatusLine().getStatusCode();
		responseBody = EntityUtils.toString(httpResponse.getEntity());
		Header[] allHeaders = httpResponse.getAllHeaders();
		System.out.println(responseCode);
		System.out.println(responseBody);
		HashMap<String, String> allHeader = new HashMap<>();
		for (Header header : allHeaders) {
			allHeader.put(header.getName(), header.getValue());
		}
		Set<Entry<String,String>> entrySet = allHeader.entrySet();
		for (Entry<String, String> entry : entrySet) {
			System.out.println(entry);
		}
	}

	@Then("User validate the response code recieved")
	public void user_validate_the_response_code_recieved() {
		Assert.assertEquals(200, responseCode);
	}

	@When("User enters the post request Url")
	public void user_enters_the_post_request_Url() throws IOException, ParseException {
		propFiles();
		String url = uri + postReq;
		String entityString = readJson();
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("Content-Type", "application/json");
		CloseableHttpResponse postRequest = postRequest(url, entityString, map);
		responseCode = postRequest.getStatusLine().getStatusCode();
		responseBody = EntityUtils.toString(postRequest.getEntity());
		System.out.println(responseCode);
		System.out.println(responseBody);
	    
	}

	@Then("User validate resource is posted with response code")
	public void user_validate_resource_is_posted_with_response_code() {
	    Assert.assertEquals(200, responseCode);
	}






}
