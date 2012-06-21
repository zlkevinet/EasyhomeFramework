//project copyright
package com.easyhome.framework.app;

import java.util.List;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.plugin.AppPluginManager;

import android.app.Application;

/**
 *
 * @author Jimmy.Z
 * @since 2012-6-21
 */
public class EasyApplication extends Application implements IApplication {

    private final AppPluginManager appPluginManager = AppPluginManager.getInstance(this);

    private static EasyApplication INSTANCE;
    
    protected EasyApplication() {
        INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static EasyApplication getInstance() {
        return INSTANCE;
    }

    public List<IAction> getActions() {
        //TODO global action
        return null;
    }
    
}
