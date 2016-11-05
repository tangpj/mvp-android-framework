package com.example.utillib;

import android.content.Context;
import android.media.AudioManager;

/**
 * @ClassName: AudioManagerUtil
 * @author create by Tang
 * @date date 16/8/22 下午2:42
 * @Description: 扬声器工具类
 */
public class AudioManagerUtil {

    private static int currVolume = 0;

    //打开扬声器
    public static void OpenSpeaker(Context mContext) {
        try{
            //判断扬声器是否在打开

            AudioManager audioManager =(AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            audioManager.setMode(AudioManager.ROUTE_SPEAKER);

            //获取当前通话音量

            currVolume =audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL);

            if(!audioManager.isSpeakerphoneOn()) {
                audioManager.setSpeakerphoneOn(true);

                audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,
                        audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL ),
                        AudioManager.STREAM_VOICE_CALL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //关闭扬声器
    public static void CloseSpeaker(Context mContext) {

        try {
            AudioManager audioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
            if(audioManager != null) {
                if(audioManager.isSpeakerphoneOn()) {
                    audioManager.setSpeakerphoneOn(false);
                    audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL,currVolume,
                            AudioManager.STREAM_VOICE_CALL);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
