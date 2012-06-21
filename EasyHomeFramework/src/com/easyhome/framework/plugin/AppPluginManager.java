//project copyright
package com.easyhome.framework.plugin;

import android.content.Context;

/**
 *
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public class AppPluginManager {

    private Context context;
    
    private static AppPluginManager INSTANCE;
    private AppPluginManager(Context context){
        this.context = context;
    }
    
    public static AppPluginManager getInstance(Context context) {
        if(INSTANCE == null){
            INSTANCE = new AppPluginManager(context);
        }
        return INSTANCE;
    }

    
}
