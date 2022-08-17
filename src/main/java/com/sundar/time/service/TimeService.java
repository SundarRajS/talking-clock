package com.sundar.time.service;

import com.sundar.time.exception.ApplicationException;
import com.sundar.time.model.Time;

public interface TimeService {

	public Time convertTimeToWords(String timeArgs) throws ApplicationException;
	
}
