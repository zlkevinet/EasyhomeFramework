//project copyright
package com.easyhome.framework.resource;


/**
 *
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public class PhoneResource extends BaseResource {

    private static PhoneResource INSTANCE;

    private PhoneResource(ResourceManager rm) {
        super(rm);
    }

    public static PhoneResource getInstance(ResourceManager rm) {
        if (INSTANCE == null) {
            INSTANCE = new PhoneResource(rm);
        }
        return INSTANCE;
    }
}
