package ru.astondevs.lab4;

import java.util.Arrays;

/**
 * @author Tatiana Futarnaya
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("-1-");
        printThreeWords();
        System.out.println("-------------------");

        System.out.println("-2-");
        checkSumSign();
        System.out.println("-------------------");

        System.out.println("-3-");
        printColor();
        System.out.println("-------------------");

        System.out.println("-4-");
        compareNumbers();
        System.out.println("-------------------");

        System.out.println("-5-");
        int num1 = 5;
        int num2 = 7;
        System.out.println("Сумма в диапазоне от 10 до 20: " + isSumInRange(num1, num2));
        System.out.println("-------------------");

        System.out.println("-6-");
        checkNumber(5);
        checkNumber(-3);
        checkNumber(0);
        System.out.println("-------------------");

        System.out.println("-7-");
        System.out.println(isNegative(-5));
        System.out.println(isNegative(0));
        System.out.println(isNegative(10));
        System.out.println("-------------------");

        System.out.println("-8-");
        printString("Привет, мир!", 5);
        System.out.println("-------------------");

        System.out.println("-9-");
        int year = 2000;
        System.out.println(year + " является високосным? " + isLeapYear(year));
        System.out.println("-------------------");

        System.out.println("-10-");
        replaceElements();
        System.out.println("\n-------------------");

        System.out.println("-11-");
        fillAndPrintArray();
        System.out.println("-------------------");

        System.out.println("-12-");
        modifyArray();
        System.out.println("\n-------------------");

        System.out.println("-13-");
        fillDiagonal();
        System.out.println("-------------------");

        System.out.println("-14-");
        int[] result = createArray(5, 10);

        for (int value : result) {
            System.out.print(value + " ");
        }
        System.out.println("\n-------------------");
    }

    //1. Создайте метод print ThreeWords(),
    // который при вызове должен отпечатать в столбец три слова: Orange, Banana, Apple.
    public static void printThreeWords() {
        System.out.println("Orange");
        System.out.println("Banana");
        System.out.println("Apple");
    }

    //2. Создайте метод checkSumSign(), в теле которого объявите две int, переменные a и b,
    // и инициализируйте их любыми значениями, которыми захотите. Далее метод должен просуммировать
    // эти переменные, и если их сумма больше или равна 0, то вывести в консоль
    // сообщение "Сумма положительная", в противном случае "Сумма отрицательная";

    public static void checkSumSign() {
        int a = 5;
        int b = -3;

        int sum = a + b;

        if (sum >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }

    //3. Создайте метод printColor() в теле которого задайте int переменную value
    // и инициализируйте ее любым значением. Если value меньше 0 (0 включительно),
    // то в консоль метод должен вывести сообщение "Красный", если лежит
    // в пределах от 0 (0 исключительно) до 100 (100 включительно), то "Желтый",
    // если больше 100 (100 исключительно) "Зеленый";
    public static void printColor() {
        int value = 50;

        if (value <= 0) {
            System.out.println("Красный");
        } else if (value > 0 && value <= 100) {
            System.out.println("Желтый");
        } else {
            System.out.println("Зеленый");
        }
    }

    //4. Создайте метод compareNumbers(), в теле которого объявите две int, переменные a и b,
    // и инициализируйте их любыми значениями, которыми захотите. Если a больше или равно b,
    // то необходимо вывести в консоль сообщение "a >= b", в противном случае "а < b";
    public static void compareNumbers() {
        int a = 5;
        int b = 3;

        if (a >= b) {
            System.out.println("a >= b");
        } else {
            System.out.println("a < b");
        }
    }

    //5.  Напишите метод, принимающий на вход два целых числа и проверяющий, что их сумма
    // лежит в пределах от 10 до 20 (включительно), если да вернуть true, в противном случае false.
    public static boolean isSumInRange(int a, int b) {
        int sum = a + b;
        return sum >= 10 && sum <= 20;
    }

    //6. Напишите метод, которому в качестве параметра передается целое число, метод должен напечатать
    // в консоль, положительное ли число передали или отрицательное. Замечание: ноль считаем
    // положительным числом.
    public static void checkNumber(int number) {
        if (number >= 0) {
            System.out.println("Число положительное.");
        } else {
            System.out.println("Число отрицательное.");
        }
    }

    //7. Напишите метод, которому в качестве параметра передается целое число.
    // Метод должен вернуть true, если число отрицательное, и вернуть false если положительное.
    // Замечание: ноль считаем положительным числом.
    public static boolean isNegative(int number) {
        return number < 0;
    }


    //8. Напишите метод, которому в качестве аргументов передается строка и число, метод должен
    // отпечатать в консоль указанную строку, указанное количество раз;
    public static void printString(String str, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(str);
        }
    }

    //9. Напишите метод, который определяет, является ли год високосным, и возвращает boolean
    // (високосный true, не високосный false). Каждый 4-й год является високосным, кроме каждого 100-го,
    // при этом каждый 400-й високосный.
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            }
            return true;
        }
        return false;
    }

    //10. Задать целочисленный массив, состоящий из элементов О и 1.
    // Например: [1, 1, 0, 0, 1, 0, 1, 1, 0, 0). С помощью цикла и условия заменить 0 на 1, 1 на 0;
    public static void replaceElements() {
        int[] array = {1, 1, 0, 0, 1, 0, 1, 1, 0, 0};

        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }

        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    //11. Задать пустой целочисленный массив длиной 100.
    // С помощью цикла заполнить его значениями 1,2,3,4,5,6,7,8 ... 100;
    public static void fillAndPrintArray() {
        int[] array = new int[100];

        for (int i = 0; i < array.length; i++) {
            array[i] = i+1;
        }

        System.out.println(Arrays.toString(array));
    }

    //12. Задать массив [1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 11 пройти по нему циклом, и числа меньшие 6 умножить на 2;
    public static void modifyArray() {
        int[] array = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 11};

        for (int i = 0; i < array.length; i++) {
            if (array[i] < 6) {
                array[i] *= 2;
            }
        }

        for (int num : array) {
            System.out.print(num + " ");
        }
    }

    //13.  Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    // и с помощью цикла(-ов) заполнить его диагональные элементы единицами (можно только одну из диагоналей,
    // если обе сложно). Определить элементы одной из диагоналей можно по следующему принципу:
    // индексы таких элементов равны, то есть [0][0], [1][1], [2][2], ..., [n][n];
    public static void fillDiagonal() {
        int n = 5;
        int[][] array = new int[n][n];

        for (int i = 0; i < n; i++) {
            array[i][i] = 1;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    //14. Написать метод, принимающий на вход два аргумента: len и initialValue, и возвращающий одномерный массив
    // типа int длиной len, каждая ячейка которого равна initialValue.
    public static int[] createArray(int len, int initialValue) {
        if (len < 0) {
            throw new IllegalArgumentException("Длина массива не может быть отрицательной");
        }

        int[] array = new int[len];

        for (int i = 0; i < len; i++) {
            array[i] = initialValue;
        }

        return array;
    }
}
