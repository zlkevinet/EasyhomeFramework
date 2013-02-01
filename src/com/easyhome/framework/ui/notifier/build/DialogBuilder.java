/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier.build;

import android.app.Activity;


/**
 * 
 * @author zhoulu
 * @since 2012-11-21-上午11:34:10
 * @version 1.0
 */
public class DialogBuilder extends Builder{
	
	public DialogBuilder(Activity activity) {
		super(activity);
	}

	@Override
	public DialogContent createNotifierContent() {
		return null;
	}

}
