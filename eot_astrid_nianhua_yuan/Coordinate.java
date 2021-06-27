package eot_astrid_nianhua_yuan;

public class Coordinate {
	String lon;
	String lat;
	
	public Coordinate(String lon, String lat) {
		this.lon = lon;
		this.lat = lat;
	}
	
	public String getLon() {
		// get longitude of the coordinate
		return lon;
	}

	public String getLat() {
		// get latitude of the coordinate
		return lat;
	}
	
}
