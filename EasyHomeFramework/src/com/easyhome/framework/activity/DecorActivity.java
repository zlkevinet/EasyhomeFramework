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
import com.easyhome.framework.ui.alert.LoadingAlert;
import com.easyhome.framework.ui.alert.ToastAlert;
import com.easyhome.framework.util.log.Loger;

/**
 * Activity的装饰类
 * @creator kevin
 * @since Nov 17, 2012
 */
@SuppressLint("HandlerLeak")
public class DecorActivity implements IActivity {

	private static final int MSG_FIRST_LOAD_DATA = 0;

	private static final boolean DEBUG = true;

	private static final String TAG = DecorActivity.class.getSimpleName();
	
	private LoadingAlert mLoadingAlert;
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
		mModuleManager.setContext(mActivity);
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
		if(mLoadingAlert == null){
			mLoadingAlert = new LoadingAlert(mActivity);
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
		ToastAlert.showToast(mActivity, resId);
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
		
		putModuleWatcher(moduleType, watcher);
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
		
		removeModuleWatcher(moduleType, watcher);
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
