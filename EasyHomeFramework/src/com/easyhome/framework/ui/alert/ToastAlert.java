/**
 * Copyright
 */
package com.easyhome.framework.ui.alert;

import android.content.Context;
import android.widget.Toast;

/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午12:58:36
 * @version 1.0
 */
public class ToastAlert {

	public static void showToast(Context context, int resId) {
		Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
	}

}
