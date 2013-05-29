package meetup;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RsvpSearchCriteria {
	
	public static final int RSVP_YES=1;
	public static final int RSVP_NO=2;

	List<String> eventIds;
	
	int rsvpStatus;
	
	int page = 200;
	
	int offset = 0;
	
	public int getPage() {
		return page;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getOffset() {
		return offset;
	}

	public List<String> getEventIds() {
		return eventIds;
	}

	public void setEventIds(List<String> eventIds) {
		this.eventIds = eventIds;
	}

	public int getRsvpStatus() {
		return rsvpStatus;
	}

	public void setRsvpStatus(int rsvpStatus) {
		this.rsvpStatus = rsvpStatus;
	}
	
	Map<String,String> getParameterMap() {
		Map<String, String> m = new HashMap<String, String>();
		m.put("order", "name");
		int yes = RSVP_YES & rsvpStatus;
		Set<String> rsvp = new HashSet<String>();
		if(yes>0) {
			rsvp.add("yes");			
		}
		int no = RSVP_NO & rsvpStatus;
		if(no>0) {
			rsvp.add("no");
		}
		if(!rsvp.isEmpty()) {
			Iterator<String> iter = rsvp.iterator();
			String arg = "";
			while(iter.hasNext()) {
				arg+=iter.next();
				if(iter.hasNext()) {
					arg+=",";
				}
			}
			m.put("rsvp", arg);
		}
		if(eventIds!=null && !eventIds.isEmpty()) {
			StringBuilder b = new StringBuilder();
			Iterator<String> ids = eventIds.iterator();
			while(ids.hasNext()) {
				b.append(ids.next());
				if(ids.hasNext()) {
					b.append(",");
				}
			}
			m.put("event_id", b.toString());
		}
		m.put("page", String.valueOf(page));
		m.put("offset", String.valueOf(offset));
		return m;
	}
	
}
