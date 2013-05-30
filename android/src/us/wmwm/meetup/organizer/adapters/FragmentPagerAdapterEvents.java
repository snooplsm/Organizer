package us.wmwm.meetup.organizer.adapters;

import java.io.Serializable;

import us.wmwm.meetup.organizer.fragments.FragmentEvents.OnEventClickedListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPagerAdapterEvents extends FragmentPagerAdapter {

	ViewPagerEventsModel model;
	
	OnEventClickedListener onEventClickedListener;
	
	public void setOnEventClickedListener(
			OnEventClickedListener onEventClickedListener) {
		this.onEventClickedListener = onEventClickedListener;
	}
	
	public FragmentPagerAdapterEvents(FragmentManager fm) {
		super(fm);
	}
	
	@Override
	public Fragment getItem(int pos) {
		FragmentEvent f = new FragmentEvent();
		Bundle b = new Bundle();
		b.putSerializable("events", (Serializable) model.keyToEvents.get(model.days.get(pos)));
		b.putSerializable("day", model.days.get(pos));
		f.setOnEventClickedListener(onEventClickedListener);
		f.setArguments(b);
		return f;
	}

	@Override
	public int getCount() {
		if(model==null) {
			return 0;
		}
		return model.days.size();
	}
	
	@Override
	public CharSequence getPageTitle(int position) {
		return model.getTitle(position);
	}

	public void setData(ViewPagerEventsModel m) {
		this.model = m;
	}

}
