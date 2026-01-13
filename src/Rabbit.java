package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Rabbit extends Animal {
    //Needs
    private float hunger = 0.0f;
    private float thirst = 0.0f;

    //Traits
    private float sensingRadius = 10.0f;

    public int posX;
    public int posY;

    private Random random;

    public Rabbit(String name, float age) {
        super(name, age);
        
        random = new Random();
        while (true) {
            int startingX = (int)Math.round(random.nextDouble() * (Constants.WINDOW_WIDTH / Constants.TILE_SIZE - 1));
            int startingY = (int)Math.round(random.nextDouble() * (Constants.WINDOW_HEIGHT / Constants.TILE_SIZE - 1));

            if (WorldState.Terrain[startingX][startingY] == 1) {
                posX = startingX * Constants.TILE_SIZE;
                posY = startingY * Constants.TILE_SIZE;
                break;
            }

        }
    }

    @Override
    public void makeSound() {
        System.out.println("Rabbit is making a sound");
    }

    public void draw(Graphics g) {
        g.setColor(new Color(1.0f, 1.0f, 1.0f));
        g.fillOval(posX, posY, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }

    public void update() {
        int[] tilePos = {5, 5};
        int[] currentPos = {posX, posY};
        int[] moveDir = nextStepToTile(tilePos, currentPos);

        move(moveDir);

        //moveRandom();
    }

    //change is in tile index not pixel position
    public void move(int[] change) {
        posX += change[0] * Constants.TILE_SIZE;
        posY += change[1] * Constants.TILE_SIZE;
    }

    public void moveRandom() {
        int dX = (int)Math.round(random.nextDouble() * 3.0 - 2.0);
        int dY = (int)Math.round(random.nextDouble() * 3.0 - 2.0);

        //if (WorldState.Terrain[posX])

        int[] change = {dX, dY};
        move(change);
    }

    
}
