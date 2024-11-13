package ru.astondevs.lab6;

/**
 * @author Tatiana Futarnaya
 */
public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phone;
    private double salary;
    private int age;


    public Employee(String fullName, String position, String email, String phone, double salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phone = phone;
        this.salary = salary;
        this.age = age;
    }


    public String getInfo() {
        return String.format("Full Name: %s\nPosition: %s\nEmail: %s\nPhone: %s\nSalary: %.2f rub.\nAge: %d years",
                fullName, position, email, phone, salary, age);
    }
}