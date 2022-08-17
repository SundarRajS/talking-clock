package com.sundar.time.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sundar.time.exception.ApplicationException;
import com.sundar.time.exception.BusinessException;
import com.sundar.time.model.Time;

@ExtendWith(MockitoExtension.class)
public class TimeServiceTest {

	private TimeService service;

	@BeforeEach
	void initUseCase() {
		service = new TimeServiceImpl();
	}

	@Test
	public void testConvertTimeToWords() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("11:00");
		assertEquals(timeInWords.getTimeInWords(), "eleven'O clock");
	}

	@Test
	public void testConvertTimeToWordsQuarterPast() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("12:15");
		assertEquals(timeInWords.getTimeInWords(), "quarter past twelve");
	}

	@Test
	public void testConvertTimeToWordsHalfPast() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("12:30");
		assertEquals(timeInWords.getTimeInWords(), "Half past twelve");
	}

	@Test
	public void testConvertTimeToWordsQuarterToPast() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("12:45");
		assertEquals(timeInWords.getTimeInWords(), "quarter past to one");
	}

	@Test
	public void testConvertTimeToWordspastZero() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("12:29");
		assertEquals(timeInWords.getTimeInWords(), "twenty nine past twelve");
	}

	@Test
	public void testConvertTimeToWordsTwoPastToOne() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("12:58");
		assertEquals(timeInWords.getTimeInWords(), "two to one");
	}

	@Test
	public void testConvertTimeToWordsTwelveOClock1() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("12:00");
		assertEquals(timeInWords.getTimeInWords(), "twelve'O clock");
	}

	@Test
	public void testConvertTimeToWordsTwelveOClock2() throws ApplicationException {
		Time timeInWords = service.convertTimeToWords("24:00");
		assertEquals(timeInWords.getTimeInWords(), "twelve'O clock");
	}

	@Test
	public void testConvertTimeInvalidArgs() {
		BusinessException thrown = assertThrows(BusinessException.class, () -> {
			service.convertTimeToWords("13:65");
		});
		assertEquals(thrown.getErrorMessage(), "Invalid Arguments, Please check your input");
	}

	@Test
	public void testConvertTimeInvalidTime() {
		BusinessException thrown = assertThrows(BusinessException.class, () -> {
			service.convertTimeToWords("null");
		});
		assertEquals(thrown.getErrorMessage(), "Invalid Arguments, Please check your input");
	}

	@Test
	public void testConvertTimeInvalidTimeNullArgs() {
		BusinessException thrown = assertThrows(BusinessException.class, () -> {
			service.convertTimeToWords(null);
		});
		assertEquals(thrown.getErrorMessage(), "Invalid Arguments, Please check your input");
	}
}
