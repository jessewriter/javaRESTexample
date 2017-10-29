package com.jesse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONObject;

@Path("/distances")
public class RestServerDistances {
	static private JSONObject distancesArray;
	static private List<CityToCityDistances> distObjList;
	static {
		// create CityToCityDistance Objects
		CityToCityDistances c2cD1 = new CityToCityDistances("Atlanta", "Seattle", 2635);
		CityToCityDistances c2cD2 = new CityToCityDistances("Los Angeles", "Nashville", 2004);
		CityToCityDistances c2cD3 = new CityToCityDistances("New York", "San Francisco", 2911);
		CityToCityDistances c2cD4 = new CityToCityDistances("Miami", "Boston", 1489);
		CityToCityDistances c2cD5 = new CityToCityDistances("San Jose", "Las Vegas", 527);
		distObjList = new ArrayList<CityToCityDistances>();
		distObjList.addAll(Arrays.asList(c2cD1, c2cD2, c2cD3, c2cD4, c2cD5));
		distancesArray = new JSONObject();
		distancesArray.put("distances", distObjList);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CityToCityDistances> showAllDistances() {
		return distObjList;
	}

	@Path("{c}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<CityToCityDistances> showDistancesLessThan(@PathParam("c") int c) {
		List<CityToCityDistances> listLessThanParam = new ArrayList<>();
		for (CityToCityDistances cityToCityDistances : distObjList) {
			if (cityToCityDistances.getDistance() < c) {
				listLessThanParam.add(cityToCityDistances);
			}
		}
		return listLessThanParam;
	}

	@Path("/updateDistance")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateDistanceForOrigin(@QueryParam("distance") int d, @QueryParam("origin") String origin) {
		System.out.println("d " +d + " orig "+ origin);
		for (CityToCityDistances cityToCityDistances : distObjList) {
			if (cityToCityDistances.getOrigin().equals(origin)) {
				cityToCityDistances.setDistance(d);
				return Response.ok(cityToCityDistances, MediaType.APPLICATION_JSON).build() ;
			}
		}
		return Response.notModified().build();
	}
	
}
