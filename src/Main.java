package src;
import java.io.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        WorldState.generateTerrain();
        Frame frame = new Frame();
        
        for (int i = 0; i < 10; i++) {
            Rabbit rabbit = new Rabbit("null", 0.0f);
            WorldState.Rabbits.add(rabbit);
        }
        
    }

}