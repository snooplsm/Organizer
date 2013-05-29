package meetup;

import java.util.List;

public class MeetupResponse<T> {

	public MeetupResponse(List<T> results, Meta meta) {
		this.results = results;
		this.meta = meta;
	}
	
	private List<T> results;
	
	private Meta meta;

	public Meta getMeta() {
		return meta;
	}

	public List<T> getResults() {
		return results;
	}
	
}
