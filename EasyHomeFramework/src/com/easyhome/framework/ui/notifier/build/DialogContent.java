/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier.build;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.view.View;

/**
 * 
 * @author zhoulu
 * @since 2012-11-21-下午2:09:44
 * @version 1.0
 */
public class DialogContent extends NotifierContent {

	private Dialog dialog;
	
	private String title;
	
	private int titleId;
	
	private Drawable homeIcon;
	
	private Drawable actionIcon;
	
	private View contentView;

	private View[] buttonViews;
	
	private View.OnClickListener[] buttonViewClickListeners;

	public Dialog getDialog() {
		return dialog;
	}

	public void setDialog(Dialog dialog) {
		this.dialog = dialog;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	public Drawable getHomeIcon() {
		return homeIcon;
	}

	public void setHomeIcon(Drawable homeIcon) {
		this.homeIcon = homeIcon;
	}

	public Drawable getActionIcon() {
		return actionIcon;
	}

	public void setActionIcon(Drawable actionIcon) {
		this.actionIcon = actionIcon;
	}

	public View getContentView() {
		return contentView;
	}

	public void setContentView(View contentView) {
		this.contentView = contentView;
	}

	public View[] getButtonViews() {
		return buttonViews;
	}

	public void setButtonViews(View[] buttonViews) {
		this.buttonViews = buttonViews;
	}

	public View.OnClickListener[] getButtonViewClickListeners() {
		return buttonViewClickListeners;
	}

	public void setButtonViewClickListeners(View.OnClickListener[] buttonViewClickListeners) {
		this.buttonViewClickListeners = buttonViewClickListeners;
	}
	
}
