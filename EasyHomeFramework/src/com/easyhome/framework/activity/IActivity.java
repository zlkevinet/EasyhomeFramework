//project copyright
package com.easyhome.framework.activity;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.plugin.module.IModule;

/**
 *
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public interface IActivity {

    void addAction(IAction action);
    void removeAction(IAction action);
    void runAction(String actionName);

    void addModule(IModule module);
    void removeModule(IModule module);
    IModule getModule(String moduleName);
    
    void initWindows();
    void initViews();
    void assembleViews();
}
