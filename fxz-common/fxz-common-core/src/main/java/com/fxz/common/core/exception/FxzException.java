package com.fxz.common.core.exception;

/**
 * @author Fxz
 * @version 1.0
 * @date 2021-11-28 19:27
 */
public class FxzException extends RuntimeException {

	private static final Long serialVersionUID = -6916154462432027437L;

	public FxzException(String message) {
		super(message);
	}

	public FxzException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
