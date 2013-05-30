package us.wmwm.meetup.organizer.views;

import java.text.DateFormat;
import java.util.Calendar;

import meetup.Event;
import meetup.PhotoSet;
import meetup.Venue;
import us.wmwm.meetup.organizer.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

public class EventItemView extends RelativeLayout {

	SmartImageView thumbnail;
	TextView groupName;
	TextView eventName;
	TextView date;
	TextView venueName;
	
	static DateFormat HOUR_FORMAT = DateFormat.getTimeInstance(DateFormat.SHORT);
	
	public EventItemView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.view_event_item,this);
		thumbnail = (SmartImageView) findViewById(R.id.thumbnail);
		groupName = (TextView) findViewById(R.id.group_name);
		eventName = (TextView) findViewById(R.id.event_name);
		date = (TextView) findViewById(R.id.date);
		venueName = (TextView) findViewById(R.id.venue_name);
	}
	
	public void setEvent(Event event) {
		String url = null;
		if(event.getGroup().getPhotoSet()!=null) {
			PhotoSet ps = event.getGroup().getPhotoSet();
			if(ps!=null) {
				url = ps.getThumbLink();
			}
		}
		thumbnail.setImageUrl(url);
		groupName.setText(event.getGroup().getName());
		eventName.setText(event.getName());
		Venue venue = event.getVenue();
		String venueName = null;
		if(venue!=null) {
			venueName = venue.getName();
		}
		this.venueName.setText(venueName);
		if(event.getTime()!=null) {
			Calendar c = Calendar.getInstance();
			c.setTimeInMillis(event.getTime());
			date.setText(HOUR_FORMAT.format(c.getTime()));
			date.setVisibility(View.VISIBLE);
		} else {
			date.setVisibility(View.GONE);
		}
	}

	
}
