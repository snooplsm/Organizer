package meetup;

import java.io.Serializable;

import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

public class PhotoSet implements Serializable {

	@SerializedName("highres_link")
	private String highresLink;
	@SerializedName("photo_id")
	private String photoId;
	@SerializedName("photo_link")
	private String photoLink;
	@SerializedName("thumb_link")
	private String thumbLink;
	
	public PhotoSet(JSONObject o ) {
		thumbLink = o.optString("thumb_link");
		photoId = o.optString("photo_id");
		highresLink = o.optString("highres_link");
		photoLink = o.optString("photo_link");
	}
	
	public String getHighresLink() {
		return highresLink;
	}
	public void setHighresLink(String highresLink) {
		this.highresLink = highresLink;
	}
	public String getPhotoId() {
		return photoId;
	}
	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}
	public String getPhotoLink() {
		return photoLink;
	}
	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}
	public String getThumbLink() {
		return thumbLink;
	}
	public void setThumbLink(String thumbLink) {
		this.thumbLink = thumbLink;
	}
	
	
	
}
