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
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.easyhome.framework.view.IView;

/**
 * 
 * @author zhoulu
 * @since 2012-10-16-下午9:31:33
 * @version 1.0
 */
public class SurfaceLyricView extends SurfaceView implements IView, SurfaceHolder.Callback {

	//字体大小
	private static final int FONT_TEXT_SIZE = 18;
	//字体垂直间距
	private static final int FONT_TEXT_VERTICAL_SPAC = 10;
	//垂直滚动单位位移
	private static final int FONT_VERTICAL_SCROLL_OFFSET = 2;
	
	private static final String TAG = "LyricView";
	
	private Paint mHighlightPaint;//高亮歌词画笔
	private Paint mNormalPaint;//普通歌词画笔
	
	private Paint mDrawPaint;//绘制使用的画笔
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
	private float mTextAutoScrollOffsetY; //文本滚动的偏移量Y
	
	private float mFontSize;//字体大小
	private float mFontHeight;//字体高
//	private float mFontWight;//字体宽
	
	private FontMetrics mFontMetrics;

	private boolean mDrawing = true;
	
	private DrawThread mDrawThread;//绘制线程
	private SurfaceHolder mHolder;
	private Canvas mCanvas;
	
	private Rect mDirtyRect;
	
	/*************************************/
	private boolean mIsBeingDrag;
	private float mLastMotionY;
	private float mTextScrollDeltaY;//手指滚动的距离
	
	public SurfaceLyricView(Context context) {
		super(context);
		init();
	}

	public SurfaceLyricView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init(){
		mHolder = getHolder();
		mHolder.addCallback(this);
		mHolder.setFormat(PixelFormat.RGBA_8888);
		
		DisplayMetrics metrics = getResources().getDisplayMetrics();
		
		mHighlightPaint = new Paint();
		mHighlightPaint.setAntiAlias(true);
		mHighlightPaint.setColor(Color.RED);
		
		float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, FONT_TEXT_SIZE, metrics);
		mHighlightPaint.setTextSize(textSize);
		mHighlightPaint.setTextAlign(Align.CENTER);
//		mHighlightPaint.setFakeBoldText(true);
		
		mNormalPaint = new Paint();
		mNormalPaint.setAntiAlias(true);
		mNormalPaint.setColor(Color.BLUE);
		
		mNormalPaint.setTextSize(textSize);
		mNormalPaint.setTextAlign(Align.CENTER);
		
