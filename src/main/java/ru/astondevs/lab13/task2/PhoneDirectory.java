package ru.astondevs.lab13.task2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tatiana Futarnaya
 */
public class PhoneDirectory {
    private Map<String, List<String>> directory;

    public PhoneDirectory() {
        directory = new HashMap<>();
    }

    // Метод для добавления записи
    public void add(String surname, String phoneNumber) {
        directory.putIfAbsent(surname, new ArrayList<>());
        directory.get(surname).add(phoneNumber);
    }

    // Метод для поиска номера телефона по фамилии
    public List<String> get(String surname) {
        return directory.getOrDefault(surname, new ArrayList<>());
    }
}
