/**
 * Copyright
 */
package com.easyhome.framework.activity;

import android.app.Activity;
import android.os.Bundle;

import com.easyhome.framework.action.ActionManager;
import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.IModule;
import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleManager;
import com.easyhome.framework.module.ModuleType;
import com.easyhome.framework.ui.alert.LoadingAlert;
import com.easyhome.framework.ui.alert.ToastAlert;
import com.easyhome.framework.util.log.Loger;

/**
 * 普通Activity抽象类
 * @author zhoulu
 * @since 2012-11-10-下午4:42:16
 * @version 1.0
 */
public abstract class BaseActivity extends Activity implements IActivity {

	private LoadingAlert mLoadingAlert;
	
	private ModuleManager mModuleManager;
	private ActionManager mActionManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mModuleManager = ModuleManager.getInstance();
		mActionManager = ActionManager.getInstance();
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
	}

	@Override
	public void onFirstLoadData() {
	}

	@Override
	public void onInitViews() {
	}

	@Override
	public void showLoading() {
		if(mLoadingAlert == null){
			mLoadingAlert = new LoadingAlert(this);
			mLoadingAlert.show();
		}
	}

	@Override
	public void dismissLoading() {
		if(mLoadingAlert != null){
			mLoadingAlert.dismiss();
			mLoadingAlert = null;
		}
	}

	@Override
	public void showToast(int resId) {
		ToastAlert.showToast(this, resId);
	}

	@Override
	public void debug(String tag, String debugMsg) {
		Loger.d(tag, debugMsg);
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
		mActionManager.addAction(action);
	}

}
