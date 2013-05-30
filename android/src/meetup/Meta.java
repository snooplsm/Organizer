package meetup;

import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

public class Meta {

	public Meta(JSONObject o) {
		lon = o.optString("lon");
		count = o.optInt("count");
		signedUrl = o.optString("signed_url");
		link = o.optString("link");
		next = o.optString("next");
		totalCount = o.optInt("total_count");
		url = o.optString("url");
		id = o.optString("id");
		title = o.optString("title");
		updated = o.optLong("updated");
		description = o.optString("description");
		method = o.optString("method");
		lat = o.optString("lat");
	}
	
	public Meta() {
		
	}
	String lon;
	int count;
	@SerializedName("signed_url")
	String signedUrl;
	String link;
	String next;
	@SerializedName("total_count")
	int totalCount;
	String url;
	String id;
	String title;
	long updated;
	String description;
	String method;
	String lat;
	public String getLon() {
		return lon;
	}
	public int getCount() {
		return count;
	}
	public String getSignedUrl() {
		return signedUrl;
	}
	public String getLink() {
		return link;
	}
	public String getNext() {
		return next;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public String getUrl() {
		return url;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public long getUpdated() {
		return updated;
	}
	public String getDescription() {
		return description;
	}
	public String getMethod() {
		return method;
	}
	public String getLat() {
		return lat;
	}
	
}
