package ru.astondevs.lab10.task1.animal;

/**
 * @author Tatiana Futarnaya
 */
public class Cat extends Animal {
    private static int catCount = 0; // Количество котов
    private boolean isFull; // Статус сытости кота

    public Cat(String name) {
        super(name); // Вызов конструктора Animal
        this.isFull = false; // Изначально кот голоден
        catCount++;
    }

    public static int getCatCount() {
        return catCount;
    }

    // Перегруженный метод eat, который использует стандартное количество еды
    public boolean eat(Bowl bowl) {
        return eat(bowl, 3); // Кот поедает стандартное количество еды
    }

    public boolean eat(Bowl bowl, int foodAmount) {
        if (!isFull && bowl.feedCat(foodAmount)) {
            isFull = true; // Кот теперь сыт
            System.out.println(name + " съел и теперь сыт.");
            return true;
        }
        System.out.println(name + " не смог поесть.");
        return false;
    }

    public boolean isFull() {
        return isFull;
    }

    // Метод для вывода информации о сытости кота
    public void printSatietyStatus() {
        System.out.println(name + " " + (isFull ? "сыт." : "всё ещё голоден."));
    }

    @Override
    public void run(int distance) {
        if (distance <= 200) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать больше 200 м.");
        }
    }

    @Override
    public void swim(int distance) {
        System.out.println(name + " не умеет плавать.");
    }
}
