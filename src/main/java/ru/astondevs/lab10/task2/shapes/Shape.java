package ru.astondevs.lab10.task2.shapes;

import java.text.DecimalFormat;

/**
 * @author Tatiana Futarnaya
 */
public interface Shape {
    default double calculatePerimeter() {
        return 0;
    }

    double calculateArea();

    Color  getFillColor();

    Color  getBorderColor();

    default void printShapeDetails() {
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("Периметр: " + df.format(calculatePerimeter()));
        System.out.println("Площадь: " + df.format(calculateArea()));
        System.out.println("Цвет заливки: " + getFillColor());
        System.out.println("Цвет границы: " + getBorderColor());
        System.out.println();
    }
}
