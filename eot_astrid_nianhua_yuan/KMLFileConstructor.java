package eot_astrid_nianhua_yuan;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class KMLFileConstructor {
	String kmlHeader;
	String kmlRear;
	String placeMarkers;
	String placeMakers_full;
	String groundOverlays;
	String groundOverlays_full;
	
	public KMLFileConstructor() {
		//define the header of the kml file
		kmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
	    		+ "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\r\n"
	    		+ "  <Document>\r\n"; 
		//define the rear of the kml file
		kmlRear = "  </Document>\r\n"
	    		+ "</kml>";
		//initialize the place markers and ground overlays
		placeMarkers = "";
		groundOverlays = "";
		
	}
	
	public void addPlaceMarker(String i, String coordinates, String tweet, String userID, String creationTime) {
	    // this method generates <PlaceMark> tag using the parameters including coordinates and attributes
		String placeMarker = "	<Placemark>\r\n"
	    		+ "		<name>Tweet no."+i+"</name>\r\n"
	    		//+ "	<description>\r\n"
	    		//+ "		Tweet: "+tweet+ "\r\n\t\t Created by: "+ userID +"\r\n\t\t At " + creationTime
	    		//+ "	</description>\r\n"
	    		+ "		<ExtendedData>\n"		//Visualize the columns ¡°tweet¡± and ¡°created_at¡± in the <ExtendedData> tag
                + "			<Data name=\"Tweet\"> " + "<value>" + tweet + "</value> </Data>\n"
                + "			<Data name=\"Created by\"> " + "<value> UserID:" + userID + "</value> </Data>\n"
                + "			<Data name=\"Created at\" >" + "<value>" + creationTime + "</value> </Data>\n"
                + "		</ExtendedData>\n"   
	    		+ "		<Point>\r\n"
	    		+ "			<coordinates>"+coordinates+"</coordinates> \r\n"
	    		+ "			<altitudeMode>relativeToGround</altitudeMode>\r\n"
	    		+ "			<extrude>1</extrude>\r\n"	//extrude the point using the attribute value
	    		+ "		</Point>\r\n"
	    		+ "		<TimeStamp>\r\n"
	    		+ "			<when>"+creationTime+"</when>\r\n"
	    		+ "		</TimeStamp>\r\n"
	    		+ "	</Placemark>\r\n";
	    placeMarkers += placeMarker;
	}

	public void addGroundOverlay(String id, String name, String color, String[] LatLonBox, String href) {
		// this method generate <GroundOverlay> tag using the parameters including name, transparency, bbox, image path
		String groundOverlay = "	<GroundOverlay id=\""+id+"\">\r\n"
				+ "		<name>"+name+"</name>\r\n"	
				+"		<color>"+color+"</color>\r\n" // transparency is defined in the 'color' string
				+ "		<Icon>\r\n"
				+ "			<href>"+href+"</href>\r\n"
				+ "		</Icon>\r\n"
				+ "		<LatLonBox>\r\n"
				+ "			<south>"+LatLonBox[0]+"</south>\r\n"
				+ "			<west>"+LatLonBox[1]+"</west>\r\n"
				+ "			<north>"+LatLonBox[2]+"</north>\r\n"
				+ "			<east>"+LatLonBox[3]+"</east>\r\n"
				+ "		</LatLonBox>\r\n"
				+ "	</GroundOverlay>\r\n";
				
		groundOverlays += groundOverlay;
	}
	
	public void writeKmlFile(String kmlPath) {
		
		String kmlString = kmlHeader + groundOverlays + placeMarkers + kmlRear;
		
		//declare a new file
		File file = new File(kmlPath);		
		try {
			// creates the file
			file.createNewFile();
			// creates a FileWriter Object
			FileWriter writer = new FileWriter(file);		
			// Writes the content to the file
			writer.write(kmlString); 
		    writer.flush();
		    writer.close();
		    System.out.println("The kml file was successfully written to "+ kmlPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		      
		
	    
	}
	
	
}
