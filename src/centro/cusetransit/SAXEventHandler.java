//////////////////////////////////////////////////////////////////////////////
// SAXEventHandler.java - Show Routesof the Bus                                  //                                      
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
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import centro.cusetransit.PredictionData;

//import android.util.Log;

public class SAXEventHandler extends DefaultHandler {


	// flags to help keep track of open tags
	private boolean lonTag = false;
	private boolean latTag = false;
	private boolean bpTag = false;
	private boolean stopTag = false;
	private boolean StopIdTag = false;
	private boolean pred = false;
	private boolean stpnm = false;
	private boolean ptTag = false;
	private boolean angleTag = false;
	private boolean busPosTag = false;
	private boolean rtTag = false;
	private boolean rtNameTag = false;
	private boolean dirTag = false;
	private boolean pidTag = false;
	private ParsedXMLData xmlData = new ParsedXMLData();
	private ParsedStopData xmlStopData = new ParsedStopData();
	private PredictionData pd = new PredictionData();
	private PatternData pad = new PatternData();
	private ParsedBusNames pbn = new ParsedBusNames();
	private BusDirections bd = new BusDirections();
	private PatternId pid = new PatternId();
	
	public ParsedXMLData getParsedData() {
		return this.xmlData;
	}

	public ParsedStopData getParsedStopData() {
		return this.xmlStopData;
	}
	
	public PredictionData getPredictionData() {
		return this.pd;
	}
	
	public PatternData getPatternData() {
		return this.pad;
	}
	
	public ParsedBusNames getParsedBusNames() {
		return this.pbn;
	}
	
	public PatternId getPatternId() {
		return this.pid;
	}
	
	public BusDirections getBusDirections() {
		return this.bd;
	}
	// called when start of XML document encountered
	@Override
	public void startDocument() throws SAXException {
		this.xmlData = new ParsedXMLData();
	}

	// called when end of XML document encountered
	@Override
	public void endDocument() throws SAXException {
		// Nothing to do
	}

	// called on start tags <...>
	@Override
	public void startElement(String namespaceURI, String localName,
			String qName, Attributes attrs) throws SAXException {

		if (localName.equals("lon")) {
			this.lonTag = true;
		} else if (localName.equals("lat")) {
			this.latTag = true;
		} else if (localName.equals("hdg")) {
			this.angleTag = true;
		} else if (localName.equals("stop")) {
			this.stopTag = true;
		} else if (localName.equals("vehicle")) {
			this.bpTag = true;
		} else if (localName.equals("stpid")) {
			this.StopIdTag = true;
		} else if(localName.equals("prdtm")){
			this.pred = true;
		} else if(localName.equals("stpnm")){
			this.stpnm = true;
		} else if(localName.equals("pt")){
			this.ptTag = true;
		} else if(localName.equals("BusPosition")){
			this.busPosTag = true;
		} else if(localName.equals("rt")){
			this.rtTag = true;
		} else if(localName.equals("rtnm")){
			this.rtNameTag = true;
		} else if(localName.equals("dir")){
			this.dirTag = true;
		} else if(localName.equals("dir")){
			this.dirTag = true;
		} else if(localName.equals("pid")){
			this.pidTag = true;
		}
		
	}

	// called on end tags </...>
	@Override
	public void endElement(String namespaceURI, String localName, String qName)
			throws SAXException {
		if (localName.equals("lon")) {
			this.lonTag = false;
		} else if (localName.equals("lat")) {
			this.latTag = false;
		} else if (localName.equals("hdg")) {
			this.angleTag = false;
		} else if (localName.equals("stop")) {
			this.stopTag = false;
		} else if (localName.equals("vehicle")) {
			this.bpTag = false;
		} else if (localName.equals("stpid")) {
			this.StopIdTag = false;
		} else if(localName.equals("prdtm")){
			this.pred = false;
		} else if(localName.equals("stpnm")){
			this.stpnm = false;
		} else if(localName.equals("pt")){
			this.ptTag = false;
		} else if(localName.equals("BusPosition")){
			this.busPosTag = false;
		} else if(localName.equals("rt")){
			this.rtTag = false;
		} else if(localName.equals("rtnm")){
			this.rtNameTag = false;
		} else if(localName.equals("pid")){
			this.pidTag = false;
		}
	}

	// called on <tag>characters</tag>
	@Override
	public void characters(char ch[], int start, int length) {
		if (this.latTag && this.bpTag) {
			xmlData.setLatList(Double.parseDouble(new String(ch, start, length)));
		}
		if (this.lonTag && this.bpTag) {
			xmlData.setLonList(Double.parseDouble(new String(ch, start, length)));
		}
		
		if (this.bpTag && this.angleTag) {
			xmlData.setAngleList(Double.parseDouble(new String(ch, start, length)));
		}
		
		if (this.latTag && this.busPosTag) {
			xmlData.setLatList(Double.parseDouble(new String(ch, start, length)));
		}
		if (this.lonTag && this.busPosTag) {
			xmlData.setLonList(Double.parseDouble(new String(ch, start, length)));
		}
		
		if (this.bpTag && this.busPosTag) {
			xmlData.setAngleList(Double.parseDouble(new String(ch, start, length)));
		}
		
		if (this.latTag && this.stopTag) {
			xmlStopData.setLatList(Double.parseDouble(new String(ch, start, length)));
		}
		if (this.lonTag && this.stopTag) {
			xmlStopData.setLonList(Double.parseDouble(new String(ch, start, length)));
		}
		if (this.StopIdTag && this.stopTag) {
			xmlStopData.setStopId(Integer.parseInt(new String(ch, start, length)));
		}
		if(this.pred){
			pd.setPred(new String(ch,start,length));
		}
		if(this.stpnm){
			pd.setStopName(new String(ch,start,length));
		}
		if (this.latTag && this.ptTag) {
			pad.setLatList(Double.parseDouble(new String(ch, start, length)));
		}
		if (this.lonTag && this.ptTag) {
			pad.setLonList(Double.parseDouble(new String(ch, start, length)));
		}
		if (this.rtTag) {
			pbn.setrt(new String(ch, start, length));
		}		
		if (this.rtNameTag) {
			pbn.setrtName(new String(ch, start, length));
		}
		if (this.dirTag) {
			bd.setDir(new String(ch, start, length));
		}
		if (this.pidTag) {
			pid.setPid(new String(ch, start, length));
		}
	}
}

