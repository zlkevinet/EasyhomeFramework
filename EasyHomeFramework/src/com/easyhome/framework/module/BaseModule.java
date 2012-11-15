/**
 * Copyright Baidu.Inc
 */
package com.easyhome.framework.module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;

import com.easyhome.framework.EasyApplication;

/**
 * 模块的抽象类
 * @author zhoulu
 * @since 2012-11-10-下午6:16:27
 * @version 1.0
 */
public abstract class BaseModule implements IModule {
	private ModuleManager mManager;

	private Map<ModuleType, IModule> mChildModule = new HashMap<ModuleType, IModule>();

	private List<IModuleWatcher> mAllWatchers = new ArrayList<IModuleWatcher>();

	private ModuleType mModuleType;

	@Override
	public void initModule() {

		mModuleType = getModuleType();
		checkModuleType();

	}

	private void checkModuleType() {
		if (mModuleType == null) {
			throw new ModuleException("you must use getModuleType() to set moduletype!");
		}
	}

	public BaseModule() {
		mManager = ModuleManager.getInstance();
	}

	public Context getContext() {
		return EasyApplication.getApplication().getApplicationContext();
	}

	public List<IModuleWatcher> getWatchers() {
		return mAllWatchers;
	}

	@Override
	public void addChildModule(ModuleType moduleType) {
		mManager.addModule(moduleType);

		if (mManager.getModule(moduleType) == null) {
			throw new ModuleException("add child module " + moduleType.toString() + " error!");
		}

		if (mChildModule != null) {
			mChildModule.put(moduleType, mManager.getModule(moduleType));
		}
	}

	@Override
	public IModule getChildModule(ModuleType moduleType) {
		IModule childModule = null;

		if (mChildModule != null && mChildModule.containsKey(moduleType)) {
			childModule = mChildModule.get(moduleType);
		} else {
			childModule = mManager.getModule(moduleType);
		}
		return childModule;
	}

	@Override
	public void removeModule() {
		clearAllWatcher();
	}

	public void registerWatcher(IModuleWatcher watcher) {
		if (watcher != null && !mAllWatchers.contains(watcher)) {
			mAllWatchers.add(watcher);
		}
	}

	public void unRegisterWatcher(IModuleWatcher watcher) {
		if (watcher != null && mAllWatchers.contains(watcher)) {
			mAllWatchers.remove(watcher);
		}
	}

	public void clearAllWatcher() {
		for (int i = 0; i < mAllWatchers.size(); i++) {
			IModuleWatcher watcher = mAllWatchers.get(i);
			if (watcher != null) {
				watcher = null;
			}
		}
		mAllWatchers.clear();
	}

}
