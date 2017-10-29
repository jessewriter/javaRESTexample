package com.jesse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import okio.BufferedSink;

public class RestConsumer {
	String distanceURI = "http://localhost:8082/DistanceREST/rest/distances/";
	{
		System.out.println("I am in a static instance block");
	}

	public List<CityToCityDistances> getDistancesShorterThan(int paramNum) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(distanceURI + paramNum).get().addHeader("cache-control", "no-cache")
				.build();
		Response response = client.newCall(request).execute();
		System.out.println("response=" + response);
		System.out.println("response.body.source=" + response.body().source());
		String resBody = response.body().string(); // NOTE can only ask 1 time!  Responses are STATELESS!!!
		System.out.println("response.body.source.string=" + resBody);
		JSONArray jo = new JSONArray(resBody);
		return  canGetBeansFromArray(jo);
	}

	public List<CityToCityDistances> getAll() throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(distanceURI).get().addHeader("cache-control", "no-cache").build();
		Response response = client.newCall(request).execute();
		JSONArray jo = new JSONArray(response.body().string());
		System.out.println("jo" + jo);
		return  canGetBeansFromArray(jo);
	}

	public JSONObject getJsonObj(String strIn) {
		JSONObject jObj = new JSONObject(strIn);
		return jObj;
	}

	public JSONArray getJSONArray(String strIn) {
		JSONObject jObj = new JSONObject(strIn);
		JSONArray jArray = new JSONArray(jObj.get("distances"));
		return jArray;
	}

	public CityToCityDistances createCityDistanceBean(JSONObject jsonObject) {
		String origin = (String) jsonObject.get("origin");
		String destination = (String) jsonObject.get("destination");
		int distance = (int) jsonObject.get("distance");
		return new CityToCityDistances(origin, destination, distance);
	}

	private List<CityToCityDistances> canGetBeansFromArray(JSONArray jObj) {
		if(jObj ==null) { return null; }
		List<CityToCityDistances> c2cd = new ArrayList<>();
		for (int i = 0; i < jObj.length(); i++) {
			JSONObject array_element = jObj.getJSONObject(i);
			String origin = array_element.getString("origin");
			String destination = array_element.getString("destination");
			int distance = array_element.getInt("distance");
			c2cd.add(new CityToCityDistances(origin, destination, distance));
		}
		return c2cd;
	}
	
	public String apacheClientExample() throws IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(distanceURI);
		HttpResponse response = client.execute(request);
		StringBuffer textView = new StringBuffer();
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String line = "";
		while ((line = rd.readLine()) != null) {
			textView.append(line);
		}
		rd.close();
		return textView.toString().replaceAll("\\s", "");
	}
	
	public String getResponseHeader() throws ClientProtocolException, IOException {
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(distanceURI);
		System.out.println(request.getRequestLine());
		CloseableHttpResponse response = client.execute(request);
		String answer = response.getStatusLine().toString().trim();
		return answer;
	}

	
	public Response updateDistance(int i, String string) throws IOException {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		  .url("http://localhost:8082/DistanceREST/rest/distances/updateDistance?distance=" +i +"&origin="+ string)
		  .put( new RequestBody() {
			@Override
			public void writeTo(BufferedSink sink) throws IOException {
			}
			@Override
			public com.squareup.okhttp.MediaType contentType() {
				return null;
			}  // Postman had put(null) but tomcat complains with error
		})
		  .addHeader("cache-control", "no-cache")
		  .build();
		Response response = client.newCall(request).execute();
		return response;
	}

}
