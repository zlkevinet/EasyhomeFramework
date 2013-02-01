/**
 * Copyright E.T.
 */
package com.easyhome.framework.activity;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;

import com.easyhome.framework.action.ActionManager;
import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.IModule;
import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleManager;
import com.easyhome.framework.module.ModuleType;
import com.easyhome.framework.ui.notifier.Notifier;
import com.easyhome.framework.util.log.Loger;

/**
 * Activity的装饰类
 * @creator kevin
 * @since Nov 17, 2012
 */
@SuppressLint("HandlerLeak")
final class DecorActivity implements IActivity {

	private static final int MSG_FIRST_LOAD_DATA = 0;

	private static final boolean DEBUG = true;

	private static final String TAG = DecorActivity.class.getSimpleName();
	
	private Map<ModuleType, IModuleWatcher> mAllModuleWatcher;
	private ModuleManager mModuleManager;
	private ActionManager mActionManager;
	
	private Activity mActivity;
	
	private Handler mHandler = new Handler(){
		@Override
		public void dispatchMessage(Message msg) {
			super.dispatchMessage(msg);
			switch(msg.what){
				case MSG_FIRST_LOAD_DATA:
					dismissLoading();
					break;
				default:
					break;
			}
		}	
	};
	
	public DecorActivity(Activity activity){
		mActivity = activity;
		mModuleManager = ModuleManager.getInstance();
		mModuleManager.setContext(mActivity.getApplicationContext());
		mActionManager = ActionManager.getInstance();
		mAllModuleWatcher = new HashMap<ModuleType, IModuleWatcher>();
	}
	
	@SuppressLint("HandlerLeak")
	@Override
	public void onFirstLoadData() {
		new Thread("onFirstLoadData thread..."){
			public void run() {
				if(mActivity instanceof IActivity){
					((IActivity) mActivity).onFirstLoadData();
				}
				mHandler.sendEmptyMessage(MSG_FIRST_LOAD_DATA);
			};
		}.start();
	}

	@Override
	public void onInitViews() {
		if(mActivity instanceof IActivity){
			((IActivity) mActivity).onInitViews();
		}
	}

	@Override
	public void showLoading() {
		Notifier.showLoading(mActivity);
	}

	@Override
	public void dismissLoading() {
		Notifier.dismissLoading(mActivity);
	}

	@Override
	public void showToast(int resId) {
		Notifier.showToast(mActivity, resId);
	}

	@Override
	public void debug(String tag, String debugMsg) {
		Loger.d(tag, debugMsg);
	}

	@Override
	public IModule addSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
		IModule module = mModuleManager.addModule(moduleType);
		if(module != null && watcher != null){
			module.registerWatcher(watcher);
		}
		
		if(watcher != null){
			putModuleWatcher(moduleType, watcher);
		}
		return module;
	}

	@Override
	public IModule getSystemModule(ModuleType moduleType) {
		return mModuleManager.getModule(moduleType);
	}

	@Override
	public void removeSystemModule(ModuleType moduleType, IModuleWatcher watcher) {
		IModule module = mModuleManager.getModule(moduleType);
		if(module != null && watcher != null){
			module.unRegisterWatcher(watcher);
		}
		
		if(watcher != null){
			removeModuleWatcher(moduleType, watcher);
		}
	}

	@Override
	public void removeAllSystemModule() {
		Iterator<ModuleType> iterator = mAllModuleWatcher.keySet().iterator();
		while (iterator.hasNext()) {
			ModuleType moduleType = (ModuleType) iterator.next();
			IModuleWatcher watcher = mAllModuleWatcher.get(moduleType);
			if(watcher != null){
				removeSystemModule(moduleType, watcher);
			}
		}
		mAllModuleWatcher.clear();
	}

	@Override
	public void sendAction(IAction action) {
		if(DEBUG){
			Loger.d(TAG, "sendAction " + action.getActionName() + " ...");
		}
		mActionManager.addAction(action);
	}
	
	private void putModuleWatcher(ModuleType moduleType, IModuleWatcher watcher){
		if(!mAllModuleWatcher.containsKey(moduleType)){
			mAllModuleWatcher.put(moduleType, watcher);
		}
	}
	
	private void removeModuleWatcher(ModuleType moduleType, IModuleWatcher watcher) {
		mAllModuleWatcher.remove(moduleType);
	}
}
