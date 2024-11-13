package ru.astondevs.lab10.task1.animal;

/**
 * @author Tatiana Futarnaya
 */
public abstract class Animal {
    private static int count = 0; // Общее количество животных
    protected String name; // Имя животного

    public Animal(String name) {
        this.name = name;
        count++;
    }

    public static int getCount() {
        return count;
    }

    public String getName() {
        return name; // Геттер для имени
    }

    // Абстрактные методы, которые должны быть реализованы подклассами
    public abstract void run(int distance);

    public abstract void swim(int distance);
}

