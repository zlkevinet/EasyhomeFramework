/**
 * Copyright
 */
package com.easyhome.framework.util.log;

import android.util.Log;

/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午1:11:41
 * @version 1.0
 */
public class Loger {

	public static final boolean DEBUG = true;

	private LogCache mCache = new LogCache();
	private LogUploader mLoader = new LogUploader();
	
	private static Loger mLoger;
	
	private Loger(){
		
	}
	
	public static Loger getInstance() {
		if(mLoger == null){
			mLoger = new Loger();
		}
		return mLoger;
	}
	
	public static void e(String tag, String debugMsg, Throwable e){
		if(DEBUG){
			Log.e(tag, debugMsg, e);
		}
	}
	
	public static void d(String tag, String debugMsg) {
		if(DEBUG){
			Log.d(tag, debugMsg);
		}
	}
	
	public static void w(String tag, String debugMsg) {
		if(DEBUG){
			Log.w(tag, debugMsg);
		}
	}
	
	/**
	 * 统计记录
	 * @param type
	 */
	public void log(FilterType type) {
		mCache.add(type);
	}
	
	/**
	 * 发送统计记录
	 */
	public void sendLog() {
		mLoader.push(mCache);
	}

}
