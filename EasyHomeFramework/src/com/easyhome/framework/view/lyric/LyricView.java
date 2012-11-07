/**
 * Copyright Baidu.Inc
 */
package com.easyhome.framework.view.lyric;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import com.easyhome.framework.view.IView;

/**
 * 
 * @author zhoulu
 * @since 2012-10-16-下午9:31:33
 * @version 1.0
 */
public class LyricView extends View implements IView {

	private static final int FONT_TEXT_SIZE = 25;
	private static final int FONT_TEXT_VERTICAL_SPAC = 10;
	private static final int FONT_VERTICAL_SCROLL_OFFSET = 2;
	
	private static final String TAG = "LyricView";
	
	private Paint mHighlightPaint;
	private Paint mNormalPaint;
	
	private Paint mDrawPaint;
	private List<String> mData;
	
	private float mVerticalSpac;//行间距
	private float mCenterX;//中心点X
	private float mCenterY;//中心点Y
	private float mVisibleWidth;//歌词可见区域的宽度
	private float mVisibleHeight;//歌词可见区域的高度
	
	private float mRealTextHeight;//歌词总长度
	
	private float mFontOffsetX;//字体相对中心点的偏移X
	private float mFontOffsetY;//字体相对中心点的偏移Y
	
	private float mScrollOffset; //滚动一次的偏移量
	private float mTextScrollOffsetY; //文本滚动的偏移量Y
	
	private float mFontSize;//字体大小
	private float mFontHeight;//字体高
	private float mFontWight;//字体宽
	private FontMetrics mFontMetrics;
	
	private ScrollThread mAutoScroller;
	
