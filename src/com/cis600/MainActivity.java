package com.cis600;


import com.cis600.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity 
implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		View Button01 = findViewById(R.id.button1);
		Button01.setOnClickListener(this);
		View Button02 = findViewById(R.id.button2);
		Button02.setOnClickListener(this);
		View Button03 = findViewById(R.id.button3);
		Button03.setOnClickListener(this);
//		View Button04 = findViewById(R.id.button4);
//		Button04.setOnClickListener(this);
//		View Button05 = findViewById(R.id.button5);
//		Button05.setOnClickListener(this);
//		View Button06 = findViewById(R.id.button6);
//		Button06.setOnClickListener(this);
	}
	public void onClick(View v) {

		switch (v.getId()) {
		
		case R.id.button1:
			Intent intent01 = new Intent(MainActivity.this, MyProfile.class);
			startActivity(intent01);
			break;
		case R.id.button2:
			Intent intent02 = new Intent(MainActivity.this, Maps.class);
			startActivity(intent02);
			break;
		case R.id.button3:
			Intent intent03 = new Intent(MainActivity.this, Routes.class);
			startActivity(intent03);
			break;
	
		}
	}

}
