/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier.build;

import android.app.Activity;

/**
 * 
 * @author zhoulu
 * @since 2012-11-21-下午1:27:30
 * @version 1.0
 */
public class ToastBuilder extends Builder{

	private ToastContent mToastContent;
	
	public ToastBuilder(Activity activity) {
		super(activity);
		
		mToastContent = new ToastContent();
	}

	@Override
	public ToastContent createNotifierContent() {
		return mToastContent;
	}
	
	public ToastBuilder setText(int textId){
		mToastContent.setMsgId(textId);
		return this;
	}
	
	public ToastBuilder setText(String text){
		mToastContent.setMsg(text);
		return this;
	}
	
	public ToastBuilder setDuration(int duration){
		mToastContent.setDuration(duration);
		return this;
	}
	
}
