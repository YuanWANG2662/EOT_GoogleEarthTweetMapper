package eot_astrid_nianhua_yuan;

import java.io.IOException;

public class GoogleEarthLauncher {
	
	String googleEarthPath;
	
	public GoogleEarthLauncher() {
		
		//define the default google earth path in the constructor
		googleEarthPath = "C:/Program Files/Google/Google Earth Pro/client/googleearth.exe";					
			    
	}
	
	public void lauchGoogleEarth(String kmlPath) {
		try {
	    	//launch google earth and load the kml file
			Runtime.getRuntime().exec(new String[]{googleEarthPath,kmlPath});
		} catch (IOException e) {
			// exceptions produced by failed or interrupted I/O operations.
			e.printStackTrace();
		}
	}
	
	public void setGoogleEarthPath(String ggEarthpath) {
		// if not in the default installation folder, set the path of  google earth
		googleEarthPath = ggEarthpath;
	}
}
