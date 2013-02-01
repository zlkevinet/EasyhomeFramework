/**
 * Copyright
 */
package com.easyhome.framework.util.http.parse;

import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;

import com.easyhome.framework.pojo.IPojo;

/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午1:11:06
 * @version 1.0
 */
public abstract class XmlParser<T extends IPojo> implements Parser<IPojo>{

	@Override
	public final T parse(InputStream in) {
		
		return null;
	}
	
	public abstract T parse(XmlPullParser xmlParser); 
}
