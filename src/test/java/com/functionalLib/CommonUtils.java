package com.functionalLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommonUtils {
	protected String uri;
	protected String getReq;
	protected String postReq;

	public void propFiles() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\ConfigFiles\\Config.properties"));
		uri = properties.getProperty("BaseURI");
		getReq = properties.getProperty("getReq");
		postReq = properties.getProperty("postReq");

	}


	public CloseableHttpResponse getRequest(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		return httpClient.execute(new HttpGet(url));
	}

	public CloseableHttpResponse postRequest(String url, String entity, Map<String,String> header) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setEntity(new StringEntity(entity));
		for (Map.Entry<String, String> entry : header.entrySet()) {
			post.addHeader(entry.getKey(),entry.getValue());
		}
		return httpClient.execute(post);

	}

	public String readJson() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		FileReader reader = new FileReader(System.getProperty("user.dir")+"\\src\\test\\resources\\Json\\Sample.json");
		Object object = parser.parse(reader);
		JSONObject jsonObj = (JSONObject) object;
		String entityString = jsonObj.toString();
		return entityString;
	}


}
