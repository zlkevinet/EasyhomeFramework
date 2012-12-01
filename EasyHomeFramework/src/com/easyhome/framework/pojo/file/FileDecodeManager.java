/**
 * Copyright E.T
 */
package com.easyhome.framework.pojo.file;

import java.io.File;

import com.easyhome.framework.pojo.IPojo;
import com.easyhome.framework.pojo.MimeType;
import com.easyhome.framework.pojo.PojoBuilder;

/**
 * 文件解密管理器
 * @author Kevin.E.T
 * @mail shuu.ro.zl@gmail.com
 * @since 2012-11-23-下午4:31:53
 * @version 1.0
 */
public class FileDecodeManager {

	/**
	 * TODO 解码文件结构，生成一个Model数据
	 * @param file
	 * @param mt
	 * @return
	 */
	public static IPojo decode(File file, MimeType mt) {
		IPojo model = null;
		if(file != null && mt != MimeType.UNKNOW){
			model = PojoBuilder.getInstance().createPojo(mt);
			model.fillPropertiesByFile(file);
		}
		return model;
	}

}
