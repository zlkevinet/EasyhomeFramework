/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier;

import android.content.Context;



/**
 * 通知者界面接口
 * @author zhoulu
 * @since 2012-11-9-上午12:56:22
 * @version 1.0
 */
public interface INotifier {

	public void show();
	
	public void dismiss();
	
	public void init(Context context, INotifierContent content);
}
