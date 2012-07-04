package com.easyhome.demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.easyhome.framework.anim.ComposerButtonAnimation;
import com.easyhome.framework.anim.InOutAnimation;
import com.easyhome.framework.view.plusmenu.ComposerLayout;

public class Path_animationActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plusmanu_main);
        setUpViews();
    }
    
    private void setUpViews(){
    	rotateStoryAddButtonIn = AnimationUtils.loadAnimation(this, R.anim.rotate_story_add_button_in);
    	rotateStoryAddButtonOut = AnimationUtils.loadAnimation(this, R.anim.rotate_story_add_button_out);
    	
    	composerButtonsWrapper = (ViewGroup) findViewById(R.id.composer_buttons_wrapper);
    	composerButtonsShowHideButton = findViewById(R.id.composer_buttons_show_hide_button);
    	composerButtonsShowHideButtonIcon = findViewById(R.id.composer_buttons_show_hide_button_icon);
    	registerComposerButtonListeners();
    }
    
    private void registerComposerButtonListeners()
    {
        composerButtonsShowHideButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				toggleComposerButtons();
			}
		});
    }
    
    private void toggleComposerButtons()
    {
		if (areButtonsShowing)
        {
            ComposerButtonAnimation.startAnimations(composerButtonsWrapper, InOutAnimation.Direction.OUT);
            composerButtonsShowHideButtonIcon.startAnimation(rotateStoryAddButtonOut);
        } else
        {
            ComposerButtonAnimation.startAnimations(composerButtonsWrapper, InOutAnimation.Direction.IN);
            composerButtonsShowHideButtonIcon.startAnimation(rotateStoryAddButtonIn);
        }
        areButtonsShowing = !areButtonsShowing;
    }
    
    private boolean areButtonsShowing = false;
    private View composerButtonMusic;
    private View composerButtonPeople;
    private View composerButtonPlace;
    private View composerButtonSleep;
    private View composerButtonThought;
    private View composerButtonsShowHideButton;//
    private View composerButtonsShowHideButtonIcon;//
    private ViewGroup composerButtonsWrapper;
    private ComposerLayout composerLayout;
    
    private Animation rotateStoryAddButtonIn;
    private Animation rotateStoryAddButtonOut;
}