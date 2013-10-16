package com.cis600;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.database.SQLException;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;

import com.cis600.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class BusDetails extends Activity implements OnClickListener {
	boolean loop;
	Marker hamburg;
	// Marker kiel;
	static final LatLng HAMBURG = new LatLng(43.033662, -76.124192);
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
		setContentView(R.layout.activity_bus_details);
		
		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		loop = true;
		int type = Integer.parseInt(getIntent().getExtras().getString("Title"));
		switch (type) {
		case 1:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=43";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=43&dir=FROM%20CAMPUS";
			break;
		case 2:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=44";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=44&dir=FROM%20CAMPUS";
			break;
		case 3:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=45";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=45&dir=FROM%20CAMPUS";
			break;
		case 4:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=144";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=144&dir=FROM%20CAMPUS";
			break;
		case 5:
			//drawRoute("143");
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=143";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=143&dir=FROM%20CAMPUS";
			break;
		case 6:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=244";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=244&dir=FROM%20CAMPUS";
			break;
		case 7:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=344";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=344&dir=FROM%20CAMPUS";
			break;
		case 8:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=443";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=443&dir=FROM%20CAMPUS";
			break;
		case 9:
			bpurl = "http://bus-time.centro.org/bustime/api/v1/getvehicles?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=643";
			stopurl = "http://bus-time.centro.org/bustime/api/v1/getstops?key=Ygk52j94TxVYHpb5KXazQgHRd&rt=643&dir=FROM%20CAMPUS";
			break;
		}
		View Button01 = findViewById(R.id.refresh);
		Button01.setOnClickListener(this);
		ParseXML xm = new ParseXML();

		xm.parseStops(stopurl);

		ArrayList<Double> LatListStops = new ArrayList<Double>();
		ArrayList<Double> LonListStops = new ArrayList<Double>();

		LatListStops = xm.getlatlistStops();
		LonListStops = xm.getlonlistStops();

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
			stopmks.add(ECM);
		}

		for (int i = 0; i < routePoints.size(); i++) {
			if (i != 0) {
				Polyline line = map.addPolyline(new PolylineOptions()
						.add(routePoints.get(i), routePoints.get(i - 1))
						.width(5).color(Color.RED).geodesic(true));
			}
		}
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(HAMBURG, 15));
		map.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);

	}

	@Override
	public void onBackPressed() {
		finish();
		loop = false;
	}

	public void onClick(View v) {
		View Button02 = findViewById(R.id.refresh);
		Button02.setEnabled(false);
		ParseXML x = new ParseXML();
		x.parse(bpurl);

		ArrayList<Double> LatList = new ArrayList<Double>();
		ArrayList<Double> LonList = new ArrayList<Double>();

		LatList = x.getlatlist();
		LonList = x.getlonlist();

		int size = LatList.size();

		ArrayList<LatLng> points1 = new ArrayList<LatLng>();

		for (int i = 0; i < size; i++) {
			LatLng EC = new LatLng(LatList.get(i), LonList.get(i));
			points1.add(EC);
		}
		final Marker[] markerlist = new Marker[size];

		for (int i = 0; i < size; i++) {
			markerlist[i] = map.addMarker(new MarkerOptions().position(points1
					.get(i)));
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
					LatLng EC = new LatLng(LatList.get(i), LonList.get(i));
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

	void drawRoute(String route) {
		
		DBLinker myDbHelper = new DBLinker(this);
		try {
			myDbHelper.createDataBase();
		} catch (IOException ioe) {
			throw new Error("Unable to create database");
		}
		try {
			myDbHelper.openDataBase();

		} catch (SQLException sqle) {

			throw sqle;

		}

		Cursor c = myDbHelper.getWritableDatabase().rawQuery(
				"SELECT lat, lng FROM " + "routeTable where busRouteID = " + route,
				null);

		Double lat;
		Double lon;

		List<Double> lats = new ArrayList<Double>();
		List<Double> lons = new ArrayList<Double>();
		if (c != null) {

			if (c.moveToFirst()) {
				do {
					try {
						lat = c.getDouble(c.getColumnIndex("lat"));
						lon = c.getDouble(c.getColumnIndex("lng"));
						lats.add(lat);
						lons.add(lon);
					} catch (Exception e) {
						System.out.print("");
					}
				} while (c.moveToNext());
			}

		}

		for (int i = 0; i < lats.size(); i++) {
			LatLng EC = new LatLng(lats.get(i), lons.get(i));
			routePoints.add(EC);
		}
	}

}