package us.wmwm.meetup.organizer.fragments;

import java.util.Calendar;
import java.util.Collections;
import java.util.concurrent.Future;

import meetup.Event;
import meetup.EventSearchCriteria;
import meetup.GroupSearchCriteria;
import meetup.MeetupClient;
import meetup.MeetupResponse;
import us.wmwm.meetup.organizer.R;
import us.wmwm.meetup.organizer.adapters.FragmentPagerAdapterEvents;
import us.wmwm.meetup.organizer.adapters.ViewPagerEventsModel;
import us.wmwm.meetup.organizer.util.ThreadHelper;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentEvents extends MeetupFragment {

	ViewPager pager;
	
	Handler handler = new Handler();
	
	Future<?> loadEventsFuture;
	
	EventSearchCriteria eventSearchCriteria;
	GroupSearchCriteria groupSearchCriteria;
	
	FragmentPagerAdapterEvents adapter;
	
	public interface OnEventClickedListener {
		void onEventClick(Event event);
	}
	
	OnEventClickedListener onEventClickedListener;
	
	public void setOnEventClickedListener(
			OnEventClickedListener onEventClickedListener) {
		this.onEventClickedListener = onEventClickedListener;
	}
	
	
	public void setClient(MeetupClient client) {
		this.client = client;
	}
	
	Runnable loadEventsRunnable = new Runnable() {
		@Override
		public void run() {
			try {
//				MeetupResponse<Group> groups = client.getGroups(groupSearchCriteria);
//				List<String> groupIds = new ArrayList<String>();
//				for(Group group : groups.getResults()) {
//					groupIds.add(group.getId());
//				}
//				eventSearchCriteria.setGroupIds(groupIds);
				Calendar before = Calendar.getInstance();
				before.add(Calendar.MONTH, 3);
				eventSearchCriteria.setBefore(before);
				MeetupResponse<Event> e = client.getEvents(eventSearchCriteria);
				final ViewPagerEventsModel m = new ViewPagerEventsModel(PreferenceManager.getDefaultSharedPreferences(getActivity()).getString("member_id", ""),e);
				handler.post(new Runnable() {
					@Override
					public void run() {
						adapter = new FragmentPagerAdapterEvents(getSherlockActivity().getSupportFragmentManager());
						adapter.setOnEventClickedListener(onEventClickedListener);
						adapter.setData(m);
						pager.setAdapter(adapter);
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_events, container,false);
		pager = (ViewPager) root.findViewById(R.id.pager);
		return root;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		EventSearchCriteria criteria = null;
		GroupSearchCriteria g = null;
		if(savedInstanceState!=null) {
			criteria = (EventSearchCriteria) savedInstanceState.getSerializable("criteria");
			g = (GroupSearchCriteria) savedInstanceState.getSerializable("groupSearchCriteria");
		} else {
			criteria = new EventSearchCriteria();
			g = new GroupSearchCriteria();
		}
		//Calendar monthAhead = Calendar.getInstance();
		//monthAhead.add(Calendar.MONTH, 3);
		criteria.setMemberId("self");
		//criteria.setRsvp(EventSearchCriteria.RSVP_YES);
		//criteria.setBefore(monthAhead);
		g.setOrganizerIds(Collections.singletonList("self"));
		eventSearchCriteria = criteria;
		groupSearchCriteria = g;
		ThreadHelper.getScheduler().submit(loadEventsRunnable);
		
	}
	
}
