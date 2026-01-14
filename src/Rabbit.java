package src;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Rabbit extends Animal {
    //Needs
    private float hunger = 0.0f;
    private float thirst = 0.0f;

    //Traits
    private int sensingRadius = 100;

    public int posX;
    public int posY;

    public boolean dead = false;

    private Random random;

    public Rabbit(String name, int age) {
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
        System.out.println(name + " is making a rabbit sound"); //I don't know what sound a rabbit makes
    }

    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("The rabbit hops");
    }

    public void hop() {
        System.out.println(name + " hops hops hops");
    }

    public void draw(Graphics g) {
        g.setColor(new Color(1.0f, 1.0f, 1.0f));
        g.fillOval(posX * Constants.TILE_SIZE, posY * Constants.TILE_SIZE, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }

    public void update() {
        updateNeeds();

        if (thirst >= Constants.THIRST_THRESHOLD) {
            int[] water = closestTile(0);

            if (water[0] != -1) {
                int[] currentPos = {posX, posY};
                int[] moveDir = nextStepToTile(water, currentPos);


                move(moveDir);

                return;
            }
        } else if (hunger >= Constants.HUNGER_THRESHOLD) {
            int[] food = closestTile(2);

            if (food[0] != -1) {
                int[] currentPos = {posX, posY};
                int[] moveDir = nextStepToTile(food, currentPos);

                move(moveDir);

                return;
            }
        }
        

        moveRandom();

    }

    public void updateNeeds() {
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (WorldState.isOutOfBounds(posX + x, posY + y)) {
                    continue;
                }

                if (WorldState.Terrain[posX + x][posY + y] == 0) {
                    thirst = 0.0f;
                }
                if (WorldState.Terrain[posX + x][posY + y] == 2) {
                    hunger = 0.0f;
                }
            }
        }


        thirst += Constants.THIRST_CHANGE;
        hunger += Constants.HUNGER_CHANGE;

        if (thirst > 1.0f || hunger > 1.0f) {
            dead = true;
        }
    }

    public int[] closestTile(int type) {


        float closestDis = 100000000.0f;
        int[] closestPos = {-1, -1};
        for (int x = -sensingRadius; x <= sensingRadius; x++) {
            for (int y = -sensingRadius; y <= sensingRadius; y++) {

                //the tile we are looking at is not within a circle
                if (Math.pow(x, 2) + Math.pow(y, 2) > Math.pow(sensingRadius, 2)) {
                    continue;
                }

                //the tile is outside of the map
                if (WorldState.isOutOfBounds(posX + x, posY + y)) {
                    continue;
                }

                //the tile is water
                if (WorldState.Terrain[posX + x][posY + y] == type) {
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
