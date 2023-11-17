package main;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionDelayHandler implements ActionListener {
    private Runnable action;

    public ActionDelayHandler(Runnable action) {
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        action.run();
    }
}