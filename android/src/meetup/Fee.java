package meetup;

import java.io.Serializable;

import org.json.JSONObject;

public class Fee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Fee(JSONObject o) {
		amount = o.optDouble("amount");
		description = o.optString("description");
		label = o.optString("label");
		required = o.optInt("required");
		accepts = o.optString("accepts");
		currency = o.optString("currency");
	}
	
	public Fee() {
		
	}
	
	Double amount;
	String description;
	String label;
	Integer required;
	String accepts;
	String currency;
	
	public String getAccepts() {
		return accepts;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public String getCurrency() {
		return currency;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Integer getRequired() {
		return required;
	}

}
