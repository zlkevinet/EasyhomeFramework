/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier.build;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.animation.Animation;


/**
 * 
 * @author zhoulu
 * @since 2012-11-21-上午11:50:11
 * @version 1.0
 */
public class LoadingBuilder extends Builder {
	
	LoadingContent mContent;
	
	public LoadingBuilder(Activity activity) {
		super(activity);
		mContent = new LoadingContent();
	}

	@Override
	public LoadingContent createNotifierContent() {
		return mContent;
	}
	
	public LoadingBuilder setDialog(Dialog dialog){
		mContent.setDialog(dialog);
		return this;
	}
	
	public LoadingBuilder setBitmap(Bitmap bitmap){
		mContent.setBitmap(bitmap);
		return this;
	}
	
	public LoadingBuilder setAnimation(Animation animation){
		mContent.setAnimation(animation);
		return this;
	}
}
