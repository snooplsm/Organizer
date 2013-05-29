
package meetup;

import java.util.Calendar;

import com.google.gson.annotations.SerializedName;

public class Photo
{
	private String albumTitle;
	private String link;
	private String albumId;
	private String memberUrl;
	
	@SerializedName("desc")
	private String description;
	
	private Calendar created;
	
	public String getAlbumTitle()
	{
		return albumTitle;
	}



	public void setAlbumTitle(String albumTitle)
	{
		this.albumTitle = albumTitle;
	}



	public String getLink()
	{
		return link;
	}



	public void setLink(String link)
	{
		this.link = link;
	}



	public String getAlbumId()
	{
		return albumId;
	}



	public void setAlbumId(String albumId)
	{
		this.albumId = albumId;
	}



	public String getMemberUrl()
	{
		return memberUrl;
	}



	public void setMemberUrl(String memberUrl)
	{
		this.memberUrl = memberUrl;
	}



	public String getDescription()
	{
		return description;
	}



	public void setDescription(String description)
	{
		this.description = description;
	}



	public Calendar getCreated()
	{
		return created;
	}



	public void setCreated(Calendar created)
	{
		this.created = created;
	}



	public String toString()
	{
		return ToStringBuilder.build(this);
	}
}
