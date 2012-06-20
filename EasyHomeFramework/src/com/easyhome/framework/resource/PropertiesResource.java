//project copyright
package com.easyhome.framework.resource;

import java.io.IOException;
import java.io.InputStream;

import com.easyhome.framework.utility.LogUtils;

/**
 * properties resource
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public class PropertiesResource extends BaseResource {

    private final String DF_RES_PATTERN = "^[a-zA-Z/$]*app-[a-zA-Z]+.properties$";
    
    private static PropertiesResource INSTANCE;
    
    private PropertiesResource(ResourceManager resourceManager) {
        super(resourceManager);
        try {
            findPatternPropertiesFile();
        } catch (IOException e) {
            LogUtils.e(getClass(), "find pattern properties files error", e);
        }
    }

    public static PropertiesResource getInstance(ResourceManager resourceManager){
        if(INSTANCE == null){
            INSTANCE = new PropertiesResource(resourceManager);
        }
        return INSTANCE;
    }
    
    private void findPatternPropertiesFile() throws IOException {
        String[] listFilePath = getResourceManager().getAssets().list("");
        for (int i = 0; i < listFilePath.length; i++) {
            final String oneFilePath = listFilePath[i];
            LogUtils.d(getClass(), oneFilePath);
            if (oneFilePath.matches(DF_RES_PATTERN)) {
                InputStream is = getResourceManager().getAssets().open(oneFilePath);
                loadResource(is);
            }
        }
    }
    
    public void loadResource(InputStream inputStream) throws IOException{
        properties.load(inputStream);
    }
}
