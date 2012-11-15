/**
 * Copyright Baidu.Inc
 */
package com.easyhome.framework.activity;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.module.IModule;
import com.easyhome.framework.module.IModuleWatcher;
import com.easyhome.framework.module.ModuleType;

/**
 * 通用接口
 * @author zhoulu
 * @since 2012-11-10-下午4:39:54
 * @version 1.0
 */
public interface IActivity {
	
	/**
	 * 第一次启动，加载数据
	 */
	public void onFirstLoadData();
	
	/**
	 * 初始化视图元素
	 */
	public void onInitViews();
	
	/**
	 * 显示正在加载中
	 */
	public void showLoading();
	
	/**
	 * 关闭Loading界面
	 */
	public void dismissLoading();
	
	/**
	 * 显示一条Toast信息
	 * @param resId
	 */
	public void showToast(int resId);
	
	/**
	 * 调试输出
	 * @param tag
	 * @param debugMsg
	 */
	public void debug(String tag, String debugMsg);
	
	/**
	 * 添加系统组件
	 * 
	 * 如果父类已经添加，则使用父类的组件
	 * @param moduleType
	 */
	public void addSystemModule(ModuleType moduleType, IModuleWatcher watcher);
	
	/**
	 * 获得系统组件
	 * 
	 * @param moduleType
	 * @return
	 */
	public IModule getSystemModule(ModuleType moduleType);
	
	/**
	 * 移除模块
	 * 
	 * @param moduleType
	 */
	public void removeSystemModule(ModuleType moduleType, IModuleWatcher watcher);
	
	/**
	 * 发送动作
	 * @param action
	 */
	public void sendAction(IAction action);
	
}
