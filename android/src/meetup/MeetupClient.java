package meetup;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.OAuthProvider;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.basic.DefaultOAuthProvider;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.exception.OAuthNotAuthorizedException;
import oauth.signpost.http.HttpRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class MeetupClient {

	OAuthConsumer consumer;

	OAuthProvider provider;
	
	Gson gson;
	
	private static final String EVENTS = "https://api.meetup.com/2/events";
	private static final String GROUPS = "https://api.meetup.com/2/groups";
	private static final String MEMBERS = "https://api.meetup.com/2/members";
	private static final String RSVPS = "https://api.meetup.com/2/rsvps";

	public MeetupClient(String consumerKey, String consumerSecret) {
		consumer = new DefaultOAuthConsumer(consumerKey, consumerSecret);
		provider = new DefaultOAuthProvider(
				"https://api.meetup.com/oauth/request",
				"https://api.meetup.com/oauth/access",
				"https://api.meetup.com/oauth/authorize");
		gson = new GsonBuilder().create();
	}

	private void setAccessToken(String token, String tokenSecret) {
		consumer.setTokenWithSecret(token, tokenSecret);
	}

	public String getLoginUrlSync() throws OAuthMessageSignerException,
			OAuthNotAuthorizedException, OAuthExpectationFailedException,
			OAuthCommunicationException, UnsupportedEncodingException {
		String tokenUrl = provider.retrieveRequestToken(consumer,
				"http://wmwm.us/organizer");
		Uri uri = Uri.parse(tokenUrl);
		String token = uri.getQueryParameter("oauth_token");
		String authorizeUrl = "http://www.meetup.com/authorize/?oauth_token="
				+ token;
		return authorizeUrl;
	}

	public String getAccessToken(String verifier)
			throws OAuthMessageSignerException, OAuthNotAuthorizedException,
			OAuthExpectationFailedException, OAuthCommunicationException {
		provider.retrieveAccessToken(consumer, verifier);
		return consumer.getTokenSecret();
	}

	public void saveSession(Editor edit) {
		edit.putString("oauthTokenSecret", consumer.getTokenSecret());
		edit.putString("oauthToken", consumer.getToken());
		edit.commit();
	}

	public boolean restoreSession(SharedPreferences prefs) {
		String tokenSecret = prefs.getString("oauthTokenSecret", null);
		String token = prefs.getString("oauthToken", null);
		if (!TextUtils.isEmpty(tokenSecret)) {
			setAccessToken(token, tokenSecret);
			return true;
		}
		return false;
	}
	
	public MeetupResponse<Rsvp> getRsvps(RsvpSearchCriteria c) throws Exception {
		HttpURLConnection conn = createConnection(RSVPS,c.getParameterMap());
		HttpRequest req = sign(conn);
		Type type = new TypeToken<MeetupResponse<Rsvp>>(){}.getType();
		MeetupResponse<Rsvp> events = gson.fromJson(new InputStreamReader(conn.getInputStream()), type);
		return events;
	}
	
	public JSONObject getRsvpsObject(RsvpSearchCriteria c) throws Exception {
		HttpURLConnection conn = createConnection(RSVPS,c.getParameterMap());
		HttpRequest req = sign(conn);
		Type type = new TypeToken<MeetupResponse<Rsvp>>(){}.getType();
		StringBuilder b = new StringBuilder();
		String line = null;
		BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while((line = r.readLine())!=null) {
			b.append(line);
			b.append("\n");
		}
		
		return new JSONObject(b.toString());
	}

	public MeetupResponse<Event> getEvents(EventSearchCriteria c) throws Exception {
		JSONObject events = getEventsObject(c);
		
		List<Event> _events = null;
		if(events.has("results")) {
			JSONArray result = events.optJSONArray("results");
			List<Event> e = new ArrayList<Event>(result.length());
			for(int i = 0; i < result.length(); i++) {
				e.add(new Event(result.optJSONObject(i)));
			}
			_events = e;
		}
		Meta meta = null;
		if(events.has("meta")) {
			meta = new Meta(events.optJSONObject("meta"));
		}
		
		return new MeetupResponse<Event>(_events,meta);
	}
	
	public JSONObject getEventsObject(EventSearchCriteria c) throws Exception {
		HttpURLConnection conn = createConnection(EVENTS,c.getParameterMap());
		HttpRequest req = sign(conn);
		System.out.println(req.getRequestUrl());
		conn.connect();
		int resp = conn.getResponseCode();
		Type type = new TypeToken<MeetupResponse<Event>>(){}.getType(); 
		StringBuilder b = new StringBuilder();
		String line = null;
		BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while((line = r.readLine())!=null) {
			b.append(line);
			b.append("\n");
		}
		return new JSONObject(b.toString());
	}
	
	public MeetupResponse<Member> getSelf() throws Exception {
		HttpURLConnection conn = createConnection(MEMBERS,Collections.singletonMap("member_id", "self"));
		HttpRequest req = sign(conn);
		System.out.println(req.getRequestUrl());
		conn.connect();
		int resp = conn.getResponseCode();
		Type type = new TypeToken<MeetupResponse<Member>>(){}.getType(); 
//		StringBuilder b = new StringBuilder();
//		String line = null;
//		BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		while((line = r.readLine())!=null) {
//			b.append(line);
//			b.append("\n");
//		}
//		System.out.println(b.toString());
		MeetupResponse<Member> members = gson.fromJson(new InputStreamReader(conn.getInputStream()), type);
		return members;
	}
	
	public MeetupResponse<Group> getGroups(GroupSearchCriteria c) throws Exception {
		HttpURLConnection conn = createConnection(GROUPS,c.getParameterMap());
		HttpRequest req = sign(conn);
		System.out.println(req.getRequestUrl());
		conn.connect();
		int resp = conn.getResponseCode();
		Type type = new TypeToken<MeetupResponse<Group>>(){}.getType(); 
//		StringBuilder b = new StringBuilder();
//		String line = null;
//		BufferedReader r = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//		while((line = r.readLine())!=null) {
//			b.append(line);
//			b.append("\n");
//		}
//		System.out.println(b.toString());
		MeetupResponse<Group> groups = gson.fromJson(new InputStreamReader(conn.getInputStream()), type);
		return groups;
	}
	
	private HttpRequest sign(HttpURLConnection conn) throws Exception{
		return consumer.sign(conn);
	}

	private HttpURLConnection createConnection(String url,
			Map<String, String> params) throws Exception {
		
		Uri uri = Uri.parse(url);
		Uri.Builder b = uri.buildUpon();
		if (params != null) {
			for (Map.Entry<String, String> e : params.entrySet()) {
				b.appendQueryParameter(e.getKey(), e.getValue());
			}
		}
		URL u = new URL(b.build().toString());
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		return conn;
	}

}