		mVerticalSpac = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, FONT_TEXT_VERTICAL_SPAC, metrics);
		mScrollOffset = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, FONT_VERTICAL_SCROLL_OFFSET, metrics);
		
		mDrawPaint = new Paint();
		changePaint(mNormalPaint);
		
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
		
		mDrawThread = new DrawThread();
		
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		Log.d(TAG, "surfaceChanged....");
		Log.d(TAG, "surfaceChanged...paddingLeft." + getPaddingLeft());
		Log.d(TAG, "surfaceChanged...paddingRight." + getPaddingRight());
		Log.d(TAG, "surfaceChanged...paddingTop." + getPaddingTop());
		Log.d(TAG, "surfaceChanged...paddingBottom." + getPaddingBottom());
		mCenterX = width >> 1;
		mCenterY = height >> 1;
		
		mVisibleWidth = width - getPaddingLeft() - getPaddingRight();
		mVisibleHeight = height - getPaddingTop() - getPaddingBottom();
		
		mDirtyRect = new Rect((int)(mCenterX - mVisibleWidth / 2), (int)(mCenterY - mVisibleHeight / 2), 
				(int)(mCenterX + mVisibleWidth / 2), (int)(mCenterY + mVisibleHeight / 2));
		breakAllData();
		
		mRealTextHeight = (mDrawPaint.getTextSize() + mVerticalSpac) * mData.size() - mVerticalSpac;
		
	}
	
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG, "surfaceCreated....");
		mDrawThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "surfaceDestroyed....");
		mDrawing = false;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(mData == null || mData.size() == 0){
			return true;
		}
		int action = event.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			
			mLastMotionY = event.getY();
			mIsBeingDrag = true;
			
			break;
		case MotionEvent.ACTION_MOVE:
			if(mIsBeingDrag){
				final float y = event.getY();
				final float offset = (int) (mLastMotionY - y);
				mTextScrollDeltaY += offset;
				if(offsetOverSide()){
					mTextScrollDeltaY -= offset;
				} else {
					mLastMotionY = y;
					justUpdateUI();
				}
			}
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_POINTER_UP:
			if(mIsBeingDrag){
				endDrag();
			}
			break;
		}
		return true;
	}

	/**
	 * 是否滚动越界
	 * @return
	 */
	private boolean offsetOverSide(){
		return ((mTextAutoScrollOffsetY + mTextScrollDeltaY <= mRealTextHeight)
				&& (mTextAutoScrollOffsetY + mTextScrollDeltaY >= 0)) ? false : true;
	}
	
	private void endDrag() {
		//TODO 响应完成滚动之后的事件
		mIsBeingDrag = false;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		//设置背景
		canvas.drawColor(Color.WHITE);
		if(mData == null
				|| mData.size() == 0
				|| canvas == null){
			return;
		}
		
		mFontOffsetX = 0;
		mFontOffsetY = 0;
		
		for (int dataIdx = 0; dataIdx < mData.size(); dataIdx++) {
			String drawStr = mData.get(dataIdx);
			mFontOffsetY += mFontSize + mVerticalSpac;
			if(isCenter()){
				changePaint(mHighlightPaint);
			} else {
				changePaint(mNormalPaint);
			}
			canvas.drawText(drawStr, mCenterX + mFontOffsetX, mCenterY + mFontOffsetY - mTextAutoScrollOffsetY - mTextScrollDeltaY, mDrawPaint);
		}
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
	
	private void changePaint(Paint newPaint){
		mDrawPaint.reset();
		mDrawPaint.set(newPaint);
	}
	
	private boolean isCenter() {
		return ((mFontOffsetY <= mTextAutoScrollOffsetY + mTextScrollDeltaY + mFontHeight / 2)
				&& (mFontOffsetY >= mTextAutoScrollOffsetY + mTextScrollDeltaY - mFontHeight / 2)) ? true : false;
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
	
	private void justUpdateUI(){
		try{
			mCanvas = mHolder.lockCanvas(mDirtyRect);
			synchronized (mHolder) {
				onDraw(mCanvas);
			}
		}finally{
			if(mCanvas != null){
				mHolder.unlockCanvasAndPost(mCanvas);
			}
		}
	}
	
	class DrawThread extends Thread implements Callback{
		private static final int RATE = 10;
		DelayScrollTask mDelayScrollTime;
		Handler mHandler;
		boolean nextScroll = true;//进入下次自动滚动
		boolean nextTouchScroll = true;
		public DrawThread() {
			mDelayScrollTime = new DelayScrollTask("");
			mDelayScrollTime.start();
			mHandler = mDelayScrollTime.getHandler(this);
		}
		@Override
		public void run() {
			while(mDrawing){
				if(mDirtyRect == null){
					continue;
				}
				
				if(mIsBeingDrag){
					continue;
				}
				
				try{
					mCanvas = mHolder.lockCanvas(mDirtyRect);
					long startDrawTime = System.currentTimeMillis();
					synchronized (mHolder) {
						onDraw(mCanvas);
						autoScrollByDelay(0);
					}
					long endDrawTime = System.currentTimeMillis();
					while (endDrawTime - startDrawTime < RATE) {
						Thread.sleep(10);
						endDrawTime = System.currentTimeMillis();
					}
				} catch (Exception e) {
				}finally{
					if(mCanvas != null){
						mHolder.unlockCanvasAndPost(mCanvas);
					}
				}
			}
		}

		private void autoScrollByDelay(long delaytime) {
			if(nextScroll){
				nextScroll = false;
				mHandler.removeMessages(0);
				mHandler.sendEmptyMessageDelayed(0, delaytime);
			}
		}

		@Override
		public boolean handleMessage(Message msg) {
			if(msg.what == 0){
				if(!offsetOverSide()){
					mTextAutoScrollOffsetY += mScrollOffset;
				}
				nextScroll = true;
			} 
			return true;
		}
		
	}
	
	class DelayScrollTask extends HandlerThread{

		public DelayScrollTask(String name) {
			super(name);
		}
		
		public Handler getHandler(Handler.Callback callback) {
			return new Handler(getLooper(), callback);
		}
		
	}

}
