package com.sundar.time.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.sundar.time.model.Time;
import com.sundar.time.service.TimeService;

@WebMvcTest(TimeController.class)
public class TimeControllerTest {

	@MockBean
	TimeService timeService;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void showReturnHoursAndMinutes() throws Exception {
		Time inWords = new Time();
		inWords.setTimeInWords("eleven'O clock");
		Mockito.when(timeService.convertTimeToWords("11:00")).thenReturn(inWords);
		mockMvc.perform(get("/time-to-words?time=11:00")).andExpect(status().isOk())
				.andExpect(jsonPath("$.time-in-words", Matchers.is(inWords.getTimeInWords())));
	}

	@Test
	public void showReturnDefaultHoursAndMinutes() throws Exception {
		int hours = LocalDateTime.now().getHour() % 12;
		int minutes = LocalDateTime.now().getMinute();
		String systemTime = hours + ":" + minutes;
		Time inWords = new Time();
		inWords.setTimeInWords("eleven'O clock");
		Mockito.when(timeService.convertTimeToWords(systemTime)).thenReturn(inWords);
		mockMvc.perform(get("/time-to-words")).andExpect(status().isOk())
				.andExpect(jsonPath("$.time-in-words", Matchers.is(inWords.getTimeInWords())));
	}

}
