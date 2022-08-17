package com.sundar.time.service;

import org.springframework.stereotype.Service;

import com.sundar.time.constants.ApplicationConstants;
import com.sundar.time.exception.ApplicationException;
import com.sundar.time.exception.BusinessException;
import com.sundar.time.model.Time;

@Service
public class TimeServiceImpl implements TimeService {

	public Time convertTimeToWords(String timeArgs) throws ApplicationException {
		Time time = null;
		try {
			if (timeArgs == null || !timeArgs.contains(ApplicationConstants.DELIMITER)) {
				throw new BusinessException(600, "Invalid Arguments, Please check your input");
			}
			int hours = Integer.parseInt(timeArgs.split(":")[0]);
			int minutes = Integer.parseInt(timeArgs.split(":")[1]);
			if (hours > 24 || minutes > 60) {
				throw new BusinessException(600, "Invalid Arguments, Please check your input");
			}
			time = new Time();
			if ((hours == 0 && minutes == 0) || (hours == 24 && minutes == 0)) {
				String message = "twelve'O clock";
				time.setTimeInWords(message);
			} else if (minutes == 0) {
				String message = ApplicationConstants.TIME_TO_WORDS_KEY.get(hours) + "'O clock";
				time.setTimeInWords(message);
			} else if (minutes == 1) {
				String message = "one past " + ApplicationConstants.TIME_TO_WORDS_KEY.get(hours);
				time.setTimeInWords(message);
			} else if (minutes == 59) {
				hours = (hours % 12) + 1;
				String message = "one minute to " + ApplicationConstants.TIME_TO_WORDS_KEY.get(hours);
				time.setTimeInWords(message);
			} else if (minutes == 15) {
				String message = "quarter past " + ApplicationConstants.TIME_TO_WORDS_KEY.get(hours);
				time.setTimeInWords(message);
			} else if (minutes == 30) {
				String message = "Half past " + ApplicationConstants.TIME_TO_WORDS_KEY.get(hours);
				time.setTimeInWords(message);
			} else if (minutes == 45) {
				hours = (hours % 12) + 1;
				String message = "quarter past to " + ApplicationConstants.TIME_TO_WORDS_KEY.get(hours);
				time.setTimeInWords(message);
			} else if (minutes <= 30) {
				String message = ApplicationConstants.TIME_TO_WORDS_KEY.get(minutes) + " past "
						+ ApplicationConstants.TIME_TO_WORDS_KEY.get(hours);
				time.setTimeInWords(message);
			} else {
				minutes = (60 - minutes);
				hours = (hours % 12) + 1;
				String message = ApplicationConstants.TIME_TO_WORDS_KEY.get(minutes) + " to "
						+ ApplicationConstants.TIME_TO_WORDS_KEY.get(hours);
				time.setTimeInWords(message);
				return time;
			}
		} catch (NumberFormatException e) {
			throw new BusinessException(600, "Invalid Arguments, Please check your input");
		}
		return time;

	}

}
