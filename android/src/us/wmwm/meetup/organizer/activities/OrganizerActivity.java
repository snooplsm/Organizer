package us.wmwm.meetup.organizer.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

import meetup.Event;
import meetup.EventSearchCriteria;
import meetup.MeetupClient;
import meetup.MeetupResponse;
import meetup.Rsvp;
import meetup.RsvpSearchCriteria;

import org.json.JSONArray;
import org.json.JSONObject;

import us.wmwm.meetup.organizer.R;
import us.wmwm.meetup.organizer.fragments.FragmentEventEdit;
import us.wmwm.meetup.organizer.fragments.FragmentEvents;
import us.wmwm.meetup.organizer.fragments.FragmentEvents.OnEventClickedListener;
import us.wmwm.meetup.organizer.fragments.FragmentLogin;
import us.wmwm.meetup.organizer.fragments.FragmentLogin.OnLoginListener;
import us.wmwm.meetup.organizer.fragments.MeetupFragment;
import us.wmwm.meetup.organizer.util.ThreadHelper;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.flurry.android.FlurryAgent;

public class OrganizerActivity extends SherlockFragmentActivity {

	MeetupClient meetupClient;
	
	SharedPreferences prefs;
	
	Fragment currentFragment;
	
	Stack<Fragment> fragmentStacks = new Stack<Fragment>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		meetupClient = new MeetupClient("ps5b0vafb33bifcuokn2k0a16k","46fdj18amdo06ga7thck80fvkb");
		boolean authed = meetupClient.restoreSession(prefs);
		setContentView(R.layout.activity_organizer);
		
		Fragment fragmentToShow = null;
		
		if(authed) {
			//fragmentToS
			FragmentEvents fragmentEvents = new FragmentEvents();
			fragmentEvents.setOnEventClickedListener(onEventClickedListener);
//			ThreadHelper.getScheduler().submit(new Runnable() {
//				@Override
//				public void run() {
//					EventSearchCriteria c = new EventSearchCriteria();
//					Calendar fif = Calendar.getInstance();
//					fif.add(Calendar.DAY_OF_YEAR, 10);
//					c.setBefore(fif);
//					try {
//						JSONObject events = meetupClient.getEventsObject(c);
//						JSONArray a = events.optJSONArray("results");
//						if(a!=null) {
//							List<String> eventIds = new ArrayList<String>();
//							for(int i = 0; i < a.length(); i++) {
//								JSONObject o = a.optJSONObject(i);
//								int count = o.optInt("yes_rsvp_count");
//								if(count>2) {
//									String id = o.optString("id");
//									eventIds.add(id);	
//									
//								}
//								if(eventIds.size()==200 || i==a.length()-1) {
//									RsvpSearchCriteria k = new RsvpSearchCriteria();
//									k.setRsvpStatus(RsvpSearchCriteria.RSVP_YES);
//									k.setEventIds(eventIds);									
//									System.out.println(Arrays.toString(eventIds.toArray()));
//									eventIds = new ArrayList<String>();
//									JSONObject rsvps = meetupClient.getRsvpsObject(k);
//									JSONArray res = rsvps.optJSONArray("results");
//									JSONObject meta = rsvps.optJSONObject("meta");
//									while(meta!=null) {
//										for(int j = 0; j < res.length(); j++) {
//											JSONObject may = res.optJSONObject(j);
//											String id = may.optJSONObject("member").optString("member_id");
//											if(id.equals(c.getMemberId())) {
//												String event = may.optJSONObject("event").optString("event_url");												
//												Calendar when = Calendar.getInstance();
//												when.setTimeInMillis(may.optJSONObject("event").optLong("time"));
//												System.out.println("found her @ " + event + " " + when.getTime());
//											}
//										}
//										if(meta.optString("next",null)!=null) {
//											k.setOffset(k.getOffset()+1);
//											rsvps = meetupClient.getRsvpsObject(k);
//											res = rsvps.optJSONArray("results");
//											meta = rsvps.optJSONObject("meta");
//										} else {
//											meta = null;
//										}
//									}
//								}
//							}
//							
//						}
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			});
			fragmentToShow = fragmentEvents;
		} else {
			FragmentLogin fragmentLogin = new FragmentLogin();
			fragmentLogin.setClient(meetupClient);
			fragmentLogin.setOnLoginListener(onLoginListener);
			fragmentToShow = fragmentLogin;
		}		
		
		if(fragmentToShow instanceof MeetupFragment) {
			((MeetupFragment)fragmentToShow).setClient(meetupClient);
		}
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentToShow).commit();
		currentFragment = fragmentToShow;
		
	}
	
	OnEventClickedListener onEventClickedListener = new OnEventClickedListener() {
		
		@Override
		public void onEventClick(Event event) {
			System.out.println(event);
			FragmentEventEdit fee = FragmentEventEdit.newInstance(event);
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_secondary_content, fee).commit();
		}
	};
	
	OnLoginListener onLoginListener = new OnLoginListener() {
		@Override
		public void onLogin() {
			meetupClient.saveSession(prefs.edit());
			showDefaultFragment();
		}
		
	};
	
	private void showDefaultFragment() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onStart()
	{
		super.onStart();
		FlurryAgent.onStartSession(this, "R2QZRTWR7HNJQHZ8FCHJ");
	}
	 
	@Override
	protected void onStop()
	{
		super.onStop();		
		FlurryAgent.onEndSession(this);
	}

}
