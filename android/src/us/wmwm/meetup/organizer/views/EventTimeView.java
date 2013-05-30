package us.wmwm.meetup.organizer.views;

import us.wmwm.meetup.organizer.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class EventTimeView extends RelativeLayout {

	public TextView text;
	
	public EventTimeView(Context context) {
		super(context);
		LayoutInflater.from(context).inflate(R.layout.view_event_time,this);
		text = (TextView) findViewById(R.id.text);
	}
	
	

}
