/**
 * Copyright
 */
package com.easyhome.framework.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.content.Context;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.local.DatabaseModule;
import com.easyhome.framework.module.local.MediaEffectModule;
import com.easyhome.framework.module.local.SdcardModule;
import com.easyhome.framework.module.local.SharePreferenceModule;
import com.easyhome.framework.util.log.Loger;

/**
 * 模块管理中心
 * @author zhoulu
 * @since 2012-11-10-下午6:04:49
 * @version 1.0
 */
public class ModuleManager {

	private static final boolean DEBUG = true;

	private static final String TAG = ModuleManager.class.getSimpleName();

	private Map<ModuleType, IModule> mAllModule = new HashMap<ModuleType, IModule>();
	
	private static ModuleManager mModuleManager;
	private Context mContext;
	private ModuleManager(){}
	
	public static ModuleManager getInstance(){
		if(mModuleManager == null){
			mModuleManager = new ModuleManager();
		}
		return mModuleManager;
	}
	
	public void setContext(Context context){
		mContext = context;
	}
	
	public IModule addModule(ModuleType moduleType) {
		IModule module = null;
		if (mAllModule.containsKey(moduleType)) {
			module = mAllModule.get(moduleType);
		} else {
			switch (moduleType) {
			case DATABASE:
				module = new DatabaseModule();
				break;
			case SDCARD:
				module = new SdcardModule();
				break;
			case SHARE_PREFERENCE:
				module = new SharePreferenceModule();
				break;
			case MEDIA_EFFECT:
				module = new MediaEffectModule();
				break;
			default:
				break;
			}

			if (module != null) {
				mAllModule.put(moduleType, module);
			}
		}
		
		return module;
	}
	
	public IModule getModule(ModuleType moduleType) {
		if (mAllModule != null) {

			if (mAllModule.containsKey(moduleType)) {
				return mAllModule.get(moduleType);
			}
		}

		return null;
	}
	
	/**
	 * 移除模块
	 * @param moduleType
	 */
	public void removeModule(ModuleType moduleType){
		IModule module = getModule(moduleType);
		if(module != null){
			module.removeModule();
		}
	}
	
	/**
	 * 清空所有的模块
	 */
	public void clearModules(){
		Iterator<ModuleType> iterator = mAllModule.keySet().iterator();
		while (iterator.hasNext()) {
			ModuleType moduleType = (ModuleType) iterator.next();
			IModule module = mAllModule.get(moduleType);
			if(module != null){
				module.removeModule();
				module = null;
			}
		}
		mAllModule.clear();
	}

	/**
	 * 分发动作
	 * @param action
	 */
	public void dispatchAction(IAction action) {
		if(DEBUG){
			Loger.d(TAG, "dispatchAction  " + action.getActionName() + " ...");
		}
		Iterator<IModule> iterator = mAllModule.values().iterator();
		while (iterator.hasNext()) {
			IModule module = iterator.next();
			if(module.hasAction(action.getActionName())){
				if(DEBUG){
					Loger.d(TAG, "Module " + module.getModuleType().toString() +" doAction  " + action.getActionName() + " ...");
				}
				module.doAction(action);
			}
		}
	}

	/**
	 * application context
	 * @return
	 */
	public Context getContext() {
		return mContext;
	}
	
}
