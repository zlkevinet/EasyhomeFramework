/**
 * Copyright
 */
package com.easyhome.framework.ui.notifier.build;

import android.widget.Toast;

/**
 * 
 * @author zhoulu
 * @since 2012-11-21-下午1:35:14
 * @version 1.0
 */
public class ToastContent extends NotifierContent {
	
	private static final int DEFAULT_DURATION = Toast.LENGTH_SHORT;
	
	private int msgId = -1;
	private String msg;
	private int duration = DEFAULT_DURATION;
	
	public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
}
