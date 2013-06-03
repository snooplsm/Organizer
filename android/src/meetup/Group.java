
package meetup;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

public class Group implements Serializable
{
	
	
	
	public Group(JSONObject o ) {
		id = o.optString("id");
		latitude = o.optString("group_lat");
		longitude = o.optString("group_lon");
		joinMode = o.optString("join_mode");
		urlName = o.optString("urlname");
		photoSet = new PhotoSet(o.optJSONObject("group_photo"));
		who = o.optString("who");
	}
	
	
	private String name;
	
	@SerializedName("join_mode")
	private String joinMode;
	
	private String groupPhotoCount;
	
	@SerializedName("group_urlname")
	private String urlName;
	
	private String who;
	
	private List<Topic> topics = new ArrayList<Topic>();
	
	@SerializedName("group_lon")
	private String longitude;
	@SerializedName("group_lat")
	private String latitude;
	private String state;
	private String id;
	private String zip;
	
	@SerializedName("group_photo")
	private PhotoSet photoSet;
	
	private BigDecimal rating;
	
	@SerializedName("organizerProfileURL")
	private String organizerProfileUrl;
	
	private String organizerId;
	private String organizerName;
	
	private String country;
	private String photoUrl;
	private Long created;
	private Long updated;
	private Integer members;
	private String link;
	private String city;
	private String description;
	
	@SerializedName("daysleft")
	private Integer daysLeft;
	
	public void setPhotoSet(PhotoSet photoSet) {
		this.photoSet = photoSet;
	}
	
	public PhotoSet getPhotoSet() {
		return photoSet;
	}
	
	public Group()
	{
		super();
	}

	public String getName()
	{
		return name;
	}


	public void setName(String name)
	{
		this.name = name;
	}


	public String getLongitude()
	{
		return longitude;
	}


	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}


	public String getLatitude()
	{
		return latitude;
	}


	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}


	public String getState()
	{
		return state;
	}


	public void setState(String state)
	{
		this.state = state;
	}


	public String getId()
	{
		return id;
	}


	public void setId(String id)
	{
		this.id = id;
	}


	public String getZip()
	{
		return zip;
	}


	public void setZip(String zip)
	{
		this.zip = zip;
	}


	public String getOrganizerProfileUrl()
	{
		return organizerProfileUrl;
	}


	public void setOrganizerProfileUrl(String url)
	{
		this.organizerProfileUrl = url;
	}


	public String getCountry()
	{
		return country;
	}


	public void setCountry(String country)
	{
		this.country = country;
	}


	public String getPhotoUrl()
	{
		return photoUrl;
	}


	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}
	
	public Long getCreated() {
		return created;
	}
	
	public Long getUpdated() {
		return updated;
	}
	
	public void setCreated(Long created) {
		this.created = created;
	}
	
	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public Integer getMembers()
	{
		return members;
	}


	public void setMembers(Integer members)
	{
		this.members = members;
	}


	public String getLink()
	{
		return link;
	}


	public void setLink(String link)
	{
		this.link = link;
	}


	public String getCity()
	{
		return city;
	}


	public void setCity(String city)
	{
		this.city = city;
	}


	public String getDescription()
	{
		return description;
	}


	public void setDescription(String description)
	{
		this.description = description;
	}


	public Integer getDaysLeft()
	{
		return daysLeft;
	}


	public void setDaysLeft(Integer daysLeft)
	{
		this.daysLeft = daysLeft;
	}


	
	public String getJoinMode()
	{
		return joinMode;
	}

	public void setJoinMode(String joinMode)
	{
		this.joinMode = joinMode;
	}

	public String getGroupPhotoCount()
	{
		return groupPhotoCount;
	}

	public void setGroupPhotoCount(String groupPhotoCount)
	{
		this.groupPhotoCount = groupPhotoCount;
	}

	public String getUrlName() {
		return urlName;
	}

	public String getWho()
	{
		return who;
	}

	public void setWho(String who)
	{
		this.who = who;
	}

	public List<Topic> getTopics()
	{
		return topics;
	}

	public void setTopics(List<Topic> topics)
	{
		this.topics = topics;
	}

	public BigDecimal getRating()
	{
		return rating;
	}

	public void setRating(BigDecimal rating)
	{
		this.rating = rating;
	}

	public String getOrganizerId()
	{
		return organizerId;
	}

	public void setOrganizerId(String organizerId)
	{
		this.organizerId = organizerId;
	}

	public String getOrganizerName()
	{
		return organizerName;
	}

	public void setOrganizerName(String organizerName)
	{
		this.organizerName = organizerName;
	}

	public String toString()
	{
		return ToStringBuilder.build(this);
	}
}
