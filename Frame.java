// Source - https://stackoverflow.com/a
// Posted by Hovercraft Full Of Eels, modified by community. See post 'Timeline' for change history
// Retrieved 2026-01-08, License - CC BY-SA 3.0

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Frame extends JPanel {

   private final int WINDOW_WIDTH = 800;
   private final int WINDOW_HEIGHT = 600;

   private NoiseGenerator noise;

    public Frame() {
        createAndShowGui();

        noise = new NoiseGenerator();
    }

   @Override
   protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
        

        for (int x = 0; x < WINDOW_WIDTH; x+=10) {
            for (int y = 0; y < WINDOW_HEIGHT; y += 10) {
                float noiseVal = Math.min((float)((noise.noise(x/20.0, y/20.0) + 1.0) / 2.0), 1.0f);
                g.setColor(new Color(noiseVal, noiseVal, noiseVal));
                g.fillRect(x, y, 10, 10);
            }
        }
   }

   @Override
   public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT);
   }

   // create the GUI explicitly on the Swing event thread
   private void createAndShowGui() {

        JFrame frame = new JFrame("DrawRect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setTitle("Simulation");
   }
}
