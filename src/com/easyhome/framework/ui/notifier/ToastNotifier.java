/**
 * Copyright E.T
 */
package com.easyhome.framework.ui.notifier;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Toast;

import com.easyhome.framework.ui.notifier.build.ToastContent;



/**
 * Toast通知
 * @author zhoulu
 * @since 2012-11-9-上午12:58:36
 * @version 1.0
 */
public class ToastNotifier extends BaseNotifier{

	private Toast mToast;
	
	@Override
	public void show() {
		if(mToast != null){
			mToast.show();
		}
	}

	@Override
	public void dismiss() {
		if(mToast != null){
			mToast.cancel();
			mToast = null;
		}
	}

	@SuppressLint({ "ShowToast", "ShowToast" })
	@Override
	public void init(Context context, INotifierContent content) {
		super.init(context, content);
		if(content instanceof ToastContent){
			ToastContent toastContent = (ToastContent) content;
			
			if(toastContent.getMsgId() != -1){
				mToast = Toast.makeText(context, toastContent.getMsgId(), toastContent.getDuration());
			} else if(toastContent.getMsg() != null){
				mToast = Toast.makeText(context, toastContent.getMsg(), toastContent.getDuration());
			}
			
		} else {
			throw new ClassCastException();
		}
	}

}
