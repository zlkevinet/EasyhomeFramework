/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier.build;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.view.animation.Animation;

/**
 * 
 * @author zhoulu
 * @since 2012-11-21-下午1:57:29
 * @version 1.0
 */
public class LoadingContent extends NotifierContent {
	private Dialog dialog;
	private Bitmap bitmap;
	private Animation animation;
	public Dialog getDialog() {
		return dialog;
	}
	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	public Animation getAnimation() {
		return animation;
	}
	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
}
