/**
 * Copyright
 */
package com.easyhome.framework.activity;

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

	@Override
	public void onFirstLoadData() {
	}

	@Override
	public void onInitViews() {
	}

	@Override
	public void showLoading() {
	}

	@Override
	public void dismissLoading() {
	}

	@Override
	public void showToast(int resId) {
	}

	@Override
	public void debug(String tag, String debugMsg) {
	}

	@Override
	public IModule getSystemModule(ModuleType moduleType) {
		return null;
	}

	@Override
	public void addSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
	}

	@Override
	public void removeSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
	}

	@Override
	public void sendAction(IAction action) {
	}

	
}
