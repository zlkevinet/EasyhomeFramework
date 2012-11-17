/**
 * Copyright
 */
package com.easyhome.framework.activity;

import android.app.ListActivity;
import android.os.Bundle;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.IModule;
import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleManager;
import com.easyhome.framework.module.ModuleType;

/**
 * ListActivity 抽象类
 * @author zhoulu
 * @since 2012-11-10-下午4:39:14
 * @version 1.0
 */
public abstract class BaseListActivity extends ListActivity implements IActivity {

	private ModuleManager mModuleManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mModuleManager = ModuleManager.getInstance();
	}
	
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
	public void addSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
		mModuleManager.addModule(moduleType);
		IModule module = mModuleManager.getModule(moduleType);
		if(module != null){
			module.registerWatcher(watcher);
		}
	}

	@Override
	public IModule getSystemModule(ModuleType moduleType) {
		return mModuleManager.getModule(moduleType);
	}

	@Override
	public void removeSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
		IModule module = mModuleManager.getModule(moduleType);
		if(module != null){
			module.unRegisterWatcher(watcher);
		}
	}

	@Override
	public void sendAction(IAction action) {
		
	}
}
