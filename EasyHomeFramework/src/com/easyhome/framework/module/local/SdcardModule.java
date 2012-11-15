/**
 * Copyright Baidu.Inc
 */
package com.easyhome.framework.module.local;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;

import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleType;

/**
 * 外置卡模块
 * @author zhoulu
 * @since 2012-11-10-下午6:20:22
 * @version 1.0
 */
public class SdcardModule extends LocalModule {

	private BroadcastReceiver mSdcardReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			String action = intent.getAction();
			Message msg = Message.obtain(mHandler);
			msg.obj = action;
			msg.sendToTarget();
		}
	};
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String action = (String)msg.obj;
			dispatchChange(action);
		}
	};
	
	@Override
	public void initModule() {
		super.initModule();
		addChildModule(ModuleType.DATABASE);
		registMediaUnmountReceiver();
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
	
	public interface SdcardWatcher extends IModuleWatcher {
		public void onSdcardStateChange(String state);
	}
}
