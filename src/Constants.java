package src;
public class Constants {

    public static final int WINDOW_WIDTH = 800;
    public static final int WINDOW_HEIGHT = 600;

    public static final int LOOP_TIME = 100;

    //Terrain Gen
    public static final double SEED = 182.31225277557712; //pretty good seed that I found
    public static final float GROUND_CUTOFF = 0.5f;
    public static final double PERLIN_ZOOM = 20.0;
    public static final int TILE_SIZE = 10; //WINDOW_WIDTH and WINDOW_HEIGHT should be divisible by this
    public static final int PLANT_NUMBER = 10;

    //Simulation constants
    public static final double THIRST_CHANGE = 0.01;
    public static final double THIRST_THRESHOLD = 0.3;
    public static final double HUNGER_CHANGE = 0.01;
    public static final double HUNGER_THRESHOLD = 0.3;
}
