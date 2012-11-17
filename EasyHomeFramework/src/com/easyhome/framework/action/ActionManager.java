/**
 * Copyright
 */
package com.easyhome.framework.action;

/**
 * 管理action的注册、发送、返回的逻辑
 * 
 * @author zhoulu
 * @since 2012-11-9-上午12:19:16
 * @version 1.0
 */
public class ActionManager {

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
		mActionQueue.putAction(action);
	}

}
