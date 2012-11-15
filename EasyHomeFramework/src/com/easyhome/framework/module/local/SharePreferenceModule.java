/**
 * Copyright Baidu.Inc
 */
package com.easyhome.framework.module.local;

import java.util.List;

import android.content.SharedPreferences;

import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleType;

/**
 * 
 * @author zhoulu
 * @since 2012-11-10-下午7:59:29
 * @version 1.0
 */
public class SharePreferenceModule extends LocalModule {

//	private PreferencesController mPreferencesController;
	
	@Override
	public ModuleType getModuleType() {
		return ModuleType.SHARE_PREFERENCE;
	}
	
	@Override
	public void initModule() {
		super.initModule();
		
//		mPreferencesController = PreferencesController.getPreferences(getContext());
//		mPreferencesController.addListener(mPreferenceListener);
		
	}

//	private OnSharedPreferenceChangeListener mPreferenceListener = new OnSharedPreferenceChangeListener() {
//		@Override
//		public void onSharedPreferenceChanged(
//				SharedPreferences sharedPreferences, String key) {
//			dispatchChange(sharedPreferences, key);
//		}
//
//	};
	
	protected void dispatchChange(SharedPreferences sharedPreferences, String key) {
		List<IModuleWatcher> watchers = getWatchers();
		if(watchers != null){
			for (int i = 0; i < watchers.size(); i++) {
				SharePreferenceWatcher watcher = (SharePreferenceWatcher) watchers.get(i);
				if(watcher != null){
					watcher.onSharedPreferenceChanged(sharedPreferences, key);
				}
			}
		}
	}
	
	public interface SharePreferenceWatcher extends IModuleWatcher {
		public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key);
	}
}
