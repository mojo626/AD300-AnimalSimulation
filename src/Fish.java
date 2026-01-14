package src;

public class Fish extends Animal {
    public Fish(String name, int age) {
        super(name, age);

    }

    @Override
    void makeSound() {
        System.out.println(name + " is ... blub blub blub blub");
    }

    public void swim() {
        System.out.println(name + " is swimming");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();

        System.out.println("The fish swims");
    }
}
