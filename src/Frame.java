package src;
// Source - https://stackoverflow.com/a
// Posted by Hovercraft Full Of Eels, modified by community. See post 'Timeline' for change history
// Retrieved 2026-01-08, License - CC BY-SA 3.0

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.*;

public class Frame extends JPanel {


   private NoiseGenerator noise;

    public Frame() {
        noise = new NoiseGenerator(Constants.SEED); 
        createAndShowGui();
    }

   @Override
   protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // draw the rectangle here
        

        for (int x = 0; x < Constants.WINDOW_WIDTH; x += Constants.TILE_SIZE) {
            for (int y = 0; y < Constants.WINDOW_HEIGHT; y += Constants.TILE_SIZE) {
                float noiseVal = Math.max(Math.min((float)((noise.noise(x/Constants.PERLIN_ZOOM, y/Constants.PERLIN_ZOOM) + 1.0) / 2.0), 1.0f), 0.0f);

                if (noiseVal > Constants.GROUND_CUTOFF) {
                    g.setColor(new Color(0.0f, 1.0f, 0.0f));
                } else {
                    g.setColor(new Color(0.0f, 0.0f, 1.0f));
                }
                
                g.fillRect(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }
   }

   @Override
   public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
   }

   // create the GUI explicitly on the Swing event thread
   private void createAndShowGui() {

        JFrame frame = new JFrame("DrawRect");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
        //frame.setResizable(false);
        frame.setTitle("Simulation");
   }
}
