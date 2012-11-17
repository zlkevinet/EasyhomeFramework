/**
 * Copyright
 */
package com.easyhome.framework.action;

import android.os.Bundle;

/**
 * 
 * @author zhoulu
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

	
}
