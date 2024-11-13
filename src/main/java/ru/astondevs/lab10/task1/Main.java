package ru.astondevs.lab10.task1;


import ru.astondevs.lab10.task1.animal.Animal;
import ru.astondevs.lab10.task1.animal.Bowl;
import ru.astondevs.lab10.task1.animal.Cat;
import ru.astondevs.lab10.task1.animal.Dog;

/**
 * @author Tatiana Futarnaya
 */
public class Main {
    public static void main(String[] args) {
        // Создаем миску с первоначальным количеством еды
        Bowl foodBowl = new Bowl(10);

        // Создаем массив животных
        Animal[] animals = {
                new Cat("Мурзик"),
                new Cat("Василий"),
                new Cat("Барсик"),
                new Dog("Шарик"),
                new Dog("Бобик")
        };



        System.out.println("Каждый кот пытается поесть из миски");
        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                ((Cat) animal).eat(foodBowl, 5); // Каждый кот пытается съесть 5 еды
            }
        }



        System.out.println("Выводим информацию о сытости котов");
        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                ((Cat) animal).printSatietyStatus(); // Выводим статус сытости каждого кота
            }
        }

        ((Cat)animals[2]).eat(foodBowl);


        System.out.println("Добавляем больше еды в миску");
        foodBowl.addFood(5);



        System.out.println("Пытаемся снова покормить котов");
        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                ((Cat) animal).eat(foodBowl, 5); // Каждый кот снова пытается съесть 5 еды
            }
        }



        System.out.println("Выводим информацию о сытости котов снова");
        for (Animal animal : animals) {
            if (animal instanceof Cat) {
                ((Cat) animal).printSatietyStatus(); // Выводим статус сытости каждого кота снова
            }
        }


        System.out.println("Выводим общее количество созданных животных");
        System.out.println("Общее количество животных: " + Animal.getCount());
        System.out.println("Количество собак: " + Dog.getDogCount());
        System.out.println("Количество котов: " + Cat.getCatCount());
    }
}
