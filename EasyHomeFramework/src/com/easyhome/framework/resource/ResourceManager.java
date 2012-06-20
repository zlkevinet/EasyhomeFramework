//project copyright

package com.easyhome.framework.resource;

import java.util.Properties;

import android.content.Context;
import android.content.res.AssetManager;

/**
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public class ResourceManager {

    public static final int CONFIG_TYPE_PROPERTIES = 0;
    public static final int CONFIG_TYPE_APP = 1;
    public static final int CONFIG_TYPE_PHONE = 2;

    private Context context;
    private static ResourceManager INSTANCE;

    private IResource resource;
    
    private Properties properties_container = new Properties();
    private Properties app_container = new Properties();
    private Properties phone_container = new Properties();

    private ResourceManager(Context context) {
        this.context = context;
    }

    public static ResourceManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ResourceManager(context);
        }
        return INSTANCE;
    }

    public AssetManager getAssets() {
        return context.getAssets();
    }

    public Properties getProperties(int resType) {
        switch (resType) {
            case CONFIG_TYPE_PROPERTIES:
                return properties_container;
            case CONFIG_TYPE_APP:
                return app_container;
            case CONFIG_TYPE_PHONE:
                return phone_container;
        }
        throw new IllegalArgumentException("the resType: " +resType+ " is not found.");
    }

    public IResource getConfigResource(int resType) {
        switch (resType) {
            case CONFIG_TYPE_PROPERTIES:
                resource = PropertiesResource.getInstance(this);
                break;
            case CONFIG_TYPE_APP:
                resource = AppResource.getInstance(this);
                break;
            case CONFIG_TYPE_PHONE:
                resource = PhoneResource.getInstance(this);
                break;
        }
        return resource;
    }

    public Object getValue(int resType, Object key){
        return getConfigResource(resType).getValue(key);
    }
    
    public Context getContext() {
        return context;
    }

}
