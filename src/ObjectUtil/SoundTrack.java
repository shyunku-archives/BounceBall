package ObjectUtil;

import Managers.SoundManager;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class SoundTrack {
    private Clip clip = null;
    String filename;
    boolean isloop;

    public SoundTrack(String filename, boolean isloop) {
        this.filename = filename;
        this.isloop = isloop;
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(SoundManager.getSoundPath(filename)).getAbsoluteFile()));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if(isloop) clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void start() {
        clip.start();
    }

    public void end() {
        clip.close();
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(SoundManager.getSoundPath(filename)).getAbsoluteFile()));
        } catch (LineUnavailableException | IOException | UnsupportedAudioFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Clip getClip(){
        return clip;
    }
}
