package com.cis600;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.cis600.R;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class Routes extends Activity implements OnItemSelectedListener, OnClickListener{
	private HashMap<Integer,String> myMap = new HashMap<Integer,String>();
	private HashMap<Integer,String> stopMap = new HashMap<Integer,String>();
	
	private String userSelection;
	Spinner s,s1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_routes);
		
		View Button01 = findViewById(R.id.subb);
		Button01.setOnClickListener(this);
		
		s = (Spinner) findViewById(R.id.spinner1);
		s.setOnItemSelectedListener(this);
		
		
		List<String> list = new ArrayList<String>();
		List<Integer> busIds = new ArrayList<Integer>();
		List<String> disp = new ArrayList<String>();
		ParseXML xm = new ParseXML();
		xm.parseNames("http://bus-time.centro.org/bustime/api/v1/getroutes?key=Ygk52j94TxVYHpb5KXazQgHRd");
		list = xm.getNames();
		busIds = xm.getbIds();		
		
		for(int i=0;i<list.size();i++)
		{
			 myMap.put(busIds.get(i),list.get(i));
			 disp.add(busIds.get(i).toString() + "-"+list.get(i));
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, disp);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			s.setAdapter(dataAdapter);		
	}
	@Override
	public void onClick(View v) {
		
		TextView tv = (TextView) findViewById(R.id.textView1);
		if(s.getSelectedItem()!=null && s1.getSelectedItem()!=null)
		{
			String selectedRoute = s.getSelectedItem().toString();
			String selectedStop = s1.getSelectedItem().toString();
		selectedRoute = selectedRoute.substring(0, selectedRoute.indexOf("-"));
		selectedStop = selectedStop.substring(0, selectedStop.indexOf("-"));
		
		String url = "http://bus-time.centro.org/bustime/api/v1/getpredictions?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=" + selectedRoute + "&stpid="+ selectedStop;
		ParseXML xm = new ParseXML();
		xm.parsePrediction(url);
		String stName = xm.getpreds();
		
		if(stName == null)
		{
			tv.setText("NO SERVICE AT THIS TIME");
		}
		else
		{
			tv.setText(stName);
		}
		}
		else
		{
			tv.setText("Please select values in the drop down box");
		}
	}
	@Override
	public void onItemSelected(AdapterView arg0, View arg1, int pos,long arg3) {
		//userSelection = list[pos];
		userSelection = s.getSelectedItem().toString();
		populateList2();
	}
	@Override
	public void onNothingSelected(AdapterView arg0) {
		// TODO Auto-generated method stub
		userSelection = "";
	}
	
	void populateList2()
	{
		String temp = userSelection;
		temp = temp.substring(0, temp.indexOf("-"));
		String url = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=" + temp + "&dir=FROM%20CAMPUS";
		List<String> stName = new ArrayList<String>();
		List<Integer> stId = new ArrayList<Integer>();
		List<String> disp = new ArrayList<String>();
		s1 = (Spinner) findViewById(R.id.spinner2);
		
		
		ParseXML xm = new ParseXML();
		xm.parseNews(url);
		stName = xm.getsNames();
		stId = xm.getsIds();
		
		for(int i=0;i<stName.size();i++)
		{
			stopMap.put(stId.get(i),stName.get(i));
			 disp.add(stId.get(i).toString() + "-"+stName.get(i));
		}
		
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, disp);
			dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			s1.setAdapter(dataAdapter);
		
	}
	
}
