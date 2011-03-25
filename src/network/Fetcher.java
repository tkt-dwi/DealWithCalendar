package network;

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
	private Scanner reader; //reader for the content
	
	/**
	 * Constructor to initialize the URL and reader
	 * 
	 * @param _URL The URL to be read
	 */
	public Fetcher(String _URL)
	{
		try
		{
			address = new URL(_URL);
			reader = new Scanner(address.openStream());
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
		
		//TODO: parsing
	}
}
