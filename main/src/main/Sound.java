package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

public class Sound {
    Clip clip;
    URL soundURL = null;
    GamePanel gp;

    public Sound(String name, GamePanel gp) {
        this.gp = gp;
        soundURL = getClass().getResource("/res/sound/" + name + ".wav");
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL);

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

    public void playMusic() {
        if (clip != null) {
            clip.start();
            this.loop();
        }
    }

    public void playSE() {
        if(gp.mainMenu.isSoundEffectEnabled || gp.startMenu.isSoundEffectEnabled) {
            if (clip != null) {
                clip.start();
                this.loop();
            }
        }
    }

    public void playSEOnce() {
        if (gp.mainMenu.isSoundEffectEnabled || gp.startMenu.isSoundEffectEnabled)
        {
            if(clip != null) {
                clip.setFramePosition(0);
                clip.start();
            }
        }
    }

    public void loop() {
        clip.loop(clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        if (clip != null) {
            clip.stop();
        }
    }
}
