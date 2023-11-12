
package panelpackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Custompanel extends JPanel  {
    
    @Override
    protected void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        g.setColor(new Color(164, 113, 230));
        g.fillRect(0, getHeight() - 1, getWidth(), 1);
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(super.getPreferredSize().width, 30);
    }
}
