package UI;

import main.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class PeriodOfDay extends Menu {
    Timer sleepTimer;

    BufferedImage sleepingMessage;

    public boolean isSleep = false;

    public PeriodOfDay(GamePanel gp) {
        super(gp);
        sleepingMessage = setupImage("sleeping_message", 2);
    }

    public void draw(Graphics2D g2) {
        String timeString = String.format("Day %d, %02d:%02d", gp.gameDay, gp.gameHour, gp.gameMinute);
        drawText(g2, timeString, 10, 40);
        drawNight(g2);
        if(isSleep) {
            gp.music[0].stop();
            drawSleepScene(g2);
            drawImage(g2, sleepingMessage, 130, -50);
        }
    }

    void drawText(Graphics2D g2, String text, int x, int y) {
         // Chuỗi thời gian
        Font font = new Font("Arial", Font.BOLD, 32); // Font chữ
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);
    }

    void drawNight(Graphics2D g2) {
        if(gp.gameHour < 5) {
            g2.setColor(new Color(0, 0, 0, 120));
            g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        } else if(gp.gameHour >= 5 && gp.gameHour  <= 11) {
            g2.setColor(new Color(0, 0, 0, 10));
            g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        } else if(gp.gameHour > 11 && gp.gameHour  <= 17) {
            g2.setColor(new Color(0, 0, 0, 0));
            g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        } else if(gp.gameHour > 17 && gp.gameHour  <= 19) {
            g2.setColor(new Color(0, 0, 0, 80));
            g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        } else if(gp.gameHour  > 19) {
            g2.setColor(new Color(0, 0, 0, 180));
            g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        }
    }

    public void drawSleepScene(Graphics2D g2) {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRect(0, 0, gp.getWidth(), gp.getHeight());
        gp.gameState = gp.pauseState;
        sleepTimer = new Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sleepTimer.stop();
                if(isSleep) {
                    gp.gameDay++;
                }
                gp.gameState = gp.playState;
                gp.gameHour = 5;
                isSleep = false;
                if(gp.mainMenu.isMusicEnabled) {
                    gp.music[0].playMusic();
                } else {
                    gp.music[0].stop();
                }
            }
        });
        sleepTimer.setRepeats(false);
        sleepTimer.start();
        sleepTimer.restart();
    }
}
