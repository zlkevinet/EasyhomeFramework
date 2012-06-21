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
    
    protected Properties properties;
    
    private ResourceManager resourceManager;
    private static boolean inited; 
    
    public BaseResource(ResourceManager resourceManager){
        this.resourceManager = resourceManager;
        
        if(this instanceof AppResource){
            properties = resourceManager.getProperties(ResourceManager.CONFIG_TYPE_APP);
        } else if(this instanceof PhoneResource){
            properties = resourceManager.getProperties(ResourceManager.CONFIG_TYPE_PHONE);
        } else if(this instanceof PropertiesResource){
            properties = resourceManager.getProperties(ResourceManager.CONFIG_TYPE_PROPERTIES);
        }
        
        if(!inited){
            inited = true;
            try {
                InputStream is = resourceManager.getAssets().open(DF_RES_FILE);
                properties.load(is);
            } catch (IOException e) {
                LogUtils.e(getClass(), "load resource error!", e);
            }
        }
        
    }
    
    public ResourceManager getResourceManager() {
        return resourceManager;
    }

    @Override
    public void loadResource() throws IOException{
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
