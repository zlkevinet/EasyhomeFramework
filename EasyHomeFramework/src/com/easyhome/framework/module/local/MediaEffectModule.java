/**
 * Copyright E.T
 */
package com.easyhome.framework.module.local;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.AudioEffect;
import android.media.audiofx.AutomaticGainControl;
import android.media.audiofx.BassBoost;
import android.media.audiofx.EnvironmentalReverb;
import android.media.audiofx.Equalizer;

import com.easyhome.framework.module.ModuleType;
import com.easyhome.framework.util.Library;
import com.easyhome.framework.util.log.Loger;

/**
 * 音效模块
 * @author Kevin.E.T
 * @mail shuu.ro.zl@gmail.com
 * @since 2012-11-22-下午10:13:06
 * @version 1.0
 */
@SuppressLint("NewApi")
public class MediaEffectModule extends LocalModule 
	implements AudioEffect.OnEnableStatusChangeListener, AudioEffect.OnControlStatusChangeListener
{
	/*0 indicate global setting*/
	private static final int GLOBAL_AUDIO_SESSION_ID = 0;
	protected static final String TAG = "MediaEffectModule";
	protected static final boolean DEBUG = true;
	private Equalizer equalizer;
	private BassBoost bassBoost;
	private EnvironmentalReverb environmentalReverb;
	private AutomaticGainControl automaticGainControl;
	private AcousticEchoCanceler acousticEchoCanceler;
	
	@Override
	public void initModule() {
		super.initModule();
		
	}

	@Override
	public ModuleType getModuleType() {
		return ModuleType.MEDIA_EFFECT;
	}

	public void openAudioEffectUI(){
		Intent i = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
		i.putExtra(AudioEffect.EXTRA_CONTENT_TYPE, AudioEffect.CONTENT_TYPE_MUSIC);
		getContext().startActivity(i);
	}
	
	public void openGlobalEffect(){
		openEffect(GLOBAL_AUDIO_SESSION_ID);
	}
	
	public void closeGlobalEffect(){
		closeEffect(GLOBAL_AUDIO_SESSION_ID);
	}
	
	public void openGlobalVisulizer(){
		openVisulizer(GLOBAL_AUDIO_SESSION_ID);
	}
	
	public void closeGlobalVisulizer(){
		closeVisulizer(GLOBAL_AUDIO_SESSION_ID);
	}
	
	/**
	 * 打开音效
	 * @param audioSessionId
	 */
	public boolean openEffect(int audioSessionId) {
		try{
			addEqualizer(audioSessionId);
			addBassBoost(audioSessionId);
			addEnvironmentalReverb(audioSessionId);
			addNoiseSuppressor(audioSessionId);
			addPresetReverb(audioSessionId);
			addVirtualizer(audioSessionId);
			addAEC(audioSessionId);
			addAGC(audioSessionId);
			return true;
		}catch (Exception e) {
			if(DEBUG) {
				Loger.e(TAG, "open effect fail", e);
			}
			return false;
		}
	}
	
	/**
	 * 关闭音效
	 * @param audioSessionId
	 */
	public void closeEffect(int audioSessionId){
		
	}
	
	/**
	 * 打开可视化
	 * @param audioSessionId
	 */
	public void openVisulizer(int audioSessionId){
		addVisulizer(audioSessionId);
	}
	
	/**
	 * 关闭可视化
	 * @param audioSessionId
	 */
	public void closeVisulizer(int audioSessionId){
		
	}
	
	/**
	 * 添加均衡器
	 * @param audioSessionId
	 */
	private void addEqualizer(int audioSessionId){
		if(!Library.checkVersion()) return;
		equalizer = new Equalizer(0, audioSessionId);
		equalizer.setEnableStatusListener(this);
		equalizer.setControlStatusListener(this);
		equalizer.setParameterListener(new Equalizer.OnParameterChangeListener() {
			
			@Override
			public void onParameterChange(Equalizer effect, int status, int param1, int param2, int value) {
				//获得每个Band的值
				if(DEBUG){
					Loger.d(TAG, "#Equalizer# status = " + status + " param1 = " + param1 + " param2 = " + param2
							+ " value = " + value + " \n effect = " + effect.toString());
				}
			}
		});
		equalizer.setEnabled(true);
	}
	
	/**
	 * 添加低音增强
	 * @param audioSessionId
	 */
	private void addBassBoost(int audioSessionId){
		if(!Library.checkVersion()) return;
		bassBoost = new BassBoost(0, audioSessionId);
		bassBoost.setControlStatusListener(this);
		bassBoost.setEnableStatusListener(this);
		bassBoost.setParameterListener(new BassBoost.OnParameterChangeListener() {
			
			@Override
			public void onParameterChange(BassBoost effect, int status, int param, short value) {
				
			}
		});
		bassBoost.setEnabled(true);
	}
	
	/**
	 * 添加环境混响
	 * @param audioSessionId
	 */
	private void addEnvironmentalReverb(int audioSessionId){
		if(!Library.checkVersion()) return;
		environmentalReverb = new EnvironmentalReverb(0, audioSessionId);
		environmentalReverb.setControlStatusListener(this);
		environmentalReverb.setEnableStatusListener(this);
		environmentalReverb.setEnabled(true);
	}
	
	/**
	 * 添加预设混响
	 * @param audioSessionId
	 */
	private void addPresetReverb(int audioSessionId){
		
	}
	
	/**
	 * 添加声道
	 * @param audioSessionId
	 */
	public void addVirtualizer(int audioSessionId){
		
	}
	
	/**
	 * 添加噪音消声器
	 * @param audioSessionId
	 */
	private void addNoiseSuppressor(int audioSessionId){
		
	}
	
	/**
	 * 添加可视化
	 * @param audioSessionId
	 */
	private void addVisulizer(int audioSessionId){
		
	}
	
	/**
	 * 添加AutomaticGainControl
	 * @param audioSessionId
	 */
	private void addAGC(int audioSessionId){
		if(!Library.checkSpecVersion(android.os.Build.VERSION_CODES.JELLY_BEAN)) return;
		if(!AutomaticGainControl.isAvailable()) return;
		automaticGainControl = AutomaticGainControl.create(audioSessionId);
		automaticGainControl.setControlStatusListener(this);
		automaticGainControl.setEnableStatusListener(this);
		automaticGainControl.setEnabled(true);
	}
	
	/**
	 * 添加AcousticEchoCanceler
	 * @param audioSessionId
	 */
	private void addAEC(int audioSessionId){
		if(!Library.checkSpecVersion(android.os.Build.VERSION_CODES.JELLY_BEAN)) return;
		if(!AcousticEchoCanceler.isAvailable()) return;
		acousticEchoCanceler = AcousticEchoCanceler.create(audioSessionId);
		acousticEchoCanceler.setControlStatusListener(this);
		acousticEchoCanceler.setEnableStatusListener(this);
		acousticEchoCanceler.setEnabled(true);
	}

	@Override
	public void onControlStatusChange(AudioEffect effect, boolean controlGranted) {
		if(DEBUG){
			Loger.d(TAG, "#onControlStatusChange# controlGranted = "+ controlGranted +"\n effect = " + effect.toString());
		}
		if(!controlGranted){
			effect.setEnabled(false);
			effect.release();
		}
	}

	@Override
	public void onEnableStatusChange(AudioEffect effect, boolean enabled) {
		if(DEBUG){
			Loger.d(TAG, "#onEnableStatusChange# enabled = "+ enabled +" \n effect = " + effect.toString());
		}
		if(!enabled){
			effect.setEnabled(false);
			effect.release();
		}
	}
}
