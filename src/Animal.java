package src;
public abstract class Animal {
    public String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //for assignment, not used in simulation
    public void eat() {
        System.out.println(name + " is eating");
    }

    //for assignment, not used in simulation
    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    //for assignment, not used in simulation
    abstract void makeSound();

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }

    //tilePos and currentPos should be tile index, not pixel position on screen
    public int[] nextStepToTile(int[] tilePos, int[] currentPos) {

        if (tilePos[0] == currentPos[0] && tilePos[1] == currentPos[1]) {
            int[] noMove = {0, 0};
            return noMove;
        }

        float bestCost = 1000000;
        int[] bestStep = {0, 0};
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                float distance = (float)Math.sqrt(Math.pow(currentPos[0] + x - tilePos[0], 2) + Math.pow(currentPos[1] + y - tilePos[1], 2));

                // //means that this is a diagonal path
                // if (!(x == 0 || y == 0)) {
                //     distance *= 1.44; //diagonal paths are longer so scale the cost accordingly
                // }

                if (distance < bestCost) {
                    bestCost = distance;
                    bestStep[0] = x;
                    bestStep[1] = y;
                }
            }
        }

        return bestStep;
    }
}
