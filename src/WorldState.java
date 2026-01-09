package src;

import java.util.Vector;

public class WorldState {
    public static int[][] Terrain; //1 is ground, 0 is water
    public static Vector<Rabbit> Rabbits = new Vector<Rabbit>();

    public static void generateTerrain() {
        NoiseGenerator noise = new NoiseGenerator(Constants.SEED); 

        Terrain = new int[Constants.WINDOW_WIDTH/Constants.TILE_SIZE][Constants.WINDOW_HEIGHT/Constants.TILE_SIZE];

        for (int x = 0; x < Constants.WINDOW_WIDTH; x += Constants.TILE_SIZE) {
            for (int y = 0; y < Constants.WINDOW_HEIGHT; y += Constants.TILE_SIZE) {
                float noiseVal = Math.max(Math.min((float)((noise.noise(x/Constants.PERLIN_ZOOM, y/Constants.PERLIN_ZOOM) + 1.0) / 2.0), 1.0f), 0.0f);

                if (noiseVal > Constants.GROUND_CUTOFF) {
                    Terrain[x/Constants.TILE_SIZE][y/Constants.TILE_SIZE] = 1;
                } else {
                    Terrain[x/Constants.TILE_SIZE][y/Constants.TILE_SIZE] = 0;
                }
            }
        }
    }

    public static void run() {
        for (Rabbit rabbit : Rabbits) {
            rabbit.update();
        }
    }
}
