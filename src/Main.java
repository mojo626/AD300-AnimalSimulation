package src;
import java.io.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        WorldState.generateTerrain();
        Frame frame = new Frame();
        
        for (int i = 0; i < 10; i++) {
            Rabbit rabbit = new Rabbit("null", 0);
            WorldState.Rabbits.add(rabbit);
        }



        Animal[] animals = {new Rabbit("judy", -10), new Fox("george", 0), new Fish("Hanako", 226)};

        for (Animal animal : animals) {
            animal.eat();
            animal.sleep();
            
            if (animal instanceof Rabbit) {
                ((Rabbit) animal).hop();
            } else if (animal instanceof Fox) {
                ((Fox) animal).pounce();
            } else if (animal instanceof Fish) {
                ((Fish) animal).swim();
            }

            animal.displayInfo();

            System.out.println("");
        }
        
    }

}