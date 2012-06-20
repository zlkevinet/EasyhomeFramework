//project copyright
package com.easyhome.framework.resource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.easyhome.framework.utility.LogUtils;

/**
 * base resource
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public abstract class BaseResource implements IResource{

    private final String DF_RES_FILE = "app.properties";
    
    protected final Properties properties = new Properties();
    
    private ResourceManager resourceManager;
    
    public BaseResource(ResourceManager resourceManager){
        this.resourceManager = resourceManager;
        try {
            loadResource();
        } catch (IOException e) {
            LogUtils.e(getClass(), "load resource error!", e);
        }
    }
    
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    @Override
    public void loadResource() throws IOException{
        InputStream is = resourceManager.getAssets().open(DF_RES_FILE);
        properties.load(is);
    }
    
    @Override
    public Object getValue(Object key) {
        return properties.get(key);
    }

    @Override
    public void setValue(Object key, Object value) {
        properties.put(key, value);
    }
    
}
