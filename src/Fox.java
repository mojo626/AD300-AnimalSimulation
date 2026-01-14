package src;

public class Fox extends Animal {
    public Fox(String name, int age) {
        super(name, age);

    }

    @Override
    void makeSound() {
        System.out.println(name + " is growling");
    }

    public void pounce() {
        System.out.println(name + " is pouncing");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("The fox pounces");
    }
}
