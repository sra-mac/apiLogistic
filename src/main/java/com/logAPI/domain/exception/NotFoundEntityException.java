package com.logAPI.domain.exception;

public class NotFoundEntityException extends TradeException{

	private static final long serialVersionUID = 1L;

	public NotFoundEntityException(String message) {
		super(message);
	}

}
