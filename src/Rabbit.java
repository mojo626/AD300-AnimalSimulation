package src;

public class Rabbit extends Animal {

    public Rabbit(String name, float age) {
        super(name, age);
        
    }

    @Override
    public void makeSound() {
        System.out.println("Rabbit is making a sound");
    }
    
}
