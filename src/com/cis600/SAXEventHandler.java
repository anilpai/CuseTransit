package com.cis600;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

//import android.util.Log;

public class SAXEventHandler extends DefaultHandler {


	// flags to help keep track of open tags
	private boolean lonTag = false;
	private boolean latTag = false;
	private boolean bpTag = false;
	private boolean stopTag = false;
	private boolean bname = false;
	private boolean rtId = false;
	private boolean stid = false;
	private boolean stnm = false;
	private boolean pred = false;
	

	private ParsedXMLData xmlData = new ParsedXMLData();
	private ParsedStopData xmlStopData = new ParsedStopData();
	private BusName bs = new BusName();
	private StopName sn = new StopName();
	private PredictionData pd = new PredictionData();
	
	public ParsedXMLData getParsedData() {
		return this.xmlData;
	}

	public ParsedStopData getParsedStopData() {
		return this.xmlStopData;
	}
	public BusName getBusName() {
		return this.bs;
	}
	public StopName getStopName() {
		return this.sn;
	}
	public PredictionData getPredictionData() {
		return this.pd;
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
		} else if (localName.equals("stop")) {
			this.stopTag = true;
		} else if (localName.equals("vehicle")) {
			this.bpTag = true;
		} else if(localName.equals("rtnm")){
			this.bname = true;
		} else if(localName.equals("rt")){
			this.rtId = true;
		} else if(localName.equals("stpnm")){
			this.stnm = true;
		} else if(localName.equals("stpid")){
			this.stid = true;
		} else if(localName.equals("prdtm")){
			this.pred = true;
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
		} else if (localName.equals("stop")) {
			this.stopTag = false;
		} else if (localName.equals("vehicle")) {
			this.bpTag = false;
		} else if(localName.equals("rtnm")){
			this.bname = false;
		} else if(localName.equals("rt")){
			this.rtId = false;
		} else if(localName.equals("stpnm")){
			this.stnm = false;
		} else if(localName.equals("stpid")){
			this.stid = false;
		} else if(localName.equals("prdtm")){
			this.pred = false;
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
		if (this.latTag && this.stopTag) {
			xmlStopData.setLatList(Double.parseDouble(new String(ch, start, length)));
		}
		if (this.lonTag && this.stopTag) {
			xmlStopData.setLonList(Double.parseDouble(new String(ch, start, length)));
		}
		if(this.bname){
			bs.setNames(new String(ch,start,length));
		}
		if(this.rtId){
			bs.setIds(Integer.parseInt(new String(ch,start,length)));
			
		}
		if(this.stnm){
			sn.setNames(new String(ch,start,length));
			
		}
		if(this.stid){
			sn.setIds(Integer.parseInt(new String(ch,start,length)));
		}
		if(this.pred){
			pd.setPred(new String(ch,start,length));
		}
	}
}
