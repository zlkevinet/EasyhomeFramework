/**
 * Copyright
 */
package com.easyhome.framework.action;

import com.easyhome.framework.module.ModuleManager;

/**
 * 动作发送者
 * @author zhoulu
 * @since 2012-11-9-上午12:19:57
 * @version 1.0
 */
public class ActionDispatcher {

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
		if(action instanceof LinkedAction){
			action.send();
		} else {
			mModuleManager.dispatchAction(action);
		}
	}

}
