
package meetup;

import java.io.Serializable;
import java.util.List;

import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

/**
 *  
 *   Event
 *
 */
public class Event implements Serializable
{
	
	public Event(JSONObject o) {
		rsvpLimit = o.optInt("rsvp_limit");
		status = o.optString("status");
		visibility = o.optString("visibility");
		maybeRsvpCount = o.optInt("maybe_rsvp_count");
		if(o.has("venue")) {
			venue = new Venue(o.optJSONObject("venue"));
		}
		if(o.has("fee")) {
			fee = new Fee(o.optJSONObject("fee"));
		}
		
		id = o.optString("id");
		time = o.optLong("time");
		waitListCount = o.optInt("waitlist_count");
		updated = o.optLong("updated");
		yesRsvpCount = o.optInt("yes_rsvp_count");
		created = o.optLong("created");
		eventUrl = o.optString("event_url");
		description = o.optString("description");
		name = o.optString("name");
		headcount = o.optInt("headcount");
		if(o.has("group")) {
			group = new Group(o.optJSONObject("group"));
		}
		
		
	}
	
	private Long created;
	
	private Integer headcount;
	private Integer yesRsvpCount;
	
	public Event() {
		
	}
	
	Fee fee;
	
	private String visibility;
	
	private Integer waitListCount;
	
	private Float longitude;
	private Float latitude;
	
	@SerializedName("rsvpcount")
	private Integer rsvpCount;
	
	@SerializedName("maybe_rsvpcount")
	private Integer maybeRsvpCount;
	
	private Group group;
	
	private Long time;
	private Long updated;
	
	@SerializedName("event_hosts")
	private List<EventHost> eventHosts;
	
	public List<EventHost> getEventHosts() {
		return eventHosts;
	}

	public void setEventHosts(List<EventHost> eventHosts) {
		this.eventHosts = eventHosts;
	}
	@SerializedName("ismeetup")
	private Boolean isMeetup;
	
	private Boolean allowMaybeRsvp;
	
	@SerializedName("no_rsvpcount")
	private Boolean noRsvpCount;
	
	private String eventUrl;
	
	
	private Integer attendeeCount;
	
	@SerializedName("rsvp_limit")
	private Integer rsvpLimit;
	
	private String id;
	private String organizerId;
	private String organizerName;
	
	private Venue venue;
	
	private String description;
	private String photoUrl;
	
	private String groupPhotoUrl;
	
	@SerializedName("feedesc")
	private String feeDescription;
	private String questions;
	private String name;
	
	Self self;
	
	public void setGroup(Group group) {
		this.group = group;
	}
	
	public void setSelf(Self self) {
		this.self = self;
	}
	
	public Self getSelf() {
		return self;
	}
	
//	@SerializedName("myrsvp")
//	private String myRsvp;
	
	private Long rsvpCutoff;
	
	private String status;
	
	private Boolean rsvpClosed;
	
	public Float getLongitude()
	{
		return longitude;
	}
	public void setLongitude(Float longitude)
	{
		this.longitude = longitude;
	}
	public Float getLatitude()
	{
		return latitude;
	}
	public void setLatitude(Float latitude)
	{
		this.latitude = latitude;
	}
	public Integer getRsvpCount()
	{
		return rsvpCount;
	}
	public void setRsvpCount(Integer rsvpCount)
	{
		this.rsvpCount = rsvpCount;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public Long getTime()
	{
		return time;
	}
	public void setTime(Long time)
	{
		this.time = time;
	}
	public Long getUpdated()
	{
		return updated;
	}
	public void setUpdated(Long updated)
	{
		this.updated = updated;
	}
	public Boolean getIsMeetup()
	{
		return isMeetup;
	}
	public void setIsMeetup(Boolean isMeetup)
	{
		this.isMeetup = isMeetup;
	}
	public String getEventUrl()
	{
		return eventUrl;
	}
	public void setEventUrl(String eventUrl)
	{
		this.eventUrl = eventUrl;
	}
	public Integer getAttendeeCount()
	{
		return attendeeCount;
	}
	public void setAttendeeCount(Integer attendeeCount)
	{
		this.attendeeCount = attendeeCount;
	}
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}

	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getPhotoUrl()
	{
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl)
	{
		this.photoUrl = photoUrl;
	}
	public String getFeeDescription()
	{
		return feeDescription;
	}
	public void setFeeDescription(String feeDescription)
	{
		this.feeDescription = feeDescription;
	}
	public String getQuestions()
	{
		return questions;
	}
	public void setQuestions(String questions)
	{
		this.questions = questions;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}

	
//	public String getMyRsvp()
//	{
//		return myRsvp;
//	}
//	
//	public void setMyRsvp(String s)
//	{
//		this.myRsvp = s;
//	}
	
	public String toString()
	{
		return ToStringBuilder.build(this);
	}

	public Long getRsvpCutoff()
	{
		return rsvpCutoff;
	}
	
	public void setRsvpCutoff(Long c)
	{
		this.rsvpCutoff = c;
	}
	
	public boolean hasRsvpCutoff()
	{
		return (this.getRsvpCutoff() != null);
	}
		
	public String getOrganizerId()
	{
		return organizerId;
	}
	
	public void setOrganizerId(String oid)
	{
		this.organizerId = oid;
	}

	public String getStatus()
	{
		return status;
	}
	
	public void setStatus(String status)
	{
		this.status = status;
	}
	
	public boolean isRsvpClosed()
	{
		if (rsvpClosed == null)
		{
			return false;
		}
		else
		{
			return rsvpClosed.booleanValue();
		}
	}
	
	public void setRsvpClosed(Boolean rsvpClosed)
	{
		this.rsvpClosed = rsvpClosed;
	}
	
	public Integer getMaybeRsvpCount()
	{
		return maybeRsvpCount;
	}
	
	public void setMaybeRsvpCount(Integer maybeRsvpCount)
	{
		this.maybeRsvpCount = maybeRsvpCount;
	}
	
	public String getOrganizerName()
	{
		return organizerName;
	}
	public void setOrganizerName(String organizerName)
	{
		this.organizerName = organizerName;
	}

	public String getGroupPhotoUrl()
	{
		return groupPhotoUrl;
	}
	
	public void setGroupPhotoUrl(String groupPhotoUrl)
	{
		this.groupPhotoUrl = groupPhotoUrl;
	}
	
	public Boolean getAllowMaybeRsvp()
	{
		if (allowMaybeRsvp == null)
		{
			return false;
		}
		else
		{
			return allowMaybeRsvp;
		}
	}
	
	public void setAllowMaybeRsvp(Boolean allowMaybeRsvp)
	{
		this.allowMaybeRsvp = allowMaybeRsvp;
	}
	
	public Boolean getNoRsvpCount()
	{
		if (noRsvpCount == null)
		{
			return true;
		}
		
		return noRsvpCount;
	}
	
	public void setNoRsvpCount(Boolean noRsvpCount)
	{
		this.noRsvpCount = noRsvpCount;
	}
	
	public Integer getRsvpLimit()
	{
		return rsvpLimit;
	}
	
	public void setRsvpLimit(Integer limit)
	{
		this.rsvpLimit = limit;
	}
	public Venue getVenue() {
		return venue;
	}
	public void setVenue(Venue venue) {
		this.venue = venue;
	}
	
	

	
}
