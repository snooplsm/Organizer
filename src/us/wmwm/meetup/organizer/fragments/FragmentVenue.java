package us.wmwm.meetup.organizer.fragments;

import meetup.Venue;
import us.wmwm.meetup.organizer.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class FragmentVenue extends MeetupFragment {

	MapView mapView;
	
	Venue venue;
	
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_venue, container,false);
		mapView = (MapView) root.findViewById(R.id.fragment_map);
		return root;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		//getSherlockActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_map, SupportMapFragment.newInstance()).commit();
		mapView.onCreate(savedInstanceState);
		//CameraUpdate c = CameraUpdateFactory.newLatLng(new LatLng((double)venue.getLat(), (double)venue.getLon()));
		GoogleMap map = mapView.getMap();
		if(map!=null) {
			map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			map.getUiSettings().setZoomControlsEnabled(false);
			//map.moveCamera(c);
			map.addMarker(new MarkerOptions().position(new LatLng((double)venue.getLat(), (double)venue.getLon())));
		}
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
//		mapView.onPause();
	}
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
//		mapView.onResume();
	}
}
