package com.sapient.coding.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

public class Utils {
	
	public static Date stringToDate(String date) {
		try {
			return !StringUtils.isEmpty(date) ? new SimpleDateFormat("MM/dd/yyyy").parse(date) : null;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

}
