/**
 * Copyright
 */
package com.easyhome.framework.util.http.parse;

import java.io.InputStream;

import org.json.JSONException;

import com.easyhome.framework.pojo.IPojo;
import com.easyhome.framework.util.IOUtiler;
import com.easyhome.framework.util.log.Loger;

/**
 * Json解析成POJO对象
 * @author zhoulu
 * @since 2012-11-9-上午1:10:56
 * @version 1.0
 */
public abstract class JsonParser<T extends IPojo> implements Parser<IPojo> {
	
	private static final String TAG = "JsonParse";

	@Override
	public final T parse(InputStream in) {
		String json = IOUtiler.getContentForJsonString(in);
		if(json != null && !"".equals(json)){
			try {
				return parse(json);
			} catch (JSONException e) {
				Loger.e(TAG, "json parse error!", e);
			}
		}
		return null;
	}
	
	public abstract T parse(String json) throws JSONException;
}
