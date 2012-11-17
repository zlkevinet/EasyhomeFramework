/**
 * Copyright
 */
package com.easyhome.framework.module.local;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.easyhome.framework.action.ActionCallback;
import com.easyhome.framework.action.ActionCommand;
import com.easyhome.framework.action.ActionFliper;
import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleType;
import com.easyhome.framework.util.log.Loger;

/**
 * 外置卡模块
 * @author zhoulu
 * @since 2012-11-10-下午6:20:22
 * @version 1.0
 */
public class SdcardModule extends LocalModule {

	public static final String ACTION_SEARCH = SdcardModule.class.getSimpleName() + "search";

	private static final boolean DEBUG = true;

	private static final String TAG = SdcardModule.class.getSimpleName();
	
	private BroadcastReceiver mSdcardReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			//TODO 可能出现ANR问题
			dispatchChange(action);
		}
	};
	
	
	@Override
	public void initModule() {
		super.initModule();
		addChildModule(ModuleType.DATABASE);
		registMediaUnmountReceiver();
		ActionFliper fliper = new ActionFliper();
		fliper.addAction(ACTION_SEARCH);
		registerActions(fliper);
	}

	protected void dispatchChange(String action) {
		List<IModuleWatcher> watchers = getWatchers();
		if(watchers != null){
			for (int i = 0; i < watchers.size(); i++) {
				SdcardWatcher watcher = (SdcardWatcher) watchers.get(i);
				if(watcher != null){
					watcher.onSdcardStateChange(action);
				}
			}
		}
	}

	// 监听SDCARD卡拔插事件。
	public void registMediaUnmountReceiver() {
		IntentFilter iFilter = new IntentFilter();
		iFilter.addAction(Intent.ACTION_MEDIA_UNMOUNTED);
		iFilter.addAction(Intent.ACTION_MEDIA_MOUNTED);
		iFilter.addDataScheme("file");
		getContext().registerReceiver(mSdcardReceiver, iFilter);
	}
		
	private void unRegistMediaUnmountReceiver() {
		if (mSdcardReceiver != null) {
			getContext().unregisterReceiver(mSdcardReceiver);
			mSdcardReceiver = null;
		}
	}
	
	@Override
	public ModuleType getModuleType() {
		return ModuleType.SDCARD;
	}
	
	@Override
	public void removeModule() {
		super.removeModule();
		unRegistMediaUnmountReceiver();
	}
	
	@Override
	public void doAction(IAction action) {
		super.doAction(action);
		String actionName = action.getActionName();
		ActionCallback callback = action.getActionCallback();
		
		if(DEBUG){
			Loger.d(TAG, "actionName " + actionName +" callback  " + callback + " ...");
		}
		
		if(ACTION_SEARCH.equals(actionName)){
			//TODO 执行搜寻功能
			
			if(callback != null){
				ActionCommand ac = new ActionCommand();
				//TODO 组装指令结束的反馈
				callback.responseAction(ac);
			}
		}
	}

	public interface SdcardWatcher extends IModuleWatcher {
		public void onSdcardStateChange(String state);
	}
}
