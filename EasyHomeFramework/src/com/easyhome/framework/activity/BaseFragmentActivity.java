/**
 * Copyright
 */
package com.easyhome.framework.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.IModule;
import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleType;

/**
 * 支持v4版本的FragmentActivity抽象类
 * @author zhoulu
 * @since 2012-11-10-下午4:41:03
 * @version 1.0
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements IActivity {
	private DecorActivity mDecorActivity;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mDecorActivity = new DecorActivity(this);
		
		mDecorActivity.onFirstLoadData();
		mDecorActivity.onInitViews();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		dismissLoading();
		if(mDecorActivity != null){
			mDecorActivity.removeAllSystemModule();
			mDecorActivity = null;
		}
	}

	/**
	 * 显示loading状态
	 */
	public void showLoading() {
		mDecorActivity.showLoading();
	}

	/**
	 * 注销loading状态
	 */
	public void dismissLoading() {
		mDecorActivity.dismissLoading();
	}

	/**
	 * 显示一个toast
	 * @param resId
	 */
	public void showToast(int resId) {
		mDecorActivity.showToast(resId);
	}

	/**
	 * 打印日志信息
	 * @param tag
	 * @param debugMsg
	 */
	public void debug(String tag, String debugMsg) {
		mDecorActivity.debug(tag, debugMsg);
	}

	/**
	 * 添加一个模块和监听
	 * @param moduleType
	 * @param watcher
	 */
	public void addSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
		mDecorActivity.addSystemModule(moduleType, watcher);
	}

	/**
	 * 获得模块
	 * @param moduleType
	 * @return
	 */
	public IModule getSystemModule(ModuleType moduleType) {
		return mDecorActivity.getSystemModule(moduleType); 
	}

	/**
	 * 移除一个模块和监听
	 * @param moduleType
	 * @param watcher
	 */
	public void removeSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
		mDecorActivity.removeSystemModule(moduleType, watcher);
	}

	@Override
	public void removeAllSystemModule() {
		mDecorActivity.removeAllSystemModule();
	}
	
	/**
	 * 发送动作命令
	 * @param action
	 */
	public void sendAction(IAction action) {
		mDecorActivity.sendAction(action);		
	}
	
}
