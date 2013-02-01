/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier.build;

import android.app.Activity;

import com.easyhome.framework.ui.notifier.INotifier;
import com.easyhome.framework.ui.notifier.INotifierContent;

/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午12:58:13
 * @version 1.0
 */
public abstract class Builder {
	private INotifier mEmptyNotifier;
	private INotifierContent mNotifierContent;

	private Activity mActivity;

	public Builder(Activity activity) {
		mActivity = activity;
	}

	public abstract INotifierContent createNotifierContent();

	public INotifier create() {
		mNotifierContent = createNotifierContent();
		if(mNotifierContent == null){
			throw new NullPointerException("create alertcontent is null");
		}
		mEmptyNotifier.init(mActivity, mNotifierContent);
		return mEmptyNotifier;
	}

	public INotifier getNotifier() {
		return mEmptyNotifier;
	}

	public INotifierContent getNotifierContent() {
		return mNotifierContent;
	}

	public void setNotifierContent(INotifierContent mNotifierContent) {
		this.mNotifierContent = mNotifierContent;
	}

	public Activity getActivity() {
		return mActivity;
	}

}
