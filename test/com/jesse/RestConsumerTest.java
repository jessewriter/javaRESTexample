package com.jesse;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestConsumerTest {

	private  RestConsumer rc;

	@BeforeEach
	void setUp() throws Exception {
		rc = new RestConsumer();
	}

	@Test
	void JSONResponseHeader() throws ClientProtocolException, IOException {
System.out.println("get JSONResponse " + rc.getResponseHeader());
String expected = "HTTP/1.1 200";
String actual =  rc.getResponseHeader();
assertEquals(expected,actual);
	}
	
	
	@Test
	void getLessThanQuery() throws ClientProtocolException, IOException {
System.out.println("OKJsonResponse " + rc.getDistancesShorterThan(15000));
assertTrue(rc.getDistancesShorterThan(15000).size()==5);
assertTrue(rc.getDistancesShorterThan(1000).size()==1);
	}
	@Test
	void getAll() throws ClientProtocolException, IOException {
		System.out.println("get all" + rc.getAll());
assertTrue(rc.getAll().size()==5);
assertEquals( "Los Angeles" , rc.getAll().get(1).getOrigin());
assertTrue(rc.getAll().get(1) instanceof CityToCityDistances);
	}
	
	@Test
	void closeableApacheClient() throws ClientProtocolException, IOException {
System.out.println("apache client  " + rc.apacheClientExample());
String expected = "{\"origin\":\"Atlanta\",\"destination\":\"Seattle\",\"distance\":2635}";
String actual = rc.apacheClientExample();
assertTrue(actual.contains(expected));
	}
	
	@Test
	void canUpdateDistance() throws Exception {
		assertEquals(200, rc.updateDistance(2635, "Atlanta").code());  // 200 means success
	}
	

}
