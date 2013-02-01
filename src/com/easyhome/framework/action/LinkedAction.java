/**
 * Copyright
 */
package com.easyhome.framework.action;

import java.util.ArrayList;
import java.util.List;

/**
 * 动作组
 * @author zhoulu
 * @since 2012-11-9-上午12:24:57
 * @version 1.0
 */
public class LinkedAction extends BaseAction{

	private List<IAction> mActions;
	
	public LinkedAction() {
		super();
		mActions = new ArrayList<IAction>();
	}

	public void addAction(IAction action){
		if(action != null && !mActions.contains(action)){
			mActions.add(action);
		}
	}
	
	@Override
	public void send() {
		for (IAction action : mActions) {
			action.send();
		}
		mActions.clear();
	}

}