	private Handler mHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			invalidate();
		}
		
	};
	public LyricView(Context context) {
		super(context);
		init();
	}

	public LyricView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		
		mHighlightPaint = new Paint();
		mHighlightPaint.setAntiAlias(true);
		mHighlightPaint.setColor(Color.RED);
		
		float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, FONT_TEXT_SIZE, metrics);
		mHighlightPaint.setTextSize(textSize);
		mHighlightPaint.setTextAlign(Align.CENTER);
		mHighlightPaint.setFakeBoldText(true);
		
		mNormalPaint = new Paint();
		mNormalPaint.setAntiAlias(true);
		mNormalPaint.setColor(Color.BLUE);
		
		mNormalPaint.setTextSize(textSize);
		mNormalPaint.setTextAlign(Align.CENTER);
		
		mVerticalSpac = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, FONT_TEXT_VERTICAL_SPAC, metrics);
		mScrollOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, FONT_VERTICAL_SCROLL_OFFSET, metrics);
		
		mDrawPaint = new Paint();
		mDrawPaint.set(mNormalPaint);
		
		mFontMetrics = mDrawPaint.getFontMetrics();
		mFontSize = mDrawPaint.getTextSize();
		mFontHeight = mFontMetrics.bottom - mFontMetrics.top;
		
		mData = new ArrayList<String>();
		mData.add("AAAAAAAAAAAAAAAAAAA");
		mData.add("BBBBBBBBBBBBBBBBBBB");
		mData.add("CCCCCCCCCCCCCCCCCCC");
		mData.add("DDDDDDDDDDDDDDDDDDDD");
		mData.add("EEEEEEEEEEEEEEEEE");
		mData.add("FFFFFFFFFFFFFFFFFFFF");
		mData.add("GGGGGGGGGGGGGGGGGGGGGG");
		mData.add("HHHHHHHHHHHHHHHHHHHH");
		mData.add("EEEEEEEEEEEEEEEEEEEEEE");
		mData.add("FFFFFFFFFFFFFFFFFFFF");
		mData.add("RRRRRRRRRRRRRRRRRRRRRRR");
		mData.add("HHWWWWW");
		mData.add("QQQQQQQQQQQQQQQQQQQQQQQQQ");
		mData.add("HJJJJJJJJJJJJJJJJJJJJ");
		mData.add("LLLLLLLLLLLLLLLLLLLLLL");
		mData.add("PPPPPPPPPPPPPPPPPPPPPP");
		mData.add("TTTTTTTTTTTTTTTTT");
		mData.add("YYYYYYYYYYYYYYYYYYYYYYY");
		
		mAutoScroller = new ScrollThread();
	}

	
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		mCenterX = w >> 1;
		mCenterY = h >> 1;
		
		mVisibleWidth = w - getPaddingLeft() - getPaddingRight();
		mVisibleHeight = h - getPaddingTop() - getPaddingBottom();
		
		breakAllData();
		
		mRealTextHeight = (mDrawPaint.getTextSize() + mVerticalSpac) * mData.size() - mVerticalSpac;
		
		mAutoScroller.start();
	}

	private void breakAllData() {
		if(mData == null 
				|| mData.size() == 0
				|| mVisibleWidth == 0){
			return;
		}
		for (int dataIdx = 0; dataIdx < mData.size(); dataIdx++) {
			String text = mData.get(dataIdx);
			List<String> breakTexts = breakText(text, mVisibleWidth, mDrawPaint);
			int count = breakTexts.size();
			if(breakTexts != null && count > 1){
				//将打散的字符串加入到歌词数据中
				mData.remove(dataIdx);
				mData.addAll(dataIdx, breakTexts);
				dataIdx += count + 1;
				continue;
			}
		}
		
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return super.onTouchEvent(event);
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//设置背景为透明色
		canvas.drawColor(Color.TRANSPARENT);
		Log.i(TAG, "onDraw....");
		Log.i(TAG, "+++++mTextScrollOffsetY = " + mTextScrollOffsetY);
		if(mData == null
				|| mData.size() == 0){
			return;
		}
		
		mFontOffsetX = 0;
		mFontOffsetY = 0;
		
		for (int dataIdx = 0; dataIdx < mData.size(); dataIdx++) {
			String drawStr = mData.get(dataIdx);
			mFontOffsetY += mFontSize + mVerticalSpac;
			if(isCenter()){
				mDrawPaint.reset();
				mDrawPaint.set(mHighlightPaint);
			} else {
				mDrawPaint.reset();
				mDrawPaint.set(mNormalPaint);
			}
			canvas.drawText(drawStr, mCenterX + mFontOffsetX, mCenterY + mFontOffsetY - mTextScrollOffsetY, mDrawPaint);
		}
		
	}

	private boolean isCenter() {
		return ((mFontOffsetY <= mTextScrollOffsetY + mFontHeight / 2)
				&& (mFontOffsetY >= mTextScrollOffsetY - mFontHeight / 2)) ? true : false;
	}

	/**
	 * 打散文本，将文本按照显示宽度width分段
	 * 
	 * @param text
	 * @param width
	 * @return
	 */
	protected List<String> breakText(String text, float width, Paint paint) {
		List<String> list = new ArrayList<String>();
		int count = paint.breakText(text, true, width, null);
		String preText = text.substring(0, count);
		String lastText = text.substring(count);
		if (preText.length() >= text.length()) {
			list.add(preText);
			return list;
		}
		int splitIndex = getLastSplitPosition(preText);
		if (splitIndex > 0 && splitIndex < preText.length()) {
			preText = text.substring(0, splitIndex);
			lastText = text.substring(splitIndex);
		}

		while (!TextUtils.isEmpty(preText)) {
			list.add(preText);
			if (TextUtils.isEmpty(lastText))
				break;
			count = paint.breakText(lastText, true, width, null);
			if (count >= lastText.length()) {
				list.add(lastText);
				return list;
			}
			if (count <= 0)
				preText = null;
			else {
				preText = lastText.substring(0, count);
				text = lastText;
				if (count < 0 || count >= lastText.length())
					lastText = null;
				else
					lastText = lastText.substring(count);
				splitIndex = getLastSplitPosition(preText);
				if (splitIndex > 0 && splitIndex < preText.length()) {
					preText = text.substring(0, splitIndex);
					lastText = text.substring(splitIndex);
				}
			}
		}
		return list;
	}
	
	/**
	 * 获取文本中最后一个分割符的位置
	 * 
	 * @param text
	 * @return
	 */
	private int getLastSplitPosition(String text) {
		if (TextUtils.isEmpty(text))
			return -1;
		int pos = -1;
		int posBlank = text.lastIndexOf(" ");
		int posComma = text.lastIndexOf(",");
		int posSemicolon = text.lastIndexOf(";");
		pos = Math.max(posBlank, posComma);
		pos = Math.max(pos, posSemicolon);
		return pos;
	}
	
	class ScrollThread extends Thread {

		@Override
		public void run() {
			while(mTextScrollOffsetY <= mRealTextHeight){
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
				mTextScrollOffsetY += mScrollOffset;
				mHandler.sendEmptyMessage(0);
			}
		}
		
	}
}
