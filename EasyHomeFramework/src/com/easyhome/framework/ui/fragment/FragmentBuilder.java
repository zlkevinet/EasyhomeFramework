/**
 * Copyright
 */
package com.easyhome.framework.ui.fragment;

import android.app.Activity;

/**
 * 
 * @author zhoulu
 * @since 2012-11-9-上午1:02:03
 * @version 1.0
 */
public class FragmentBuilder {

	private static FragmentBuilder mFragmentBuilder; 
	
	private FragmentBuilder(Activity activity){
		
	}
	
	public static FragmentBuilder getInstance(Activity activity) {
		if(mFragmentBuilder == null){
			mFragmentBuilder = new FragmentBuilder(activity);
		}
		return mFragmentBuilder;
	}
	
}
