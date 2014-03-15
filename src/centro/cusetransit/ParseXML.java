//////////////////////////////////////////////////////////////////////////////
// ParseXML.java - Show Routesof the Bus                                  //                                      
// ver 1.0                                                                  //
// Language:    JAVA, Android SDK                                           //
// Platform:    Dell Inspiron N5010, Win7                                   //
// Application: CuseTransit,Independent Study, Fall 2013                    // 
// Author:      Sundar Lakshmanan, 751818942, Syracuse University           //
//              (315) 744-7468, slaksh02@syr.edu                            //
//////////////////////////////////////////////////////////////////////////////
/*   
 * Maintenance History:
 * --------------------
 * ver 1.0 : 12 Dec 2012
 * - first release
 */
package centro.cusetransit;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
	private ArrayList<Double> angleList = new ArrayList<Double>();

	private ArrayList<Double> LatListStops = new ArrayList<Double>();
	private ArrayList<Double> LonListStops = new ArrayList<Double>();
	private List<Integer> StopIdName = new ArrayList<Integer>();
	private String StopName;

	private ArrayList<Double> LatListPatterns = new ArrayList<Double>();
	private ArrayList<Double> LonListPatterns = new ArrayList<Double>();

	private ArrayList<String> Names = new ArrayList<String>();
	private ArrayList<String> BusIds = new ArrayList<String>();

	private ArrayList<String> sNames = new ArrayList<String>();
	private ArrayList<Integer> sIds = new ArrayList<Integer>();

	private List<String> prediction = new ArrayList<String>();
	
	private ArrayList<String> rt = new ArrayList<String>();
	private ArrayList<String> rtName = new ArrayList<String>();

	
	private ArrayList<String> dir = new ArrayList<String>();
	
	private ArrayList<String> pid = new ArrayList<String>();
	
	public void setPid(ArrayList<String> input) {
		pid = input;
	}

	public ArrayList<String> getPid() {
		return pid;
	}
	
	public void setDir(ArrayList<String> input) {
		dir = input;
	}

	public ArrayList<String> getDir() {
		return dir;
	}
	
	public void setrt( ArrayList<String> input) {
		rt = input;
	}

	public ArrayList<String> getrt() {
		return rt;
	}
	
	public void setrtName(ArrayList<String> input) {
		rtName = input;
	}

	public ArrayList<String> getrtName() {
		return rtName;
	}

	public ArrayList<Double> getlatlist() {
		return LatList;
	}

	public ArrayList<Double> getlonlist() {
		return LonList;
	}
	
	public ArrayList<Double> getAnglelist() {
		return angleList;
	}

	public void setlatlist(ArrayList<Double> latitude) {
		LatList = latitude;
	}

	public void setlonlist(ArrayList<Double> longitude) {
		LonList = longitude;
	}
	
	public void setAnglelist(ArrayList<Double> AngleList) {
		angleList = AngleList;
	}

	public void setlatlistStops(ArrayList<Double> latitude) {
		LatListStops = latitude;
	}

	public void setlonlistStops(ArrayList<Double> longitude) {
		LonListStops = longitude;
	}

	public void setlatlistPatterns(ArrayList<Double> latitude) {
		LatListPatterns = latitude;
	}

	public void setlonlistPatterns(ArrayList<Double> longitude) {
		LonListPatterns = longitude;
	}

	public ArrayList<Double> getlatlistStops() {
		return LatListStops;
	}

	public void setStopIdName(List<Integer> longitude) {
		StopIdName = longitude;
	}

	public List<Integer> getStopIdName() {
		return StopIdName;
	}

	public ArrayList<Double> getlonlistStops() {
		return LonListStops;
	}

	public ArrayList<Double> getlatlistPatterns() {
		return LatListPatterns;
	}

	public ArrayList<Double> getlonlistPatterns() {
		return LonListPatterns;
	}

	public String getStopName() {
		return StopName;
	}

	public void setStopName(String input) {
		StopName = input;
	}

	public ArrayList<String> getNames() {
		return Names;
	}

	public void setNames(ArrayList<String> input) {
		Names = input;
	}

	public ArrayList<String> getbIds() {
		return BusIds;
	}

	public void setbIds(ArrayList<String> input) {
		BusIds = input;
	}

	public ArrayList<String> getsNames() {
		return sNames;
	}

	public void setsNames(ArrayList<String> input) {
		sNames = input;
	}

	public ArrayList<Integer> getsIds() {
		return sIds;
	}

	public void setsIds(ArrayList<Integer> input) {
		sIds = input;
	}

	public List<String> getpreds() {
		return prediction;
	}

	public void setpreds(List<String> input) {
		prediction = input;
	}

	public void parse(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
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
			setAnglelist(xmlData.getAngleList());
			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}
	}

	public void parseStops(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
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
			setStopIdName(StopData.getStopId());
			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}

	}

	public void parsePrediction(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
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
			setStopName(nm.getStopName());
			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}
	}

	public void parseBusNames(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
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
			ParsedBusNames nm = sh.getParsedBusNames();
			setrt(nm.getrt());
			setrtName(nm.getrtName());
			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}
	}

	public void parseBusDir(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
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
			BusDirections nm = sh.getBusDirections();
			setDir(nm.getDir());

			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}
	}
	
	public void parsePid(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
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
			PatternId nm = sh.getPatternId();
			setPid(nm.getPid());

			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}
	}
	public void parsePattern(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
		StrictMode.setThreadPolicy(policy);
		try {
			//link = "http://stackoverflow.com/feeds/tag?tagnames=android&sort=newest";
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
			
			//xr.parse(new InputSource(url.openStream()));
			XMLPullParser xp = new XMLPullParser();
			PatternData nm = xp.parse(url.openStream());
			String a = url.openStream().toString();
			// Our ExampleHandler now provides the parsed data to us. */
			//PatternData nm = sh.getPatternData();
			
			setlatlistPatterns(nm.getLatList());
			setlonlistPatterns(nm.getLonList());
			setrt(nm.getrt());
			setrtName(nm.getrtName());
			

			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}
	}
	
	
	public void parseTest(String link) {

		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
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
			XMLPullParser xp = new XMLPullParser();
			PatternData nm = xp.parse(url.openStream());
			String a = url.openStream().toString();
			// Our ExampleHandler now provides the parsed data to us. */
			//PatternData nm = sh.getPatternData();
			
			setlatlistPatterns(nm.getLatList());
			setlonlistPatterns(nm.getLonList());

			// display results
		} catch (Exception e) {
			Log.e(TAG, "Hit a problem", e);
		}
	}

}
