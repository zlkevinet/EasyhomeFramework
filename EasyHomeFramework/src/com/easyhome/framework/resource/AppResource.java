//project copyright
package com.easyhome.framework.resource;

import java.io.IOException;

import android.content.res.XmlResourceParser;


/**
 *
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public class AppResource extends BaseResource {

    private static final String CONFIG_FILE_PATH = "easy.xml";
    private static AppResource INSTANCE;

    private AppResource(ResourceManager rm) {
        super(rm);
    }

    public static AppResource getInstance(ResourceManager rm) {
        if (INSTANCE == null) {
            INSTANCE = new AppResource(rm);
        }
        return INSTANCE;
    }

    @Override
    public void loadResource() throws IOException {
        XmlResourceParser xmlParser = getResourceManager().getAssets().openXmlResourceParser(CONFIG_FILE_PATH);
        //TODO 解析easy.xml文件
        /*
         * how to get androidmanifest.xml 's activity.
         * 
         */
    }
    
    
}
