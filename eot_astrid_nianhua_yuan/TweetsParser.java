package eot_astrid_nianhua_yuan;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TweetsParser {
	
	List<Coordinate> coords;
	List<String> tweets;
	List<String> userIDs;
	List<String> creation_time;
	
	public TweetsParser() {
		//initialize Lists
		coords = new ArrayList<Coordinate>();
		tweets = new ArrayList<String>();
		userIDs = new ArrayList<String>();
		creation_time = new ArrayList<String>();
	}

	public void parseTweetsData(String tweetsPath) {
		try {
			URL tweetsURL = new URL(tweetsPath);
			Scanner s = new Scanner(tweetsURL.openStream());
			int i = -1;
			while(s.hasNext()) {				
				//get next line
				String row = s.nextLine();
				//skip the first row
				if (i==-1) {
					i++;
					continue;
				}
				//split the String line
				String[] row_data =  row.split(";");
				//get the fields of interest
				String lon = row_data[1];
				String lat = row_data[2];
				String tweet = row_data[5];
				String time = row_data[6];
				String id = row_data[7];
				//create a new Coordinate object with longitude and latitude
				Coordinate coord= new Coordinate(lon,lat);
				//format of kml dateTime (YYYY-MM-DDThh:mm:sszzzzzz)
				String dateTime = time.replace(" ", "T")+":00";
				
				coords.add(coord);
				tweets.add(tweet);
				userIDs.add(id);				
				creation_time.add(dateTime);	
				System.out.println(coords.get(i).lon+","+coords.get(i).lat+","+tweets.get(i)+","+creation_time.get(i)+","+userIDs.get(i));
			    i++;
			}
			s.close();
			System.out.println("Tweets data parsed successfully.");
		} catch (MalformedURLException e) {
			// Either no legal protocol could be found in a specification string or the string could not be parsed.
			e.printStackTrace();
		} catch (IOException e) {
			// exceptions produced by failed or interrupted I/O operations
			e.printStackTrace();
		}
		
	}
	
	public List<Coordinate> getCoords() {
		return coords;
	}
	
	public List<String> getTweets(){
		return tweets;
	}
	
	public List<String> getUserIDs(){
		return userIDs;
	}
	
	public List<String> getCreationTime(){
		return creation_time;
	}
}

