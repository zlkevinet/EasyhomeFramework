/**
 * Copyright E.T
 */
package com.easyhome.framework.ui.notifier;

import android.app.Activity;

import com.easyhome.framework.ui.notifier.build.Builder;
import com.easyhome.framework.ui.notifier.build.LoadingBuilder;


/**
 * 通知者
 * @author Kevin.E.T
 * @mail shuu.ro.zl@gmail.com
 * @since 2012-11-9-上午12:56:58
 * @version 1.0
 */
public class Notifier {

	private static LoadingNotifier mLoadingAlert;
	
	public static void showLoading(Activity activity){
		if(mLoadingAlert == null){
			mLoadingAlert = (LoadingNotifier) new LoadingBuilder(activity).create();
		}
	}
	
	public static void dismissLoading(Activity activity){
		
	}
	
	public static void showToast(Activity activity, int msgId) {
		
	}
	
	public static void showDialog(Activity activity, Builder builder){
		
	}
}
