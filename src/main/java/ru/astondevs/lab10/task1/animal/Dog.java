package ru.astondevs.lab10.task1.animal;

/**
 * @author Tatiana Futarnaya
 */
public class Dog extends Animal {
    private static int dogCount = 0; // Количество собак

    public Dog(String name) {
        super(name); // Вызов конструктора Animal
        dogCount++;
    }

    public static int getDogCount() {
        return dogCount;
    }

    public void run(int distance) {
        if (distance <= 500) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать больше 500 м.");
        }
    }

    public void swim(int distance) {
        if (distance <= 10) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть больше 10 м.");
        }
    }
}
