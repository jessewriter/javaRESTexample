package com.jesse;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CityToCityDistancesTest {

	private CityToCityDistances ctcd;

	@BeforeEach
	void setUp() throws Exception {
		ctcd = new CityToCityDistances("San Jose", "Las Vegas", 527);
	}

	@Test
	void canGetDistance() {
			assertEquals(527 , ctcd.getDistance());
	}

}
