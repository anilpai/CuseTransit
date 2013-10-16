package com.cis600;

import com.cis600.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class Maps extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);
		View Button01 = findViewById(R.id.button1);
		Button01.setOnClickListener(this);
		View Button02 = findViewById(R.id.button2);
		Button02.setOnClickListener(this);
		View Button03 = findViewById(R.id.button3);
		Button03.setOnClickListener(this);
		View Button04 = findViewById(R.id.button4);
		Button04.setOnClickListener(this);
		View Button05 = findViewById(R.id.button5);
		Button05.setOnClickListener(this);
		View Button06 = findViewById(R.id.button6);
		Button06.setOnClickListener(this);
		View Button07 = findViewById(R.id.button7);
		Button07.setOnClickListener(this);
		View Button08 = findViewById(R.id.button8);
		Button08.setOnClickListener(this);
		View Button09 = findViewById(R.id.button9);
		Button09.setOnClickListener(this);
	}
	
	public void onClick(View v) {
		Intent intent01 = new Intent(Maps.this, BusDetails.class);
		switch (v.getId()) {
		case R.id.button1:
			intent01.putExtra("Title", "1");
			break;
		case R.id.button2:
			intent01.putExtra("Title", "2");
			break;
		case R.id.button3:
			intent01.putExtra("Title", "3");
			break;
		case R.id.button4:
			intent01.putExtra("Title", "4");
			break;
		case R.id.button5:
			intent01.putExtra("Title", "5");
			break;
		case R.id.button6:
			intent01.putExtra("Title", "6");
			break;
		case R.id.button7:
			intent01.putExtra("Title", "7");
			break;
		case R.id.button8:
			intent01.putExtra("Title", "8");
			break;
		case R.id.button9:
			intent01.putExtra("Title", "9");
			break;
		}
		startActivity(intent01);
	}
}
