package meetup;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Self {

	List<String> actions;
	
	@SerializedName("checked_in")
	Boolean checkedIn;
	
	@SerializedName("pay_status")
	String payStatus;
	
	Boolean rated;
	
	String role;
	
	Rsvp rsvp;

	public List<String> getActions() {
		return actions;
	}
	
	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public Boolean getRated() {
		return rated;
	}

	public void setRated(Boolean rated) {
		this.rated = rated;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Rsvp getRsvp() {
		return rsvp;
	}

	public void setRsvp(Rsvp rsvp) {
		this.rsvp = rsvp;
	}
	
}
