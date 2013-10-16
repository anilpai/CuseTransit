package com.cis600;

import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.os.StrictMode;
import android.util.Log;

public class ParseXML {
	private final static String TAG = "ParseXML";

	/** Called when the activity is first created. */
	private ArrayList<Double> LatList = new ArrayList<Double>();
	private ArrayList<Double> LonList = new ArrayList<Double>();

	private ArrayList<Double> LatListStops = new ArrayList<Double>();
	private ArrayList<Double> LonListStops = new ArrayList<Double>();
	
	private ArrayList<String> Names = new ArrayList<String>();
	private ArrayList<Integer> BusIds = new ArrayList<Integer>();
	
	private ArrayList<String> sNames = new ArrayList<String>();
	private ArrayList<Integer> sIds = new ArrayList<Integer>();
	
	private String prediction;
	
	public ArrayList<Double> getlatlist()
	{
		return LatList;
	}
	
	public ArrayList<Double> getlonlist()
	{
		return LonList;
	}
	
	public void setlatlist(ArrayList<Double> latitude)
	{
		LatList = latitude;
	}
	
	public void setlonlist(ArrayList<Double> longitude)
	{
		LonList = longitude;
	}
	
	public void setlatlistStops(ArrayList<Double> latitude)
	{
		LatListStops = latitude;
	}
	
	public void setlonlistStops(ArrayList<Double> longitude)
	{
		LonListStops = longitude;
	}
	
	public ArrayList<Double> getlatlistStops()
	{
		return LatListStops;
	}
	
	public ArrayList<Double> getlonlistStops()
	{
		return LonListStops;
	}
	
	public ArrayList<String> getNames()
	{
		return Names;
	}
	
	public void setNames(ArrayList<String> input)
	{
		Names = input;
	}

	public ArrayList<Integer> getbIds()
	{
		return BusIds;
	}
	
	public void setbIds(ArrayList<Integer> input)
	{
		BusIds = input;
	}
	
	public ArrayList<String> getsNames()
	{
		return sNames;
	}
	
	public void setsNames(ArrayList<String> input)
	{
		sNames = input;
	}

	public ArrayList<Integer> getsIds()
	{
		return sIds;
	}
	
	public void setsIds(ArrayList<Integer> input)
	{
		sIds = input;
	}
	
	public String getpreds()
	{
		return prediction;
	}
	
	public void setpreds(String input)
	{
		prediction = input;
	}
	
	public void parse(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 
		try {
			URL url = new URL(link);

			// get new SAXParser
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();

			// get the XMLReader from SAXParser
			XMLReader xr = sp.getXMLReader();

			// get new ContentHandler for XMLReader
			SAXEventHandler sh = new SAXEventHandler();
			xr.setContentHandler(sh);

			// parse XML data from URL
			xr.parse(new InputSource(url.openStream()));

			// Our ExampleHandler now provides the parsed data to us. */
			ParsedXMLData xmlData = sh.getParsedData();
			setlatlist(xmlData.getLatList());
			setlonlist(xmlData.getLonList());	
			// display results
		} catch (Exception e) {	
			Log.e(TAG, "Hit a problem", e);
		}
	}
		public void parseStops(String link) {

			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy); 
			try {
				URL url = new URL(link);

				// get new SAXParser
				SAXParserFactory spf = SAXParserFactory.newInstance();
				SAXParser sp = spf.newSAXParser();

				// get the XMLReader from SAXParser
				XMLReader xr = sp.getXMLReader();

				// get new ContentHandler for XMLReader
				SAXEventHandler sh = new SAXEventHandler();
				xr.setContentHandler(sh);

				// parse XML data from URL
				xr.parse(new InputSource(url.openStream()));

				// Our ExampleHandler now provides the parsed data to us. */
				ParsedStopData StopData = sh.getParsedStopData();
				setlatlistStops(StopData.getLatList());
				setlonlistStops(StopData.getLonList());	
				// display results
			} catch (Exception e) {	
				Log.e(TAG, "Hit a problem", e);
			}
			
		}
			public void parseNames(String link) {

				StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
				StrictMode.setThreadPolicy(policy); 
				try {
					URL url = new URL(link);

					// get new SAXParser
					SAXParserFactory spf = SAXParserFactory.newInstance();
					SAXParser sp = spf.newSAXParser();

					// get the XMLReader from SAXParser
					XMLReader xr = sp.getXMLReader();

					// get new ContentHandler for XMLReader
					SAXEventHandler sh = new SAXEventHandler();
					xr.setContentHandler(sh);

					// parse XML data from URL
					xr.parse(new InputSource(url.openStream()));

					// Our ExampleHandler now provides the parsed data to us. */
					BusName nm = sh.getBusName();
					setNames(nm.getNames());
					setbIds(nm.getIds());
					// display results
				} catch (Exception e) {	
					Log.e(TAG, "Hit a problem", e);
				}
			}
				public void parseNews(String link) {

					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					StrictMode.setThreadPolicy(policy); 
					try {
						URL url = new URL(link);

						// get new SAXParser
						SAXParserFactory spf = SAXParserFactory.newInstance();
						SAXParser sp = spf.newSAXParser();

						// get the XMLReader from SAXParser
						XMLReader xr = sp.getXMLReader();

						// get new ContentHandler for XMLReader
						SAXEventHandler sh = new SAXEventHandler();
						xr.setContentHandler(sh);

						// parse XML data from URL
						xr.parse(new InputSource(url.openStream()));

						// Our ExampleHandler now provides the parsed data to us. */
						StopName nm = sh.getStopName();
						setsNames(nm.getNames());
						setsIds(nm.getIds());
						// display results
					} catch (Exception e) {	
						Log.e(TAG, "Hit a problem", e);
					}
	}
				
				public void parsePrediction(String link) {

					StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
					StrictMode.setThreadPolicy(policy); 
					try {
						URL url = new URL(link);

						// get new SAXParser
						SAXParserFactory spf = SAXParserFactory.newInstance();
						SAXParser sp = spf.newSAXParser();

						// get the XMLReader from SAXParser
						XMLReader xr = sp.getXMLReader();

						// get new ContentHandler for XMLReader
						SAXEventHandler sh = new SAXEventHandler();
						xr.setContentHandler(sh);

						// parse XML data from URL
						xr.parse(new InputSource(url.openStream()));

						// Our ExampleHandler now provides the parsed data to us. */
						PredictionData nm = sh.getPredictionData();
						setpreds(nm.getPred());
						
						// display results
					} catch (Exception e) {	
						Log.e(TAG, "Hit a problem", e);
					}
	}
}
