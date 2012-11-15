//project copyright
package com.easyhome.framework.action;
/**
 * 动作接口
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public interface IAction {
	
	public void send(String action);
	
	public void setActionCallback(ActionCallback callback);
	
}
