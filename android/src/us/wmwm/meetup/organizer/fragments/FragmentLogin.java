package us.wmwm.meetup.organizer.fragments;

import java.util.concurrent.Future;

import meetup.MeetupClient;
import meetup.MeetupResponse;
import meetup.Member;
import us.wmwm.meetup.organizer.R;
import us.wmwm.meetup.organizer.util.ThreadHelper;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentLogin extends SherlockFragment {
	
	public static interface OnLoginListener {
		void onLogin();
	};

	WebView webView;
	
	Future<?> requestToken,verifyTokenFuture;
	
	String tokenUrl;
	String token;
	String tokenSecret;
	String verifier;
	
	MeetupClient client;
	
	OnLoginListener onLoginListener;
	
	Handler handler = new Handler();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View root = inflater.inflate(R.layout.fragment_login, container, false);
		webView = (WebView) root.findViewById(R.id.webview);
		webView.getSettings().setJavaScriptEnabled(true);

		webView.setWebChromeClient(new WebChromeClient() {
			
		});
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				if(url.startsWith("http://wmwm.us/organizer")) {
					Uri uri = Uri.parse(url);
					verifier = uri.getQueryParameter("oauth_verifier");
					verifyToken();
					return true;
				}
				return super.shouldOverrideUrlLoading(view, url);
			}
		});
		return root;
	}
	
	
	private Runnable verifyTokenRunnable = new Runnable() {
		public void run() {
			try {
				tokenSecret = client.getAccessToken(verifier);
				MeetupResponse<Member> members = client.getSelf();
				Member me = members.getResults().get(0);
				PreferenceManager.getDefaultSharedPreferences(getActivity()).edit().putString("member_id", me.getId()).commit();
				handler.post(new Runnable() {
					@Override
					public void run() {
						if(onLoginListener!=null) {
							onLoginListener.onLogin();
						}
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	};
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		requestToken();
	}
	
	private void verifyToken() {
		if(verifyTokenFuture!=null) {
			verifyTokenFuture.cancel(true);
		}
		verifyTokenFuture = ThreadHelper.getScheduler().submit(verifyTokenRunnable);
	}
	
	private void requestToken() {
		if(requestToken!=null) {
			requestToken.cancel(true);
		}
		requestToken = ThreadHelper.getScheduler().submit(requestTokenRunnable);
	}
	
	Runnable requestTokenRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				tokenUrl = client.getLoginUrlSync();
				handler.post(setUrlRunnable);
			} catch (Exception e) {
				handleError(e);
			} 
		}
	};
	
	Runnable setUrlRunnable = new Runnable() {
		public void run() {
			webView.loadUrl(tokenUrl);
		};
	};
	
	private void handleError(Exception e) {
		throw new RuntimeException(e);
	}
	
	public void setClient(MeetupClient client) {
		this.client = client;
	}
	
	public void setOnLoginListener(OnLoginListener onLoginListener) {
		this.onLoginListener = onLoginListener;
	}
	
}
