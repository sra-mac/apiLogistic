package com.logAPI.domain.exception;

public class TradeException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public TradeException(String message) {
		super(message);
	}
}
