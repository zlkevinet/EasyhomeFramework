/**
 * Copyright
 */
package com.easyhome.framework.util.http.parse;

import java.io.InputStream;

import com.easyhome.framework.pojo.IPojo;

/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午1:10:37
 * @version 1.0
 */
public interface Parser<T extends IPojo> {
	public T parse(InputStream in);
}
