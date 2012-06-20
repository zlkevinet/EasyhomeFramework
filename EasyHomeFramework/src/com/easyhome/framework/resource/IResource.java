//project copyright
package com.easyhome.framework.resource;

import java.io.IOException;


/**
 * interface resource
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public interface IResource {

    void loadResource() throws IOException;
    
    Object getValue(Object key);
    
    void setValue(Object key, Object value);
}
