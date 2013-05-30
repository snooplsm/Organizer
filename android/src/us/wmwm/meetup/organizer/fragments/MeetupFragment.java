package us.wmwm.meetup.organizer.fragments;

import meetup.MeetupClient;

import com.actionbarsherlock.app.SherlockFragment;

public class MeetupFragment extends SherlockFragment {

	protected MeetupClient client;
	
	public void setClient(MeetupClient client) {
		this.client = client;
	}
	
}
