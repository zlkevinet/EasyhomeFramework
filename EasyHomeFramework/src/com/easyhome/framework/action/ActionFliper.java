/**
 * Copyright E.T.
 */
package com.easyhome.framework.action;

import java.util.ArrayList;
import java.util.List;

import com.easyhome.framework.util.log.Loger;

/**
 * 动作过滤
 * @creator kevin
 * @since Nov 17, 2012
 */
public class ActionFliper {

	private static final String TAG = ActionFliper.class.getSimpleName();

	private static final boolean DEBUG = true;

	private List<String> mActions;
	
	public ActionFliper(){
		mActions = new ArrayList<String>();
	}
	
	/**
	 * 判断Action是否存在
	 * @param actionName
	 * @return
	 */
	public boolean hasAction(String actionName) {
		if(actionName == null 
				|| actionName.length() == 0
				|| "".equals(actionName.trim())){
			return false;
		}
		return mActions.contains(actionName);
	}

	/**
	 * 添加Action
	 * @param actionName
	 */
	public void addAction(String actionName) {
		if(actionName == null 
				|| actionName.length() == 0
				|| "".equals(actionName.trim())){
			Loger.d(TAG, "-------- you add a empty action!");
			return;
		}
		if(DEBUG){
			Loger.d(TAG, "addAction " + actionName);
		}
		if(!mActions.contains(actionName)){
			mActions.add(actionName);
		}
	}

}
