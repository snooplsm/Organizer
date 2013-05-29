package meetup;

import java.util.Collections;
import java.util.concurrent.Future;

import us.wmwm.meetup.organizer.util.ThreadHelper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentAttendance extends SherlockFragment {
	
	Event event;
	
	MeetupClient client;
	
	RsvpSearchCriteria rsvpSearchCriteria;
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
	public void setClient(MeetupClient client) {
		this.client = client;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		rsvpSearchCriteria = new RsvpSearchCriteria();
		rsvpSearchCriteria.setEventIds(Collections.singletonList(event.getId()));
		rsvpSearchCriteria.setRsvpStatus(RsvpSearchCriteria.RSVP_YES);
	}
	
	Future<?> loadRsvpsFuture;
	
	private void loadRsvps() {
		if(loadRsvpsFuture!=null) {
			loadRsvpsFuture.cancel(true);
		}
		loadRsvpsFuture = ThreadHelper.getScheduler().submit(loadRsvps);
	}
	
	private Runnable loadRsvps = new Runnable() {
		public void run() {
			try {
				MeetupResponse<Rsvp> rsvps = client.getRsvps(rsvpSearchCriteria);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		};
	};
	
}
