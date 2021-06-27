package eot_astrid_nianhua_yuan;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.geotools.ows.ServiceException;
import org.geotools.ows.wms.WebMapServer;
import org.geotools.ows.wms.request.GetMapRequest;
import org.geotools.ows.wms.response.GetMapResponse;


public class WMSConnector {
	
	String wmsSeriveUrl;
	String wmsVersion;
	String layerName;
	String layerStyle;
	String srs;
	String bbox;
	boolean isTransparent;
	String imgWidth;
	String imgHeight;
	String imgFormat;
	String imgPath;
	
	public void setWMSUrl(String wmsSeriveUrl) {
		this.wmsSeriveUrl = wmsSeriveUrl;
	}
	
	public void setGetMapRequestParams(String layerName, String layerStyle, String wmsVersion, String srs,
			String bbox, boolean tranparency, String imgWidth, String imgHeight, String imgFormat) {
		this.layerName = layerName;
		this.layerStyle = layerStyle;
		this.wmsVersion = wmsVersion;
		this.srs = srs;
		this.bbox = bbox;
		this.isTransparent = tranparency;
		this.imgWidth = imgWidth;
		this.imgHeight = imgHeight;
		this.imgFormat = imgFormat;		
	}
	
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	
	public void getWMSMap() {
		//this method connect to the WMS service and write the response of the GetMap request to the hard drive 
		
		//code reference:
		//https://docs.geotools.org/latest/userguide/extension/wms/wms.html
		
		try {
			// create the url object for the wms service
			URL wmsGetCapabilitiesURL = new URL(wmsSeriveUrl);
			//construct a WebMapServer object with the specified url
			WebMapServer wms = new WebMapServer(wmsGetCapabilitiesURL);
			
			//create GetMap Request
			GetMapRequest request = wms.createGetMapRequest();
			
			//configure the parameters for the GetMapRequest
			request.addLayer(layerName, layerStyle);
			request.setVersion(wmsVersion); 
			request.setSRS(srs);
			request.setBBox(bbox);
			request.setTransparent(isTransparent);
			request.setDimensions(imgWidth, imgHeight); 
			request.setFormat(imgFormat);
			
			//when layer successfully loaded
			System.out.println("layer '"+ layerName +"' loaded");
		
			//get and save the map as an image
			GetMapResponse response = wms.issueRequest(request);
			BufferedImage image = ImageIO.read(response.getInputStream());
			//save the image on the local machine
			ImageIO.write(image, "png", new File(imgPath));
			//image was generated 
			System.out.println("The image was successfully written to "+ imgPath);
			
			
		} catch (MalformedURLException e) {
			// Thrown to indicate that a malformed URL has occurred.
			//Either no legal protocol could be found in a specification string or the string could not be parsed.
			e.printStackTrace();
		} catch (ServiceException e) {
			//There was an error communicating with the server
			//For example, the server is down
			e.printStackTrace();
		} catch (IOException e) {
			//The server returned a ServiceException (unusual in this case)
			e.printStackTrace();
		}//exception handling
		
	}
	
}//class WMSConnector
