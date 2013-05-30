package us.wmwm.meetup.organizer.adapters;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import meetup.Event;
import meetup.EventHost;
import meetup.MeetupResponse;

import org.ocpsoft.prettytime.Duration;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.TimeFormat;

import us.wmwm.meetup.organizer.utils.DateUtil;

public class ViewPagerEventsModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	List<Calendar> days = new ArrayList<Calendar>();
	Map<Calendar,List<Event>> keyToEvents = new HashMap<Calendar,List<Event>>();
	String me;
	
	public ViewPagerEventsModel(String me, MeetupResponse<Event> resp) {
		this.me = me;
		List<Event> results = resp.getResults();
				
		for(Event event : results) {
//			if(!containsMe(event)) {
//				continue;
//			}
//			if(event.getSelf()==null || event.getEventHosts().contains()) {
//				System.out.println("Continuing");
//				continue;
//			}
			Long time = event.getTime();
			Calendar cal = null;
			if(time!=null) {
				cal = Calendar.getInstance();
				cal.setTimeInMillis(time);
				cal.set(Calendar.HOUR_OF_DAY, 0);
				cal.set(Calendar.MINUTE, 0);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 0);
			}
			List<Event> events = keyToEvents.get(cal);
			if(events==null) {
				events = new ArrayList<Event>();				
				keyToEvents.put(cal, events);
			}
			events.add(event);			
		}
		
		for(final Map.Entry<Calendar, List<Event>> e : keyToEvents.entrySet()) {
			Collections.sort(e.getValue(), new Comparator<Event>() {
				@Override
				public int compare(Event lhs, Event rhs) {
					int comp=0;
					if(e.getKey()!=null) {
						comp = lhs.getTime().compareTo(rhs.getTime());						
					} else {
					}
					if(comp==0) {
						comp = lhs.getName().compareTo(rhs.getName());
					}
					return comp;
				}
			});
			days.add(e.getKey());
		}
		
		Collections.sort(days, new Comparator<Calendar>() {

			@Override
			public int compare(Calendar lhs, Calendar rhs) {
				if(lhs!=null) {
					return lhs.compareTo(rhs);
				}
				if(rhs==null) {
					return 0;
				}
				return 1;
			}
			
		});
		
		if(days!=null) {
		for(Calendar d : days) {
			String dt;
			if(d!=null) {
				dt = format.format(d.getTime());
			} else {
				dt = "Unscheduled";
			}
			System.out.println(dt);
		}
		}
 	}
	
	private boolean containsMe(Event e) {
		if(e.getEventHosts()==null) {
			return false;
		}
		for(EventHost eh : e.getEventHosts()) {
			if(eh.getId().equals(me)) {
				return true;
			}
		}
		return false;
	}
	
	DateFormat format = new SimpleDateFormat("MMM d");
	
	public String getTitle(int pos) {
		Calendar c = days.get(pos);
		if(c==null) {
			return "Pending";
		}
		Calendar now = DateUtil.clearTime(Calendar.getInstance());
		Calendar tomorrow = DateUtil.clearTime(Calendar.getInstance());
		tomorrow.add(Calendar.DAY_OF_YEAR, 1);
		Calendar yesterday = DateUtil.clearTime(Calendar.getInstance());
		yesterday.add(Calendar.DAY_OF_YEAR, -1);	
		if(now.equals(c)) {
			return "Today";
		}
		if(tomorrow.equals(c)) {
			return "Tomorrow";
		}
		if(yesterday.equals(c)) {
			return "Yesterday";
		}
		long days = 0;
		if(c.after(tomorrow)) {
			days = (c.getTimeInMillis() - tomorrow.getTimeInMillis()) / 86400000;
			days = days+1;
					//tommorrow.getTic.getTimeInMillis() 
		} 
		if(days<7) {
			return days+"d";
		}
		long weeks = days/7;
		if(days<31) {
			long over = days % 7;
			if(over==0) {
				return weeks+"w";
			}
			return weeks+"w"+over+"d";
		}
		long months = days/30;
		if(months<12) {
			long mover = days % 30;
			long wover = mover / 7;
			long dover = mover % 7;
			StringBuilder b = new StringBuilder(months+"m");
			if(wover!=0) {
				b.append(wover+"w");
			}
			if(dover!=0) {
				b.append(dover+"d");
			}
			return b.toString();
		}
		long years = days/365;
		return years+"y+";
	}	
}
