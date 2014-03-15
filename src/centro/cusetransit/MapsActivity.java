//////////////////////////////////////////////////////////////////////////////
// MapsActivity.java - Show Routesof the Bus                                  //                                      
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
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.LevelListDrawable;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends Activity implements OnClickListener,
		OnMarkerClickListener {

	boolean loop;
	Marker hamburg;
	double lat;
	double lon;
	// Marker kiel;
	static final LatLng HAMBURG = new LatLng(43.033662, -76.124192);
	LatLng zoomPoint = new LatLng(0,0);
	ArrayList<LatLng> routePoints = new ArrayList<LatLng>();
	// static final LatLng KIEL = new LatLng(43.043290, -76.119645);
	private GoogleMap map;
	private String stopurl;
	private String bpurl;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_maps);

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		loop = true;
		map.setMyLocationEnabled(true);
		Location myLocation = map.getMyLocation();
		if (myLocation != null) {
			LatLng EC = new LatLng(myLocation.getLatitude(),
					myLocation.getLongitude());
			map.addMarker(new MarkerOptions().position(EC).icon(
					BitmapDescriptorFactory
							.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
		}

		drawPattern("http://bus-time.centro.org/bustime/api/v1/getpatterns?key=XXXXXXXX&pid=" + getIntent().getExtras().getString("pid0"));
		drawPattern("http://bus-time.centro.org/bustime/api/v1/getpatterns?key=XXXXXXXX&pid=" + getIntent().getExtras().getString("pid1"));
		bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=XXXXXXXX&rt=" + getIntent().getExtras().getString("rt");
		//stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=XXXXXXXX&rt=344&dir=FROM%20CAMPUS";

		try {
			map.setOnMarkerClickListener(this);
		} catch (Exception e) {
			Log.e("", "");
		}
		map.setOnInfoWindowClickListener(new OnInfoWindowClickListener() {
			public void onInfoWindowClick(Marker marker) {
				String stopid = marker.getTitle().substring(0, 5);
				String predictionurl = "http://bus-time.centro.org/bustime/api/v1/getpredictions?key=XXXXXXXX&rt=" + getIntent().getExtras().getString("rt") + "&stpid="
						+ stopid;
				ParseXML xm = new ParseXML();
				xm.parsePrediction(predictionurl);
				List<String> time = xm.getpreds();
				String timestogether = new String();
				for (int i = 0; i < time.size(); i++) {
					timestogether = timestogether + "\n"
							+ time.get(i).substring(9);
				}
				Toast.makeText(getBaseContext(), timestogether,
						Toast.LENGTH_LONG).show();
			}
		});
		View Button01 = findViewById(R.id.refresh);
		Button01.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				View Button02 = findViewById(R.id.refresh);
				Button02.setEnabled(false);
				ParseXML x = new ParseXML();
				x.parse(bpurl);

				ArrayList<Double> LatList = new ArrayList<Double>();
				ArrayList<Double> LonList = new ArrayList<Double>();
				ArrayList<Double> AngleList = new ArrayList<Double>();

				LatList = x.getlatlist();
				LonList = x.getlonlist();
				AngleList = x.getAnglelist();

				int size = LatList.size();

				ArrayList<LatLng> points1 = new ArrayList<LatLng>();

				for (int i = 0; i < size; i++) {
					LatLng EC = new LatLng(LatList.get(i), LonList.get(i));
					points1.add(EC);
				}
				final Marker[] markerlist = new Marker[size];

				for (int i = 0; i < size; i++) {
//					LevelListDrawable d=(LevelListDrawable) getResources().getDrawable(R.drawable.bus_icon);
//					d.setLevel(1234);
//					BitmapDrawable bd=(BitmapDrawable) d.getCurrent();
//					Bitmap b=bd.getBitmap();
					
					BitmapDrawable bd=(BitmapDrawable) getResources().getDrawable(R.drawable.bus_icon);
					Bitmap b=bd.getBitmap();
					Bitmap bhalfsize=Bitmap.createScaledBitmap(b, b.getWidth()/10,b.getHeight()/10, false);

					markerlist[i] = map.addMarker(new MarkerOptions()
							.rotation(AngleList.get(i).floatValue())
							.position(points1.get(i))
							.flat(true)
							.icon(BitmapDescriptorFactory.fromBitmap(bhalfsize)));
				}

				final Handler handler = new Handler();
				handler.post(new Runnable() {
					@Override
					public void run() {
						ParseXML xm1 = new ParseXML();
						xm1.parse(bpurl);

						ArrayList<Double> LatList = new ArrayList<Double>();
						ArrayList<Double> LonList = new ArrayList<Double>();

						LatList = xm1.getlatlist();
						LonList = xm1.getlonlist();

						int size = LatList.size();

						ArrayList<LatLng> points = new ArrayList<LatLng>();

						for (int i = 0; i < size; i++) {
							LatLng EC = new LatLng(LatList.get(i), LonList
									.get(i));
							points.add(EC);

						}

						for (int i = 0; i < size; i++) {
							markerlist[i].setPosition(points.get(i));
						}

						// Post again 16ms later.
						if (loop == true)
							handler.postDelayed(this, 10000);
					}
				});
			}
		});

		markStops("http://bus-time.centro.org/bustime/api/v1/getstops?key=XXXXXXXX&rt=" + getIntent().getExtras().getString("rt") + "&dir=" + getIntent().getExtras().getString("dir0"));
		markStops("http://bus-time.centro.org/bustime/api/v1/getstops?key=XXXXXXXX&rt=" + getIntent().getExtras().getString("rt") + "&dir=" + getIntent().getExtras().getString("dir1"));
		
		LatLng zoomPoint = new LatLng(lat,lon);
			map.moveCamera(CameraUpdateFactory.newLatLngZoom(zoomPoint, 15));
			map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);
		
		 
	}

	@Override
	public void onBackPressed() {
		finish();
		loop = false;
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onMarkerClick(final Marker marker) {
		// marker.setTitle(marker.getId());
		String stopid = marker.getTitle();
		String predictionurl = "http://bus-time.centro.org/bustime/api/v1/getpredictions?key=XXXXXXXX&rt=" + getIntent().getExtras().getString("rt") + "&stpid="
				+ stopid;
		ParseXML xm = new ParseXML();
		xm.parsePrediction(predictionurl);
		if(xm.getStopName() == null)
		{
			marker.setTitle(marker.getTitle());
		}
		else
		{
			marker.setTitle(marker.getTitle() + " " + xm.getStopName());
		}
		/*
		 * try{ timestogether = timestogether.substring(0,4) + "-" +
		 * timestogether.substring(4,6) + "-" +
		 * timestogether.substring(6,timestogether.length()); String time1 =
		 * timestogether; String time2 = "2013-12-31 17:00"; SimpleDateFormat
		 * format = new SimpleDateFormat("yyyy-MM-dd HH:mm"); Date date1 =
		 * format.parse(time1); Date date2 = format.parse(time2); long
		 * difference = (date2.getTime() - date1.getTime())/1000;
		 * marker.setTitle(String.valueOf(difference)); } catch(Exception e) {
		 * Log.d("",""); }
		 */
		// TODO Auto-generated method stub
		return false;
	}

	void markStops(String url) {
		ParseXML xm = new ParseXML();

		xm.parseStops(url);

		ArrayList<Double> LatListStops = new ArrayList<Double>();
		ArrayList<Double> LonListStops = new ArrayList<Double>();
		List<Integer> StopId = new ArrayList<Integer>();

		LatListStops = xm.getlatlistStops();
		LonListStops = xm.getlonlistStops();
		StopId = xm.getStopIdName();
		int stopsize = LatListStops.size();

		ArrayList<LatLng> stoppoints = new ArrayList<LatLng>();
		ArrayList<Marker> stopmks = new ArrayList<Marker>();
		
		

		for (int i = 0; i < stopsize; i++) {
			BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory
					.defaultMarker(BitmapDescriptorFactory.HUE_AZURE);
			LatLng EC = new LatLng(LatListStops.get(i), LonListStops.get(i));
			stoppoints.add(EC);
			Marker ECM = map.addMarker(new MarkerOptions().position(EC).icon(
					bitmapDescriptor));
			ECM.setTitle(StopId.get(i).toString());
			stopmks.add(ECM);
		}
		
		if(LonListStops.size() > 0 && LatListStops.size() > 0)
		{
			try
			{
			lat = LatListStops.get(0);
			lon = LonListStops.get(0);
			}
			catch(Exception e)
			{
				Log.e("", "");
			}
		}
		
	}

	void drawPattern(String url) {
		String patternurl = url;
		ParseXML xm1 = new ParseXML();
		xm1.parsePattern(patternurl);

		List<Double> lats = new ArrayList<Double>();
		List<Double> lons = new ArrayList<Double>();

		lats = xm1.getlatlistPatterns();
		lons = xm1.getlonlistPatterns();

		for (int i = 0; i < lons.size(); i++) {
			LatLng EC = new LatLng(lats.get(i), lons.get(i));
			routePoints.add(EC);
		}

		for (int i = 0; i < routePoints.size(); i++) {
			if (i != 0) {
				Polyline line = map.addPolyline(new PolylineOptions()
						.add(routePoints.get(i), routePoints.get(i - 1))
						.width(5).color(Color.RED).geodesic(true));
			}
		}
	}

}
