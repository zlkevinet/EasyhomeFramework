/**
 * Copyright
 */
package com.easyhome.framework.action;

import android.os.Bundle;

/**
 * 异步动作
 * @author kevin.E.T
 * @since 2012-11-9-上午12:17:34
 * @version 1.0
 */
public class AsyncAction extends BaseAction {

	public AsyncAction() {
		super();
	}

	public AsyncAction(String actionName, Bundle bundle, ActionCallback callback) {
		super(actionName, bundle, callback);
	}

	public AsyncAction(String actionName, Bundle bundle) {
		super(actionName, bundle);
	}

	public AsyncAction(String actionName) {
		super(actionName);
	}

	/**
	 * 异步发送
	 */
	public void asyncSend() {
		new Thread("async send thread.."){
			public void run() {
				send();
			};
		}.start();
	}
}
