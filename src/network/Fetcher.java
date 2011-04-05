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
	 * Parse the content of the site
	 */
	public void read()
	{
		if (!isReady())
			return;
		
		JSONObject file = (JSONObject) JSONValue.parse(reader);
                //System.out.println(asd.get(0));
                //JSONArray array = (JSONArray) asd;
                //System.out.println(array.get(0));
                Set tree = file.keySet();
                Object[] hurr = tree.toArray();
                if(debug) for(int i=0;i<hurr.length;i++)
                    System.out.println(hurr[i]);
                JSONArray foo = (JSONArray) JSONValue.parse(file.get("courses").toString());
		String block;
		String[] split, split2, split3;
		ArrayList<dealwithcalendar.Course> list = new ArrayList<dealwithcalendar.Course>();
		Calendar start, end;
		String name;
                for(int i=0;i<foo.size();i++)
                {
		    if(debug) System.out.println("i: " + i + " out of " + foo.size());
                    block = foo.get(i).toString();
                    block = block.substring(1, block.length()-1);
		    split = block.split(",");
		    start = Calendar.getInstance();
		    end = Calendar.getInstance();
		    name = "";

		    for(int j=0;j<split.length;j++)
		    {
			if(debug) System.out.println("j: " + j + " out of " + split.length);
			split2 = split[j].split(":");
			if(split2.length > 1)
			{
			    if(split2[0].equals("\"course\""))
			    {
				name = split2[1];
				name = name.substring(1, name.length()-1);
			    }
			    else if(split2[0].equals("\"start_date\""))
			    {
				 split2[1] = split2[1].substring(1, split2[1].length()-1);
				 split3 = split2[1].split("-");
				 if(split3.length > 2)
				 {
				     try
				     {
					     start.set(Calendar.YEAR, new Integer(split3[0]));
					     start.set(Calendar.MONTH, new Integer(split3[1]));
					     start.set(Calendar.DAY_OF_MONTH, new Integer(split3[2]));
				     } catch (NumberFormatException NFEx)
				     {
					 System.err.println("NumberFormatException parsing start date\n" + block);
				     }
				 }
			    }
			    else if(split2.equals("\"end_date\""))
			    {
				 split2[1] = split2[1].substring(1, split2[1].length()-1);
				 split3 = split2[1].split("-");
				 if(split3.length > 2)
				 {
				     try
				     {
					     end.set(Calendar.YEAR, new Integer(split3[0]));
					     end.set(Calendar.MONTH, new Integer(split3[1]));
					     end.set(Calendar.DAY_OF_MONTH, new Integer(split3[2]));
				     } catch (NumberFormatException NFEx)
				     {
					 System.err.println("NumberFormatException parsing end date\n" + block);
				     }
				 }
			    }
			}
		    }

		    new dealwithcalendar.Main().addCourse(start, end, name, -1);
		    if(debug) System.out.println("Added " + name + "!");
                }
	}
}
