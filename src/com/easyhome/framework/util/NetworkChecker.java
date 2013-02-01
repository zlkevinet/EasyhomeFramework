/**
 * Copyright
 */
package com.easyhome.framework.util;

import android.content.Context;

/**
 * 网络监测
 * @author zhoulu
 * @since 2012-11-9-上午1:07:46
 * @version 1.0
 */
public final class NetworkChecker {

	private NetType mNetType;

	private static NetworkChecker mInstance;
	private Context mContext;
	
	public static enum NetType {
		NT_3G,
		NT_2G,
		NT_WIFI,
		NT_UN;
	}
	
	private NetworkChecker(Context context){
		mContext = context;
	}
	
	public static NetworkChecker getAppInstance(Context context) {
		if(mInstance == null){
			mInstance = new NetworkChecker(context);
		}
		return mInstance;
	}
	
	//TODO
	public boolean isConnect(){
		return false;
	}
	
	public NetType getNetType(){
		return mNetType;
	}
	
	public void addNetStateWatcher(NetStateWatcher watcher){
		
	}
	
	public interface NetStateWatcher {
		public void onConnectChanged(NetType netType);
	}
}
