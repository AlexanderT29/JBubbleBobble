package model;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class AudioManager {

    private static AudioManager instance;

    public static AudioManager getInstance() {
        if (instance == null) {
            instance = new AudioManager();
        } return instance;
    }

    private AudioManager() {

    }

    public Clip loop(String fileName) {
        Clip clip = null;
        try {
            InputStream is = new BufferedInputStream(new FileInputStream(fileName));
            AudioInputStream audioIs = AudioSystem.getAudioInputStream(is);
            clip = AudioSystem.getClip();
            clip.open(audioIs);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }

    public Clip play(String fileName) {
        Clip clip = null;
        try{
            InputStream in = new BufferedInputStream(new FileInputStream(fileName));
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(in);
            clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
        return clip;
    }

    public void reset() {
        instance = null;
    }
}
