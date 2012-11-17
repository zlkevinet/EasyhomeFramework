/**
 * Copyright
 */
package com.easyhome.framework.action;

/**
 * 动作接口抽象
 * @author zhoulu
 * @since 2012-11-9-上午12:17:05
 * @version 1.0
 */
public abstract class BaseAction implements IAction {

	@Override
	public void send(String action) {
	}

	@Override
	public void setActionCallback(ActionCallback callback) {
	}

}
