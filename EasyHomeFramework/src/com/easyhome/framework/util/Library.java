/**
 * Copyright
 */
package com.easyhome.framework.util;

import java.io.File;

import com.easyhome.framework.pojo.IPojo;
import com.easyhome.framework.pojo.MimeType;
import com.easyhome.framework.pojo.MimeTypeList;
import com.easyhome.framework.pojo.file.FileDecodeManager;

/**
 * 关于、手机信息等资料库
 * @author zhoulu
 * @since 2012-11-9-上午1:07:33
 * @version 1.0
 */
public class Library {

	private static final int MIN_SUPPORT_SDK_VERSION = 9;

	/**
	 * 获得最小可支持的SDK版本
	 * 
	 * @return
	 */
	public static int getMinSupportSDKVersion() {
		return MIN_SUPPORT_SDK_VERSION;
	}

	/**
	 * 检查当前系统版本是否支持
	 * 
	 * @return
	 */
	public static boolean checkVersion() {
		return android.os.Build.VERSION.SDK_INT >= MIN_SUPPORT_SDK_VERSION;
	}

	/**
	 * 检查指定系统版本是否支持
	 * 
	 * @param version
	 *            指定的版本号
	 * @return
	 */
	public static boolean checkSpecVersion(int version) {
		return android.os.Build.VERSION.SDK_INT >= version;
	}

	/**
	 * 获得某个文件的属性
	 * 
	 * @param filePath
	 *            指定文件路径
	 * @return 文件model
	 */
	public static IPojo gainFileProperties(String filePath) {
		File file = new File(filePath);
		if (file.isFile() && file.exists()) {
			MimeType mt = MimeTypeList.match(file);
			return FileDecodeManager.decode(file, mt);
		}
		return null;
	}

}
