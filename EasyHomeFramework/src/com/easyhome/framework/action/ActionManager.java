/**
 * Copyright
 */
package com.easyhome.framework.action;

import com.easyhome.framework.util.log.Loger;

/**
 * 管理action的注册、发送、返回的逻辑
 * 
 * @author zhoulu
 * @since 2012-11-9-上午12:19:16
 * @version 1.0
 */
public class ActionManager {

	private static final boolean DEBUG = true;

	private static final String TAG = ActionManager.class.getSimpleName();

	private static ActionManager mActionManager;

	private ActionQueue mActionQueue;
	
	private ActionManager() {
		mActionQueue = new ActionQueue();
	}

	public static ActionManager getInstance() {
		if (mActionManager == null) {
			mActionManager = new ActionManager();
		}
		return mActionManager;
	}

	public void addAction(IAction action) {
		if(DEBUG){
			Loger.d(TAG, "addAction " + action.getActionName() + " ...");
		}
		mActionQueue.putAction(action);
	}

}
