package main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URL;

public class Sound {
    public Clip clip;
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
            float initVolume = 0.8f;
            clip = AudioSystem.getClip();
            clip.open(resetAis);
            setVolume(clip, initVolume);
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

    public static void setVolume(Clip clip, float volume) {
        if (clip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minGain = gainControl.getMinimum();
            float maxGain = gainControl.getMaximum();
            float gainRange = maxGain - minGain;
            float adjustedGain = (gainRange * volume) + minGain;
            gainControl.setValue(adjustedGain);
        }
    }

    public static void increaseVolume(Clip clip) {
        if (clip.isControlSupported(FloatControl.Type.VOLUME)) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
            float currentVolume = volumeControl.getValue();
            float maxVolume = volumeControl.getMaximum();
            float newVolume = Math.min(currentVolume + 0.1f, maxVolume);
            System.out.println("Control Type : " + volumeControl.getType());
            System.out.println("currentVolume : " + currentVolume);
            volumeControl.setValue(newVolume);
            System.out.println("newVolume : " + newVolume);
        }
    }

    public static void decreaseVolume(Clip clip) {
        if (clip.isControlSupported(FloatControl.Type.VOLUME)) {
            FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.VOLUME);
            float currentVolume = volumeControl.getValue();
            float minVolume = volumeControl.getMinimum();
            float newVolume = Math.max(currentVolume - 0.1f, minVolume);
            System.out.println("Control Type : " + volumeControl.getType());
            System.out.println("currentVolume : " + currentVolume);
            volumeControl.setValue(newVolume);
            System.out.println("newVolume : " + newVolume);
        }
    }
}
