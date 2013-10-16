package com.cis600;

import java.util.ArrayList;

public class ParsedStopData {

	private ArrayList<Double> LatList = new ArrayList<Double>();
	private ArrayList<Double> LonList = new ArrayList<Double>();

	public ArrayList<Double> getLatList() {
		return LatList;
	}
	public void setLatList(Double extractedString) {
		this.LatList.add(extractedString);
	}
	
	public ArrayList<Double> getLonList() {
		return LonList;
	}
	public void setLonList(Double extractedString) {
		this.LonList.add(extractedString);
	}
}
