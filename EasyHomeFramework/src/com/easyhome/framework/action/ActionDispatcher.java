/**
 * Copyright
 */
package com.easyhome.framework.action;

import com.easyhome.framework.module.ModuleManager;
import com.easyhome.framework.util.log.Loger;

/**
 * 动作发送者
 * @author zhoulu
 * @since 2012-11-9-上午12:19:57
 * @version 1.0
 */
public class ActionDispatcher {

	private static final boolean DEBUG = true;

	private static final String TAG = ActionDispatcher.class.getSimpleName();

	private ModuleManager mModuleManager;
	
	private static ActionDispatcher mActionDispatcher;
	private ActionDispatcher(){
		mModuleManager = ModuleManager.getInstance();
	}
	public static ActionDispatcher getInstance() {
		if(mActionDispatcher == null){
			mActionDispatcher = new ActionDispatcher();
		}
		return mActionDispatcher;
	}
	
	public synchronized void dispatchAction(IAction action) {
		if(action == null){
			if(DEBUG){
				Loger.w(TAG, "dispatchAction action is null");
			}
			return;
		}
		if(DEBUG){
			Loger.d(TAG, "dispatchAction " + action.getActionName() + " ...");
		}
		if(action instanceof LinkedAction){
			if(DEBUG){
				Loger.d(TAG, "linkedAction send  " + action.getActionName() + " ...");
			}
			action.send();
		} else {
			if(DEBUG){
				Loger.d(TAG, "do module send  " + action.getActionName() + " ...");
			}
			mModuleManager.dispatchAction(action);
		}
	}

}
