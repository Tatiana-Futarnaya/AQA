package ru.astondevs.lab13.task2;

import java.util.List;

/**
 * @author Tatiana Futarnaya
 */
public class Main {
    public static void main(String[] args) {
        PhoneDirectory phoneDirectory = new PhoneDirectory();

        // Добавление записей
        phoneDirectory.add("Иванов", "123-456");
        phoneDirectory.add("Иванов", "789-012");
        phoneDirectory.add("Петров", "345-678");
        phoneDirectory.add("Петров", "987-654");
        phoneDirectory.add("Сидоров", "555-123");

        // Поиск номеров телефонов
        printPhoneNumbers(phoneDirectory, "Иванов");
        printPhoneNumbers(phoneDirectory, "Петров");
        printPhoneNumbers(phoneDirectory, "Сидоров");
        printPhoneNumbers(phoneDirectory, "Сидоров несуществующий"); // Не найден
    }

    // Метод для вывода телефонных номеров
    private static void printPhoneNumbers(PhoneDirectory directory, String surname) {
        List<String> phoneNumbers = directory.get(surname);
        if (phoneNumbers.isEmpty()) {
            System.out.println("Телефоны для фамилии \"" + surname + "\" не найдены.");
        } else {
            System.out.println("Телефоны " + surname + ": " + String.join(", ", phoneNumbers));
        }
    }
}
