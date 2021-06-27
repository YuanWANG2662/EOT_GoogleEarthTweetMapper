package eot_astrid_nianhua_yuan;

import java.util.List;


public class GoogleEarthTweetMapper {

	public static void main(String[] args) {
		
		String wmsSeriveUrl = "http://maps.heigit.org/osm-wms/service?service=wms";
		String wmsVersion = "1.3.0";
		String layerName = "osm_auto:all";
		String layerStyle = "default";
		String srs = "EPSG:4326";
		String bbox = "42.32,-71.13,42.42,-71.03";
		boolean isTransparent = true;
		String imgWidth = "1000";
		String imgHeight = "1000";
		String imgFormat = "image/png";
		String imgPath = "D:\\eclipse\\java\\workspace\\unit4\\src\\map.png";
		String kmlPath = "D:\\eclipse\\java\\workspace\\unit4\\src\\TweetsMap.kml";
		String tweetsPath = "http://www.berndresch.com/work/twitter.csv";
		
		//----------------------------------------------------------------------------------
		
		// create a WMSConnector object
		WMSConnector wmsConn = new WMSConnector();
		//set the url of the WMS service
		wmsConn.setWMSUrl(wmsSeriveUrl);
		//set the parameters of the GetMap request
		wmsConn.setGetMapRequestParams(layerName, layerStyle, wmsVersion, srs, bbox
				, isTransparent, imgWidth, imgHeight, imgFormat);
		//set the path of the image
		wmsConn.setImgPath(imgPath);
		//connect to the WMS service and write the response of the GetMap request to the imgPath
		wmsConn.getWMSMap();
		
		//----------------------------------------------------------------------------------
		
		// create a KMLFileConstructor object
		KMLFileConstructor kml_constrct = new KMLFileConstructor();
		// split the bounding box string into the LatLonBox String Array
		String[] LatLonBox = bbox.split(",");
		//set the opacity as 30%
		String color = "4dffffff"; //the color is coded as aabbggrr, where aa=alpha (00 to ff)
		//add the ground overlay to the kml file
		kml_constrct.addGroundOverlay("grdOvl1", "OSM", color, LatLonBox, imgPath);
		
		
		//----------------------------------------------------------------------------------
		TweetsParser p = new TweetsParser();
		p.parseTweetsData(tweetsPath);
		List<Coordinate> coords = p.getCoords();
		List<String> tweets = p.getTweets();
		List<String> userIDs = p.getUserIDs();
		List<String> creation_time = p.getCreationTime();
	    
		for(int i = 0; i < coords.size(); i++) {
			String coordinates = coords.get(i).getLon() + "," + coords.get(i).getLat();
			String tweet = tweets.get(i);
			String userID = userIDs.get(i);
			String creationTime = creation_time.get(i);
			kml_constrct.addPlaceMarker(Integer.toString(i+1), coordinates, tweet, userID, creationTime);
		}
		
		//write the kml file to the kmlPath
		kml_constrct.writeKmlFile(kmlPath);
		
		//----------------------------------------------------------------------------------
		
		//create a GoogleEarthLauncher object
		//the path of the google earth will be set by default in the constructor function
		GoogleEarthLauncher launcher = new GoogleEarthLauncher();
		// launch google earth and load the kml file
		launcher.lauchGoogleEarth(kmlPath);
		
		
		
		
	}

}
