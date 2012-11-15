/**
 * Copyright Baidu.Inc
 */
package com.easyhome.framework.util.log;

import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipFile;

/**
 * 存储统计
 * 
 * @author zhoulu
 * @since 2012-11-9-上午1:12:32
 * @version 1.0
 */
public class LogCache {

	Map<FilterType, Long> mCache = new HashMap<FilterType, Long>();
	
	public LogCache() {
		
	}
	
	
	/**
	 * TODO 加载数据到内存
	 */
	private void loadAllDatas() {
		
	}
	
	/**
	 * TODO 保存数据到文件
	 */
	public void saveAllDatas() {
		
	}
	
	public void add(FilterType type) {
		if(mCache.containsKey(type)){
			long current = mCache.get(type).longValue();
			mCache.put(type, current + 1);
		} else {
			mCache.put(type, 1L);
		}
	}

	/**
	 * TODO 压缩打包
	 * @return
	 */
	public ZipFile zipData(){
		loadAllDatas();
		
		return null;
	}
}
