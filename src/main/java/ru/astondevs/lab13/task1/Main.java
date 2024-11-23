package ru.astondevs.lab13.task1;

import java.util.*;

/**
 * @author Tatiana Futarnaya
 */
public class Main {
    public static void main(String[] args) {
        // Создаем массив слов с повторениями
        String[] words = {
                "apple", "banana", "orange", "apple", "banana",
                "grape", "kiwi", "kiwi", "grape", "mango",
                "banana", "kiwi", "apple", "grape", "banana"
        };

        HashSet<String> uniqueWords = new HashSet<>();

        // Добавляем слова в HashSet, дубликаты игнорируются
        for (String word : words) {
            uniqueWords.add(word);
        }

        // Выводим уникальные слова
        System.out.println("Уникальные слова:");
        for (String uniqueWord : uniqueWords) {
            System.out.println(uniqueWord);
        }

        // Используем HashMap для подсчета вхождений
        Map<String, Integer> wordCount = new HashMap<>();

        // Подсчитываем количество вхождений каждого слова
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        // Выводим количество вхождений каждого слова
        System.out.println("Уникальные слова и их количество: " + wordCount);
    }
}
