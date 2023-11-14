package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL[] = new URL[30];

    public Sound() {
        soundURL[0] = getClass().getResource("/res/sound/background_music.wav");
        soundURL[1] = getClass().getResource("/res/sound/hoe.wav");
        soundURL[2] = getClass().getResource("/res/sound/water.wav");
        soundURL[3] = getClass().getResource("/res/sound/walk.wav");
        soundURL[4] = getClass().getResource("/res/sound/closedoor.wav");

    }

    public void setFile(int i) {
//        try {
//            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
//            clip = AudioSystem.getClip();
//            clip.open(ais);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);

            // Đọc lại dữ liệu âm thanh từ đầu
            byte[] audioData = ais.readAllBytes();
            ByteArrayInputStream bis = new ByteArrayInputStream(audioData);
            AudioInputStream resetAis = new AudioInputStream(bis, ais.getFormat(), audioData.length / ais.getFormat().getFrameSize());

            clip = AudioSystem.getClip();
            clip.open(resetAis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void mute() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
            clip.close();
        }
    }
}
