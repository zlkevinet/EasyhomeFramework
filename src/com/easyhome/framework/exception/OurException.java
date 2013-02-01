/**
 * Copyright
 */
package com.easyhome.framework.exception;

/**
 * 异常的基类
 * @author zhoulu
 * @since 2012-11-9-上午12:37:48
 * @version 1.0
 */
public class OurException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OurException() {
		super();
	}

	public OurException(String detailMessage, Throwable throwable) {
		super(detailMessage, throwable);
	}

	public OurException(String detailMessage) {
		super(detailMessage);
	}

	public OurException(Throwable throwable) {
		super(throwable);
	}

	
}
