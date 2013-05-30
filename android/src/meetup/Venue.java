package meetup;

import java.io.Serializable;

import org.json.JSONObject;

import com.google.gson.annotations.SerializedName;

public class Venue implements Serializable{

	public Venue(JSONObject o) {
		zip = o.optString("zip");
		phone = o.optString("phone");
		lon = o.optDouble("lon");
		state = o.optString("state");
		address1 = o.optString("address_1");
		address2 = o.optString("address_2");
		address3 = o.optString("address_3");
		city = o.optString("city");
		country = o.optString("country");
		id = o.optLong("id");
		repinned = o.optBoolean("repinned");
		name = o.optString("name");
		lat = o.optDouble("lat");		
	}
	
	private boolean repinned;
	private String zip;
	private String phone;
	private Double lon;
	private Double lat;
	@SerializedName("address_1")
	private String address1;
	@SerializedName("address_2")
	private String address2;
	@SerializedName("address_3")
	private String address3;
	private String city;
	private String state;
	private String country;
	private String name;
	private Long id;

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
	@Override
	public String toString()
	{
		return ToStringBuilder.build(this);
	}

}
