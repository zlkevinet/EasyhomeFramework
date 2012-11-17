/**
 * Copyright
 */
package com.easyhome.framework.module;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.local.DatabaseModule;

/**
 * 模块管理中心
 * @author zhoulu
 * @since 2012-11-10-下午6:04:49
 * @version 1.0
 */
public class ModuleManager {

	private Map<ModuleType, IModule> mAllModule = new HashMap<ModuleType, IModule>();
	
	private static ModuleManager mModuleManager;
	
	private ModuleManager(){}
	
	public static ModuleManager getInstance(){
		if(mModuleManager == null){
			mModuleManager = new ModuleManager();
		}
		return mModuleManager;
	}
	
	public void addModule(ModuleType moduleType) {
		if (mAllModule == null || mAllModule.containsKey(moduleType)) {
			return;
		}
		IModule module = null;
		switch (moduleType) {
		case DATABASE:
			module = new DatabaseModule();
			break;

		default:
			break;
		}

		if (module != null) {
			mAllModule.put(moduleType, module);
		}
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
		Iterator<IModule> iterator = mAllModule.values().iterator();
		while (iterator.hasNext()) {
			IModule module = iterator.next();
			if(module.hasAction(action.getActionName())){
				module.doAction(action);
			}
		}
	}
	
}
