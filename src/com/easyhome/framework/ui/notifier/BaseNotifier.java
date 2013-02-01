/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier;

import android.content.Context;


/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午12:56:44
 * @version 1.0
 */
public abstract class BaseNotifier implements INotifier {

	@Override
	public void init(Context context, INotifierContent content) {
		//do something common 
	}

}
