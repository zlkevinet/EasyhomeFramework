/**
 * Copyright
 */
package com.easyhome.framework.module;

import com.easyhome.framework.action.ActionFliper;
import com.easyhome.framework.action.IAction;

/**
 * 系统模块
 * 
 * 电话监听
 * 插拔耳机
 * 等...的抽象
 * 
 * @author zhoulu
 * @since 2012-11-10-下午5:43:48
 * @version 1.0
 */
public interface IModule {
	
	public void initModule();
	
	public void addChildModule(ModuleType moduleType);
	
	public IModule getChildModule(ModuleType moduleType);
	
	public ModuleType getModuleType();
	
	public void removeModule();
	
	public void registerWatcher(IModuleWatcher watcher);
	
	public void unRegisterWatcher(IModuleWatcher watcher);

	public boolean hasAction(String actionName);

	public void doAction(IAction action);
	
	public void registerActions(ActionFliper fliper);
}
