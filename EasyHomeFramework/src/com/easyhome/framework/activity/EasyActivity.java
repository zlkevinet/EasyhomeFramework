//project copyright
package com.easyhome.framework.activity;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.easyhome.framework.action.IAction;
import com.easyhome.framework.app.EasyApplication;
import com.easyhome.framework.plugin.AppPluginManager;
import com.easyhome.framework.plugin.module.IModule;
import com.easyhome.framework.resource.ResourceManager;

/**
 * 
 * @author Jimmy.Z
 * @since 2012-6-20
 */
public abstract class EasyActivity extends Activity implements IActivity{
    
    private final AppPluginManager appPluginManager = AppPluginManager.getInstance(this);
    private final ResourceManager resourceManager = ResourceManager.getInstance(this);
    private final EasyApplication easyApplication = EasyApplication.getInstance();
    
    private List<IAction> listActions;
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        initPlugins();
        initResources();
        
        verifySavedState(savedInstanceState);
        
        initActions();
        
    }

    
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    private void initActions() {
        List<IAction> listCommonActions = easyApplication.getActions();
        for (int actionIndex = 0; actionIndex < listCommonActions.size(); actionIndex++) {
            addAction(listCommonActions.get(actionIndex));
        }
    }

    private void verifySavedState(Bundle savedInstanceState) {
    }

    public void assembleViews() {
    }

    public void initViews() {
    }

    public void initWindows() {
    }

    private void initResources() {
        listActions = new ArrayList<IAction>();
        
    }

    private void initPlugins() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void addAction(IAction action) {
    }

    @Override
    public void removeAction(IAction action) {
    }

    @Override
    public void runAction(String actionName) {
    }

    @Override
    public void addModule(IModule module) {
    }

    @Override
    public void removeModule(IModule module) {
    }

    @Override
    public IModule getModule(String moduleName) {
        return null;
    }
    
    
}
