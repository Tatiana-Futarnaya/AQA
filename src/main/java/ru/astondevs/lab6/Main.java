package ru.astondevs.lab6;

/**
 * @author Tatiana Futarnaya
 */
public class Main {
    public static void main(String[] args) {
        // Создание массива из 5 сотрудников
        Employee[] employees = new Employee[5];

        // Инициализация сотрудников
        employees[0] = new Employee("Иванов Иван Иванович", "Менеджер", "ivanov@example.com", "+7 900 123 45 67", 50000, 30);
        employees[1] = new Employee("Петров Пётр Петрович", "Разработчик", "petrov@example.com", "+7 900 234 56 78", 60000, 28);
        employees[2] = new Employee("Сидоров Сидор Сидорович", "Дизайнер", "sidorov@example.com", "+7 900 345 67 89", 55000, 35);
        employees[3] = new Employee("Кузнецов Алексей Алексеевич", "Аналитик", "kuznetsov@example.com", "+7 900 456 78 90", 70000, 32);
        employees[4] = new Employee("Смирнова Анна Сергеевна", "HR-менеджер", "smirnova@example.com", "+7 900 567 89 01", 45000, 29);

        // Вывод информации о каждом сотруднике
        for (Employee employee : employees) {
            System.out.println(employee.getInfo());
            System.out.println();
        }

        // Создание парка Wonderland с 5 аттракционами
        Park amusementPark = new Park("Wonderland", 5);

        //Добавление названий аттракционов, времени их работы и стоимость
        amusementPark.addAttraction("Roller Coaster", "10:00 - 18:00", 500);
        amusementPark.addAttraction("Ferris Wheel", "10:00 - 20:00", 300);
        amusementPark.addAttraction("Haunted House", "11:00 - 19:00", 400);
        amusementPark.addAttraction("Bumper Cars", "10:00 - 21:00", 250);
        amusementPark.addAttraction("Carousel", "10:00 - 22:00", 200);

        // Вывод информации о каждом аттракционе
        amusementPark.displayInfo();




    }
}
