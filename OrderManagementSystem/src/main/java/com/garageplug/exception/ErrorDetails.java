package com.garageplug.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorDetails {

	private LocalDateTime timeStamp;
	private String message;
	private String Description;
	
}
