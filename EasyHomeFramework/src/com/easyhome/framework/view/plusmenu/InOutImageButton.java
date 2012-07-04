package com.easyhome.framework.view.plusmenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;

import com.easyhome.framework.anim.InOutAnimation;

public class InOutImageButton extends ImageButton {

	public InOutImageButton(Context context) {
		super(context);
	}

	public InOutImageButton(Context context, AttributeSet attributeset) {
		super(context, attributeset);
	}

	protected void onAnimationEnd() {
		super.onAnimationEnd();
		if (animation instanceof InOutAnimation) {
			int direction = ((InOutAnimation) animation).direction;
			if (direction == InOutAnimation.Direction.OUT)
				setVisibility(View.GONE);
			else
				setVisibility(View.VISIBLE);
		}
	}

	protected void onAnimationStart() {
		super.onAnimationStart();
		if (animation instanceof InOutAnimation)
			setVisibility(View.VISIBLE);
	}

	public void startAnimation(Animation animation1) {
		super.startAnimation(animation1);
		animation = animation1;
		getRootView().postInvalidate();
	}

	private Animation animation;
}
