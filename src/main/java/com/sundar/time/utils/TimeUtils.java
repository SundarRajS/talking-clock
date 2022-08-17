package com.sundar.time.utils;

import com.sundar.time.constants.ApplicationConstants;
import com.sundar.time.exception.ApplicationException;
import com.sundar.time.exception.BusinessException;

public interface TimeUtils {

	public static String getValidTime(String time) throws ApplicationException {
		if (time == null) {
			throw new BusinessException(600, "Invalid Arguments, Please check your input");
		}
		try {
			String[] timeArr = time.split(ApplicationConstants.DELIMITER);
			int hours = Integer.parseInt(timeArr[0]);
			int minutes = Integer.parseInt(timeArr[1]);
			if (hours > 12) {
				hours = hours % 12;
			}
			return hours + ":" + minutes;
		} catch (NumberFormatException e) {
			throw new BusinessException(600, "Invalid Arguments, Please check your input");
		} catch (Exception e) {
			throw new ApplicationException(500, "Something went wrong !!  " + e.getMessage());
		}
	}

}
