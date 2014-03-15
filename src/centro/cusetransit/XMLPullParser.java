//////////////////////////////////////////////////////////////////////////////
// XMLPullParser.java - Show Routesof the Bus                                  //                                      
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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

public class XMLPullParser {
	private static final String ns = null;
	PatternData patternData = new PatternData();
	public PatternData parse(InputStream in) throws XmlPullParserException,
			IOException {
		try {
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(in, null);
			parser.nextTag();
			return readFeed(parser);
		} finally {
			in.close();
		}
	}

	private PatternData readFeed(XmlPullParser parser)
			throws XmlPullParserException, IOException {
		

		parser.require(XmlPullParser.START_TAG, ns, "bustime-response");
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			// Starts by looking for the entry tag
			if (name.equals("ptr")) {
				readEntry(parser);
			} else if (name.equals("route")){
				readRoute(parser);
			} else {
				skip(parser);
			}
		}
		return patternData;
	}

	// Parses the contents of an entry. If it encounters a title, summary, or
	// link tag, hands them off
	// to their respective "read" methods for processing. Otherwise, skips the
	// tag.
	private void readEntry(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		parser.require(XmlPullParser.START_TAG, ns, "ptr");

		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("pt")) {
				readPt(parser);
			} else {
				skip(parser);
			}
		}
	}
	
	// Parses the contents of an entry. If it encounters a title, summary, or
	// link tag, hands them off
	// to their respective "read" methods for processing. Otherwise, skips the
	// tag.
	private void readRoute(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		parser.require(XmlPullParser.START_TAG, ns, "route");

		String rt = null;
		String rtnm = null;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("rt")) {
				rt = readRt(parser);
				patternData.setrt(rt);
			} else if (name.equals("rtnm")) {
				rtnm = readRtname(parser);
				patternData.setrtName(rtnm);
			} else {
				skip(parser);
			}
		}
	}
	// Parses the contents of an entry. If it encounters a title, summary, or
	// link tag, hands them off
	// to their respective "read" methods for processing. Otherwise, skips the
	// tag.
	private void readPt(XmlPullParser parser) throws XmlPullParserException,
			IOException {

		parser.require(XmlPullParser.START_TAG, ns, "pt");
		String lat = null;
		String lon = null;
		while (parser.next() != XmlPullParser.END_TAG) {
			if (parser.getEventType() != XmlPullParser.START_TAG) {
				continue;
			}
			String name = parser.getName();
			if (name.equals("lat")) {
				lat = readTitle(parser);
				patternData.setLatList(Double.parseDouble(lat));
			} else if (name.equals("lon")) {
				lon = readSummary(parser);
				patternData.setLonList(Double.parseDouble(lon));
			} else {
				skip(parser);
			}
		}
	}

	private void skip(XmlPullParser parser) throws XmlPullParserException,
			IOException {
		if (parser.getEventType() != XmlPullParser.START_TAG) {
			throw new IllegalStateException();
		}
		int depth = 1;
		while (depth != 0) {
			switch (parser.next()) {
			case XmlPullParser.END_TAG:
				depth--;
				break;
			case XmlPullParser.START_TAG:
				depth++;
				break;
			}
		}
	}

	// Processes title tags in the feed.
	private String readTitle(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "lat");
		String title = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "lat");
		return title;
	}

	// Processes summary tags in the feed.
	private String readSummary(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "lon");
		String summary = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "lon");
		return summary;
	}

	
	private String readRt(XmlPullParser parser) throws IOException,
		XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "rt");
		String title = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "rt");
		return title;
	}

	// Processes summary tags in the feed.
	private String readRtname(XmlPullParser parser) throws IOException,
		XmlPullParserException {
		parser.require(XmlPullParser.START_TAG, ns, "rtnm");
		String summary = readText(parser);
		parser.require(XmlPullParser.END_TAG, ns, "rtnm");
		return summary;
	}
	// For the tags title and summary, extracts their text values.
	private String readText(XmlPullParser parser) throws IOException,
			XmlPullParserException {
		String result = "";
		if (parser.next() == XmlPullParser.TEXT) {
			result = parser.getText();
			parser.nextTag();
		}
		return result;
	}
}
