package Managers;

import ObjectUtil.SoundTrack;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashMap;

public class SoundManager {
    public static final int BOUNCE_SOUND = 1000;

    public HashMap<Integer, SoundTrack> soundBundle = new HashMap<>();
    public SoundTrack curPlaying = null;

    public static String getSoundPath(String filename) {
        return "resources\\sound\\"+filename;
    }

    public SoundManager() {
        soundBundle.put(BOUNCE_SOUND, new SoundTrack("bounce_sound.wav", false));
    }

    public void playSoundTrack(int key) {
        if(curPlaying != null)curPlaying.end();
        curPlaying = getSoundTrack(key);
        curPlaying.start();
    }

    public void testPlay(int key){
        SoundTrack tr = getSoundTrack(key);
        tr.getClip().start();
    }

    public void stopSoundTrack() {
        curPlaying.end();
        curPlaying = null;
    }

    public SoundTrack getSoundTrack(int KEY) {
        return soundBundle.get(KEY);
    }
}
