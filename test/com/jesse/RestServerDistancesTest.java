package com.jesse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RestServerDistancesTest {

	private RestServerDistances rsd;

	@BeforeEach
	void setUp() throws Exception {
		rsd = new RestServerDistances();
	}

	@Test
	void test() {
	System.out.println(rsd.showAllDistances().toString());
	}
	
	@Test
	void canShowDistancesLessThan() throws Exception {
		assertEquals(1,rsd.showDistancesLessThan(1000).size());
	}
	
	@Test
	void canShowAllDistances() throws Exception {
		int actual = rsd.showAllDistances().size();
		assertEquals(5,actual);
	}
	
	@Test
	void updateDistance() throws Exception {
		//304 Not modified
		// 200 created
		System.out.println(rsd.updateDistanceForOrigin(99, "Atlanta").getStatus());
		assertEquals(200, rsd.updateDistanceForOrigin(99, "Atlanta").getStatus());
	}

}
