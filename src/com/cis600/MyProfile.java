package com.cis600;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.cis600.R;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseObject;

public class MyProfile extends Activity implements OnClickListener{
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_profile);
		
		View Button01 = findViewById(R.id.button1);
		Button01.setOnClickListener(this);
		
		Parse.initialize(this, "KbiSXicfHuOMTJijDO7oeCI0kdNykjDhTWethUDM", "vrYqWxzCAtVo2oVvNaulHLbqD8sWMfdzucGErZo9");
        ParseAnalytics.trackAppOpened(getIntent());
	}
	
	@Override
	public void onClick(View v) {
		final EditText firstName  = (EditText) findViewById(R.id.editText1);
		final EditText lastName = (EditText) findViewById(R.id.editText2);
		final EditText email = (EditText) findViewById(R.id.editText3);
		final EditText age = (EditText) findViewById(R.id.editText4);
		ParseObject commuter = new ParseObject("Commuter");
		commuter.put("first_name", firstName.getText().toString());
		commuter.put("last_name",lastName.getText().toString());
		commuter.put("email_id",email.getText().toString());
		commuter.put("age",age.getText().toString());
		commuter.saveInBackground();
		firstName.setText("");
		lastName.setText("");
		email.setText("");
		age.setText("");
	}
}
