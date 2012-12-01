/**
 * Copyright E.T
 */
package com.easyhome.framework.pojo;

import com.easyhome.framework.pojo.file.Mp3PropertiesPojo;

/**
 * 
 * @author Kevin.E.T
 * @mail shuu.ro.zl@gmail.com
 * @since 2012-11-23-下午4:36:55
 * @version 1.0
 */
public class PojoBuilder {

	private static PojoBuilder instance;

	private PojoBuilder() {

	}

	public static PojoBuilder getInstance() {
		if (instance == null) {
			instance = new PojoBuilder();
		}
		return instance;
	}

	public IPojo createPojo(MimeType mt) {
		IPojo pojo = null;
		switch(mt){
		case MP3:
			pojo = new Mp3PropertiesPojo();
			break;
			
			default:
				break;
		}
		return pojo;
	}

}
