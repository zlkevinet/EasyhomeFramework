//project copyright
package com.easyhome.framework.utility;

import android.util.Log;

/**
 * log
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public class LogUtils {

    private static final boolean DEVELOPER_MODE = true;
    
    public static void e(Class<?> cls, String msg){
        if(DEVELOPER_MODE){
            Log.e(cls.getSimpleName(), msg);
        }
    }
    
    public static void e(Class<?> cls, String msg, Throwable tr){
        if(DEVELOPER_MODE){
            Log.e(cls.getSimpleName(), msg, tr);
        }
    }
    public static void d(Class<?> cls, String msg){
        if(DEVELOPER_MODE){
            Log.d(cls.getSimpleName(), msg);
        }
    }
    
    public static void d(Class<?> cls, String msg, Throwable tr){
        if(DEVELOPER_MODE){
            Log.d(cls.getSimpleName(), msg, tr);
        }
    }
    public static void v(Class<?> cls, String msg){
        if(DEVELOPER_MODE){
            Log.v(cls.getSimpleName(), msg);
        }
    }
    
    public static void v(Class<?> cls, String msg, Throwable tr){
        if(DEVELOPER_MODE){
            Log.v(cls.getSimpleName(), msg, tr);
        }
    }
    public static void i(Class<?> cls, String msg){
        if(DEVELOPER_MODE){
            Log.i(cls.getSimpleName(), msg);
        }
    }
    
    public static void i(Class<?> cls, String msg, Throwable tr){
        if(DEVELOPER_MODE){
            Log.i(cls.getSimpleName(), msg, tr);
        }
    }
    public static void w(Class<?> cls, String msg){
        if(DEVELOPER_MODE){
            Log.w(cls.getSimpleName(), msg);
        }
    }
    
    public static void w(Class<?> cls, String msg, Throwable tr){
        if(DEVELOPER_MODE){
            Log.w(cls.getSimpleName(), msg, tr);
        }
    }
    
}
