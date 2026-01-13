package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Rabbit extends Animal {
    //Needs
    private float hunger = 0.0f;
    private float thirst = 0.0f;

    //Traits
    private int sensingRadius = 10;

    public int posX;
    public int posY;

    public int[] closestWater = {-1, -1};

    private Random random;

    public Rabbit(String name, float age) {
        super(name, age);
        
        random = new Random();
        while (true) {
            int startingX = (int)Math.round(random.nextDouble() * (Constants.WINDOW_WIDTH / Constants.TILE_SIZE - 1));
            int startingY = (int)Math.round(random.nextDouble() * (Constants.WINDOW_HEIGHT / Constants.TILE_SIZE - 1));

            if (WorldState.Terrain[startingX][startingY] == 1) {
                posX = startingX;
                posY = startingY;
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
        g.fillOval(posX * Constants.TILE_SIZE, posY * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }

    public void update() {
        int[] water = closestWater();
        closestWater = water;

        if (water[0] == -1) {
            moveRandom();
            return;
        }

        int[] currentPos = {posX, posY};
        int[] moveDir = nextStepToTile(water, currentPos);

        System.out.println("" + water[0] + ", " + water[1]);

        move(moveDir);

        //moveRandom();
    }

    public int[] closestWater() {


        float closestDis = 100000000.0f;
        int[] closestPos = {-1, -1};
        for (int x = -sensingRadius; x <= sensingRadius; x++) {
            for (int y = -sensingRadius; y <= sensingRadius; y++) {

                //the tile we are looking at is not within a circle
                if (Math.pow(x, 2) + Math.pow(y, 2) > Math.pow(sensingRadius, 2)) {
                    continue;
                }

                //the tile is outside of the map
                if (posX + x < 0 || posY + y < 0 || posX + x >= Constants.WINDOW_WIDTH/Constants.TILE_SIZE || posY + y >= Constants.WINDOW_HEIGHT/Constants.TILE_SIZE) {
                    continue;
                }

                //the tile is water
                if (WorldState.Terrain[posX + x][posY + y] == 0) {
                    float dis = (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));

                    if (dis < closestDis) {
                        closestDis = dis;
                        int[] waterPos = {posX + x, posY + y};
                        closestPos = waterPos;
                    }
                }
            }
        }

        return closestPos;
    }

    //change is in tile index not pixel position
    public void move(int[] change) {
        if (posX + change[0] < 0 || posY + change[1] < 0 || posX + change[0] >= Constants.WINDOW_WIDTH/Constants.TILE_SIZE || posY + change[1] >= Constants.WINDOW_HEIGHT/Constants.TILE_SIZE) {
            return;
        }

        if (WorldState.Terrain[posX + change[0]][posY + change[1]] == 0) {
            return;
        }
        
        posX += change[0];
        posY += change[1];
    }

    public void moveRandom() {
        int dX = (int)Math.round(random.nextDouble() * 3.0 - 2.0);
        int dY = (int)Math.round(random.nextDouble() * 3.0 - 2.0);

        //if (WorldState.Terrain[posX])

        int[] change = {dX, dY};
        move(change);
    }

    
}
