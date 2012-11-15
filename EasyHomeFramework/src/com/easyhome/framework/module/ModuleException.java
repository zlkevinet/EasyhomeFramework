/**
 * Copyright Baidu.Inc
 */
package com.easyhome.framework.module;

/**
 * 模块异常
 * @author zhoulu
 * @since 2012-11-10-下午6:55:15
 * @version 1.0
 */
public class ModuleException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ModuleException(String detailMessage) {
		super(detailMessage);
	}
}
