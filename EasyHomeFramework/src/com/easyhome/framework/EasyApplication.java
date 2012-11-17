/**
 * Copyright
 */
package com.easyhome.framework;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.easyhome.framework.action.ActionManager;
import com.easyhome.framework.util.log.Loger;

/**
 * 
 * @author zhoulu
 * @since 2012-11-10-下午9:33:26
 * @version 1.0
 */
@SuppressLint("NewApi")
public abstract class EasyApplication extends Application {

	
	private static EasyApplication mApplication;

	public static EasyApplication instance() {
		return mApplication;
	}

	public EasyApplication() {
		mApplication = this;
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
		//第一次时间初始化动作管理者
		ActionManager.getInstance();
		Loger.getInstance();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
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
	
	public static Application getApplication(){
		return mApplication;
	}

}
