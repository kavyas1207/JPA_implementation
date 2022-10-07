package com.sample.project.constants;
import org.springframework.stereotype.Component;
@Component
public class Buyer_URI {
	private Buyer_URI() {
		// TODO Auto-generated constructor stub
	}
	public static final String BUYER_DETAILS="/buyer-details";
	public static final String BUYER_DETAILS_BY_NO="/buyer-details/{contactno}";
	public static final String BUYER_DETAILS_ADD="/buyer-details/add";
	public static final String BUYER_DETAILS_UPDATE="/buyer-details/{contactno}";
	public static final String BUYER_DETAILS_DELETE="/buyer-details/{contactno}";
	public static final String Buyer_details="/buyer-details/";

}





