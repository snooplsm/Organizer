package us.wmwm.meetup.organizer.fragments;

import meetup.Event;
import meetup.Venue;
import us.wmwm.meetup.organizer.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

public class FragmentEventEdit extends MeetupFragment {

	Event event;
	
	TextView name;
	CheckBox membersOnlyVenue;
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_event_edit, container,false);
		name = (TextView) root.findViewById(R.id.fragment_event_name);
		membersOnlyVenue = (CheckBox) root.findViewById(R.id.fragment_venue_private);
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		event = (Event) getArguments().getSerializable("event");
		
		name.setText(event.getName());
		Venue venue = event.getVenue();
		if(venue!=null) {
			FragmentVenue v = new FragmentVenue();
			v.setVenue(venue);
			getSherlockActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_venue, v).commit();
		}
	}

	public static FragmentEventEdit newInstance(Event event) {
		// TODO Auto-generated method stub
		Bundle b= new Bundle();
		b.putSerializable("event", event);
		FragmentEventEdit e = new FragmentEventEdit();
		e.setArguments(b);
		return e;
	}
	
}
