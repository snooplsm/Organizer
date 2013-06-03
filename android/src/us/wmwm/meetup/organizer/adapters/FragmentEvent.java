package us.wmwm.meetup.organizer.adapters;

import java.util.Calendar;
import java.util.List;

import meetup.Event;
import us.wmwm.meetup.organizer.fragments.FragmentEvents.OnEventClickedListener;
import us.wmwm.meetup.organizer.views.EventItemView;
import us.wmwm.meetup.organizer.views.EventTimeView;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentEvent extends SherlockFragment {

	ListView list;	
	
	OnEventClickedListener onEventClickedListener;
	
	Calendar day;
	
	public void setOnEventClickedListener(
			OnEventClickedListener onEventClickedListener) {
		this.onEventClickedListener = onEventClickedListener;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		list = new ListView(inflater.getContext());
		list.setDivider(null);
		return list;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		final List<Event> events = (List<Event>) getArguments()
				.getSerializable("events");
		day = (Calendar) getArguments().getSerializable("day");
		list.setAdapter(new BaseAdapter() {
			
			private static final int TIME = 0;
			
			private static final int EVENT = 1;
			
			@Override
			public int getCount() {
				return 1+events.size();
			}

			@Override
			public Event getItem(int position) {
				return events.get(position-1);
			}
			
			@Override
			public int getItemViewType(int position) {
				if(position==0) {
					return TIME;
				}
				return EVENT;
			}
			
			@Override
			public int getViewTypeCount() {
				return 2;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				int type = getItemViewType(position);
								
				if (convertView == null) {
					if(type==EVENT) {
						convertView = new EventItemView(parent.getContext());
					} else {
						convertView = new EventTimeView(parent.getContext());
					}
				}
				
				if(type==EVENT) {
					EventItemView ev = (EventItemView) convertView;
					ev.setEvent(getItem(position));
				} else {
					EventTimeView ev = (EventTimeView)convertView;
					ev.text.setText(DateFormat.getLongDateFormat(getActivity()).format(day.getTime()));
				}
				
				return convertView;
			}

		});
		
		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				if(onEventClickedListener!=null) {
					onEventClickedListener.onEventClick(((EventItemView)arg1).getEvent());
				}
			}
		});
	}

}
