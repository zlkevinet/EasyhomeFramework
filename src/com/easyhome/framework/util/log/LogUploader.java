/**
 * Copyright
 */
package com.easyhome.framework.util.log;

/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午1:12:42
 * @version 1.0
 */
public class LogUploader {

	/**
	 * TODO 打包发送
	 * @param mCache
	 */
	public void push(LogCache mCache) {
		//1.打包数据
		//2.开启线程进行发送
		//3.检测是否发送成功
		//4.重发
		mCache.zipData();
	}

}
