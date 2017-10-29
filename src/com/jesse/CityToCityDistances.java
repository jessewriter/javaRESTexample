package com.jesse;

import javax.ws.rs.Produces;

@Produces("application/json")
public class CityToCityDistances {

	private String origin;
	private String destination;
	private int distance;

	public CityToCityDistances(String origin, String destination, int distance) {
		this.origin = origin;
		this.destination = destination;
		this.distance = distance;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	@Override
	public String toString() {
		return "CityToCityDistances [origin=" + origin + ", destination=" + destination + ", distance=" + distance
				+ "]";
	}

}
