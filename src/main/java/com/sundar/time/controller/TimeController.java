package com.sundar.time.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sundar.time.exception.ApplicationException;
import com.sundar.time.model.Time;
import com.sundar.time.service.TimeService;
import com.sundar.time.utils.TimeUtils;

@RestController
@RequestMapping(produces = "application/json")
public class TimeController {

	@Autowired
	private TimeService timeService;

	@GetMapping(path = "/time-to-words")
	public ResponseEntity<?> getTimeInWords(@RequestParam(required = false) String time) throws ApplicationException {
		Time timeInWords = null;
		if (time != null) {
			timeInWords = timeService.convertTimeToWords(TimeUtils.getValidTime(time));
		} else {
			String systemTime = LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute();
			timeInWords = timeService.convertTimeToWords(systemTime);
		}
		return new ResponseEntity<Time>(timeInWords, HttpStatus.OK);
	}

}
