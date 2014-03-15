//////////////////////////////////////////////////////////////////////////////
// MainActivity.java - Show Routesof the Bus                                  //                                      
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

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;

public class MainActivity extends Activity 
implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		View Button01 = findViewById(R.id.button1);
//		Button01.setOnClickListener(this);
//		View Button02 = findViewById(R.id.button2);
//		Button02.setOnClickListener(this);
		View Button03 = findViewById(R.id.button3);
		Button03.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClick(View v) {

		switch (v.getId()) {
		
//		case R.id.button1:
//			Intent intent01 = new Intent(MainActivity.this, MapsActivity.class);
//			startActivity(intent01);
//			break;
//		case R.id.button2:
//			Intent intent02 = new Intent(MainActivity.this, BusListActivity.class);
//			startActivity(intent02);
//			break;
		case R.id.button3:
			Intent intent03 = new Intent(MainActivity.this, ListActivity.class);
			startActivity(intent03);
			break;
	
		}
	}


}
