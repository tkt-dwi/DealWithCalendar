package network;

import org.json.simple.*;
import org.json.simple.parser.*;
import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Class for fetching the JSON content and parsing it. Should be able to isolate the blocks of data and create/modify course objects as needed
 * 
 * @author Deal With It Productions
 * @version 0.1
 */
public class Fetcher
{
    private URL address; //the address to be read
    private InputStreamReader reader; //reader for the content
    private boolean debug; // super duper debug madness

    /**
     * Constructor to initialize the URL and reader
     *
     * @param _URL The URL to be read
     */
    public Fetcher(String _URL)
    {
	debug = false;

	if(debug) System.out.println("foo 1");
	try
	{
	    if(debug) System.out.println("foo 2");
	    address = new URL(_URL); //create URL
	    reader = new InputStreamReader(address.openStream()); //read from it
	} catch (MalformedURLException MalfURL)
	{	//bad URL, typo
	    System.err.println("Error establishing address " + _URL + "; malformed URL");
	} catch (IOException IOE)
	{	//misc reader exception
	    System.err.println("Error opening reader for " + _URL);
	}

    }
	
	/**
	 * Check if the Fetcher is ready
	 * @return Whether the Fetcher is ready
	 */
	private boolean isReady()
	{	//if everything is initialized
		return address != null && reader != null;
	}
	
	/**
	 * Parse the content of the site and add via the dealwithcalendar.Main class
	 */
	public void read()
	{
		if (!isReady()) //if we encounter propelms
			return;
		
		JSONObject file = (JSONObject) JSONValue.parse(reader); //read the entire interface
                Set set = file.keySet(); //get the interface in Set form
                Object[] setArray  = set.toArray(); //then as an array
                if(debug) for(int i=0;i<setArray.length;i++)  //Debug listing all the objects
                    System.out.println(setArray[i]);
                JSONArray chunks = (JSONArray) JSONValue.parse(file.get("courses").toString()); //go into the courses, split them into individual course chunks
		String block; //parsing tool
		String[] split, split2, split3; //parsing tools
		dealwithcalendar.Main main = new dealwithcalendar.Main(); //to add Courses with
		Calendar start, end; //start and end dates
		String name; //course name
                for(int i=0;i<chunks.size();i++) //for every course chunk
                {
		    if(debug) System.out.println("i: " + (i+1) + " out of " + chunks.size());
                    block = chunks.get(i).toString(); //get the individual chunk as String
                    block = block.substring(1, block.length()-1); //chop off the brackets
		    split = block.split(","); //get the descriptors as an array
		    start = Calendar.getInstance(); //INSTANS PLS
		    end = Calendar.getInstance(); //MOAR INSTANS PLS
		    name = ""; //init just to be safe and avoid null wizardry

		    for(int j=0;j<split.length;j++) //for every descriptor in the individual chunk
		    {
			if(debug) System.out.println("j: " + (j+1) + " out of " + split.length);
			split2 = split[j].split(":"); //split into key and value
			if(split2.length > 1) //if there are actually a key and value
			{
			    if(split2[0].equals("\"course\"")) //if the key is "course"
			    {
				name = split2[1]; //then the value is the name
				name = name.substring(1, name.length()-1); //and chop off the ""
			    }
			    else if(split2[0].equals("\"start_date\"")) //or it might be the start date
			    {
				 split2[1] = split2[1].substring(1, split2[1].length()-1); //chop off the ""
				 split3 = split2[1].split("-"); //get the three vals (year-month-day) as array
				 if(split3.length > 2) //if there are actually three vals
				 {
				     try
				     {
					     start.set(Calendar.YEAR, new Integer(split3[0])); //set the year as the year value
					     start.set(Calendar.MONTH, new Integer(split3[1])); //set the month as the month value
					     start.set(Calendar.DAY_OF_MONTH, new Integer(split3[2])); //set the day as the day value
				     } catch (NumberFormatException NFEx) //awwwww ssshhhhit man
				     {
					 System.err.println("NumberFormatException parsing start date\n" + block);
				     }
				 }
			    }
			    else if(split2.equals("\"end_date\"")) //hey, it might be an end date
			    {
				 split2[1] = split2[1].substring(1, split2[1].length()-1); //chop off the ""
				 split3 = split2[1].split("-"); //{year,month,day}
				 if(split3.length > 2) //if the three vals are present
				 {
				     try
				     {
					     end.set(Calendar.YEAR, new Integer(split3[0])); //year as year
					     end.set(Calendar.MONTH, new Integer(split3[1])); //month as month
					     end.set(Calendar.DAY_OF_MONTH, new Integer(split3[2])); //day as day
				     } catch (NumberFormatException NFEx) //WHAT HAS SCIENCE DONE?
				     {
					 System.err.println("NumberFormatException parsing end date\n" + block);
				     }
				 }
			    }
			}
		    }

		    main.addCourse(start, end, name, -1); //Add the course through the main class
		    if(debug) System.out.println("Added " + name + "!");
                }
	}
}
