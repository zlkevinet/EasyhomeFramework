/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier;

import android.app.Dialog;
import android.content.Context;

import com.easyhome.framework.ui.notifier.build.LoadingContent;




/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午12:58:26
 * @version 1.0
 */
public class LoadingNotifier extends BaseNotifier {

	private Dialog mDialog;

	@Override
	public void show() {
		mDialog.show();
	}

	@Override
	public void dismiss() {
		mDialog.dismiss();
	}

	@Override
	public void init(Context context, INotifierContent content) {
		super.init(context, content);
		if(content instanceof LoadingContent){
			LoadingContent loadingContent = (LoadingContent) content;
			if(loadingContent.getDialog() != null){
				mDialog = loadingContent.getDialog();
			} else {
				//use default
				mDialog = new Dialog(context);
			}
			
		} else {
			throw new ClassCastException();
		}
	}

}
