/**
 * Copyright
 */
package com.easyhome.framework;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;

import com.easyhome.framework.action.ActionManager;
import com.easyhome.framework.util.log.Loger;

/**
 * Application的抽象类
 * @author zhoulu
 * @since 2012-11-10-下午9:33:26
 * @version 1.0
 */
@SuppressLint("NewApi")
public abstract class EasyApplication extends Application {
	
	private static final boolean DEBUG = true;
	private static final String TAG = "EasyApplication";
	private static EasyApplication mApplication;

	@Override
	public void onCreate() {
		super.onCreate();
		if(DEBUG){
			Loger.d(TAG, "onCreate");
		}
		mApplication = this;
		
		//第一次时间初始化动作管理者
		ActionManager.getInstance();
		Loger.getInstance();
	}

	/**
	 * 应用程序最小化
	 * 
	 * @param activity
	 *            活动
	 */
	public void mininum(final Activity activity) {
		try {
			activity.moveTaskToBack(true);
		} catch (Exception e) {
			activity.finish();
		}
	}

	/**
	 * 安全退出应用程序
	 */
	public void exit() {

		if (android.os.Build.VERSION.SDK_INT <= android.os.Build.VERSION_CODES.ECLAIR_MR1) {
			ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
			am.killBackgroundProcesses(getPackageName());

		} else {
			android.os.Process.killProcess(android.os.Process.myPid());
		}

	}
	
	/**
	 * 获得Context
	 * @return
	 */
	public static Context getContext(){
		return mApplication.getApplicationContext();
	}
	
}
