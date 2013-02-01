/**
 * Copyright
 */
package com.easyhome.framework.action;

import java.util.LinkedList;
import java.util.List;

import com.easyhome.framework.util.log.Loger;

/**
 * 动作队列
 * @author zhoulu
 * @since 2012-11-9-上午12:19:41
 * @version 1.0
 */
public class ActionQueue {

	private static final int STATE_RUNNING = 0;
	private static final int STATE_WAIT = 1;
	private static final int STATE_DESTORY = 2;
	private static final boolean DEBUG = true;
	private static final String TAG = ActionQueue.class.getSimpleName();
	
	
	private List<IAction> mActions;
	
	private SendActionWorker mSendActionWorker;
	
	private int mState;
	
	public ActionQueue(){
		mActions = new LinkedList<IAction>();
		mSendActionWorker = new SendActionWorker();
		mSendActionWorker.start();
	}

	/**
	 * 同步地将动作加入到动作队列中
	 * @param action
	 */
	public void putAction(IAction action) {
		if(DEBUG){
			Loger.d(TAG, "putAction " + action.getActionName() + " ...");
		}
		synchronized (mSendActionWorker) {
			mActions.add(action);
			changeState(STATE_RUNNING);
			mSendActionWorker.notify();
		}
	}
	
	public void release(){
		changeState(STATE_DESTORY);
	}
	
	private void changeState(int state){
		if(DEBUG){
			Loger.d(TAG, "changeState : " + state + " ...");
		}
		mState = state;
	}
	
	class SendActionWorker extends Thread {
		
		public SendActionWorker() {
			super("sendActionWorker ...");
			changeState(STATE_RUNNING);
		}
		
		@Override
		public void run() {
			synchronized (mSendActionWorker) {
				while(mState != STATE_DESTORY){
					try {
						if(mActions.isEmpty()){
							changeState(STATE_WAIT);
							if(DEBUG){
								Loger.d(TAG, "wait ...");
							}
							wait();
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					IAction action = mActions.remove(0);
					if(action != null){
						if(DEBUG){
							Loger.d(TAG, "send action " + action.getActionName() + " ...");
						}
						action.send();
					}
				}
			}
		}
	}

}
