package  panelpackage;

import javax.swing.*;
import java.awt.*;

public class FadeOutTransition {
    public static void fadeOutComponent(Component component, int duration, JPanel mother) {
        Timer timer = new Timer(20, null);
        timer.setInitialDelay(0);
        timer.addActionListener(e -> {
            float alpha = (float) component.getForeground().getAlpha() / 255.0f;
            alpha -= 0.05f; // Adjust the fade out speed if needed

            if (alpha <= 0.0f) {
                alpha = 0.0f;
                ((Timer) e.getSource()).stop();
                mother.remove(component);
            }

            component.setForeground(new Color(164, 113, 230, (int) (alpha * 255)));
            component.repaint();
        });
        timer.setRepeats(true);
        timer.start();
    }
}
