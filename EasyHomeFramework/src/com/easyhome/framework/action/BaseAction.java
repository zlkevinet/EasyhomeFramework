/**
 * Copyright
 */
package com.easyhome.framework.action;

import com.easyhome.framework.util.log.Loger;

import android.os.Bundle;

/**
 * 动作接口抽象
 * @author zhoulu
 * @since 2012-11-9-上午12:17:05
 * @version 1.0
 */
public abstract class BaseAction implements IAction {

	private static final boolean DEBUG = true;

	private static final String TAG = BaseAction.class.getSimpleName();

	private ActionDispatcher mActionDispatcher;
	
	private String mActionName;
	private ActionCallback mActionCallback;
	private Bundle mBundle;
	
	public BaseAction(){
		this("");
	}
	
	public BaseAction(String actionName){
		this(actionName, null);
	}
	
	public BaseAction(String actionName, Bundle bundle){
		this(actionName, bundle, null);
	}
	
	public BaseAction(String actionName, Bundle bundle, ActionCallback callback){
		mActionName = actionName;
		mBundle = bundle;
		mActionCallback = callback;
		
		mActionDispatcher = ActionDispatcher.getInstance();
	}
	
	@Override
	public void send() {
		if(DEBUG){
			Loger.d(TAG, "send action " + mActionName + " ...");
		}
		mActionDispatcher.dispatchAction(this);
	}

	@Override
	public void setAction(String action) {
		mActionName = action;
	}

	@Override
	public void setActionCallback(ActionCallback callback) {
		mActionCallback = callback;
	}

	@Override
	public String getActionName() {
		return mActionName;
	}

	@Override
	public ActionCallback getActionCallback() {
		return mActionCallback;
	}

	@Override
	public void setBundle(Bundle bundle) {
		mBundle = bundle;
	}

	@Override
	public Bundle getBundle() {
		return mBundle;
	}

}
