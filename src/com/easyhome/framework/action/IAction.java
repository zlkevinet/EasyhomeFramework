//project copyright
package com.easyhome.framework.action;

import android.os.Bundle;

/**
 * 动作接口
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public interface IAction {
	
	public void send();
	public void setAction(String action);
	public String getActionName();
	public void setActionCallback(ActionCallback callback);
	public ActionCallback getActionCallback();
	
	public void setBundle(Bundle bundle);
	public Bundle getBundle();
}
