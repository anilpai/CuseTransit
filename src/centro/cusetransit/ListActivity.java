//////////////////////////////////////////////////////////////////////////////
// ListActivity.java - Show Routesof the Bus                                  //                                      
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		
		String url = "http://bus-time.centro.org/bustime/api/v1/getroutes?key=XXXXXXXXXX";
		ParseXML xm = new ParseXML();
		xm.parsePattern(url);
		
		ArrayList<String> rt = new ArrayList<String>();
		ArrayList<String> rtName = new ArrayList<String>();
		
		rt = xm.getrt();
		rtName = xm.getrtName();
		
		final ListView listview = (ListView) findViewById(R.id.listView1);
	    String[] values = new String[] { "Android", "iPhone" };

	    final ArrayList<String> list = new ArrayList<String>();
	    for (int i = 1; i < rt.size(); ++i) {
	      list.add(rt.get(i) + " " + rtName.get(i));
	    }
	    final StableArrayAdapter adapter = new StableArrayAdapter(this,
	        android.R.layout.simple_list_item_1, list);
	    listview.setAdapter(adapter);

	    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	      @Override
	      public void onItemClick(AdapterView<?> parent, final View view,
	          int position, long id) {
	        final String item = (String) parent.getItemAtPosition(position);
	        String route = item.substring(0, item.indexOf(" "));
	        String url = "http://bus-time.centro.org/bustime/api/v1/getdirections?key=XXXXXXXXXX&rt=" + route;
	        String patternurl = "http://bus-time.centro.org/bustime/api/v1/getpatterns?key=XXXXXXXXXX&rt=" + route;
	        
	        ParseXML xm = new ParseXML();
	        xm.parseBusDir(url);
	        xm.parsePid(patternurl);
	        ArrayList<String> dir = new ArrayList<String>();
	        ArrayList<String> pid = new ArrayList<String>();
	        dir = xm.getDir();
	        pid = xm.getPid();
	        
	        Intent intent01 = new Intent(ListActivity.this, MapsActivity.class);
	        intent01.putExtra("rt", route);
	        intent01.putExtra("dir0", dir.get(0).replace(" ", "%20"));
	        intent01.putExtra("dir1", dir.get(1).replace(" ", "%20"));
	        if(pid.size() == 2){
	        intent01.putExtra("pid0", pid.get(0));
	        intent01.putExtra("pid1", pid.get(1));
	        }
	        else if(pid.size() == 2)
	        {
	        	intent01.putExtra("pid0", pid.get(0));
		        intent01.putExtra("pid1", "");
	        }
	        else
	        {
	        	intent01.putExtra("pid0", "");
		        intent01.putExtra("pid1", "");
	        }
	        startActivity(intent01);
	      }

	    });
	    
	    
	  }

	  private class StableArrayAdapter extends ArrayAdapter<String> {

	    HashMap<String, Integer> mIdMap = new HashMap<String, Integer>();

	    public StableArrayAdapter(Context context, int textViewResourceId,
	        List<String> objects) {
	      super(context, textViewResourceId, objects);
	      for (int i = 0; i < objects.size(); ++i) {
	        mIdMap.put(objects.get(i), i);
	      }
	    }

	    @Override
	    public long getItemId(int position) {
	      String item = getItem(position);
	      return mIdMap.get(item);
	    }

	    @Override
	    public boolean hasStableIds() {
	      return true;
	    }
	    
	  }
	  
}
