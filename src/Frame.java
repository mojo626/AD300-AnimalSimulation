package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Frame extends JPanel {

    public Frame() {
        createAndShowGui();
    }

   @Override
   protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        

        for (int x = 0; x < Constants.WINDOW_WIDTH/Constants.TILE_SIZE; x++) {
            for (int y = 0; y < Constants.WINDOW_HEIGHT/Constants.TILE_SIZE; y ++) {
                float terrain = WorldState.Terrain[x][y];

                if (terrain == 1) {
                    g.setColor(new Color(0.0f, 1.0f, 0.0f));
                } else {
                    g.setColor(new Color(0.0f, 0.0f, 1.0f));
                }
                
                g.fillRect(x * Constants.TILE_SIZE, y * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
            }
        }

        g.setColor(new Color(1.0f, 0.0f, 0.0f));
        g.fillRect(WorldState.Rabbits.get(0).closestWater[0] * Constants.TILE_SIZE, WorldState.Rabbits.get(0).closestWater[1] * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);

        drawAnimals(g);

   }

   private void drawAnimals(Graphics g) {
    //Rabbits
    for (Rabbit rabbit : WorldState.Rabbits) {
        rabbit.draw(g);
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

        // Create a Timer that fires every 20 milliseconds (approx 50 FPS)
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorldState.run(); // Update the model/state
                frame.repaint(); // Request a repaint (calls paintComponent later)
            }
        });
        timer.start(); // Start the "draw loop"
   }
}
