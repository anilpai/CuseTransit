package com.cis600;

import java.util.ArrayList;

public class BusName {
	private ArrayList<String> Names = new ArrayList<String>();
	private ArrayList<Integer> rID = new ArrayList<Integer>();

	
	public ArrayList<Integer> getIds() {
		return rID;
	}
	public void setIds(int Input) {
		this.rID.add(Input);
	}
	
	
	public ArrayList<String> getNames() {
		return Names;
	}
	public void setNames(String Input) {
		this.Names.add(Input);
	}
	
	

}
