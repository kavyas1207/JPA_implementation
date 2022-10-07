package com.sample.project.constants;
import org.springframework.stereotype.Component;

@Component
public class ExceptionMSG {



	private ExceptionMSG()
	{
		
	}
	public static final String BUYER_NOT_FOUND_BY_NO="Buyer details not found with the given contact_no:";
	public static final String BUYER_NOT_FOUND_BY_NAME="Buyer details not found with the given name:";
	public static final String EXISTING_BUYER="Buyer is already exist with the given contact no";
}

