package com.cis600;

import java.util.ArrayList;

public class StopName {
	private ArrayList<String> sNames = new ArrayList<String>();
	private ArrayList<Integer> sID = new ArrayList<Integer>();


	public ArrayList<Integer> getIds() {
		return sID;
	}
	public void setIds(int Input) {
		this.sID.add(Input);
	}
	
	
	public ArrayList<String> getNames() {
		return sNames;
	}
	public void setNames(String Input) {
		this.sNames.add(Input);
	}
	
}
