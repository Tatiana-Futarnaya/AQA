package ru.astondevs.lab10.task2.shapes;

/**
 * @author Tatiana Futarnaya
 */
public class Circle implements Shape {
    private double radius;
    private Color fillColor;
    private Color borderColor;

    public Circle(double radius, Color fillColor, Color borderColor) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным числом.");
        }
        if (fillColor == null) {
            throw new IllegalArgumentException("Цвет заливки не может быть null.");
        }
        if (borderColor == null) {
            throw new IllegalArgumentException("Цвет границы не может быть null.");
        }
        this.radius = radius;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public Color getBorderColor() {
        return borderColor;
    }
}

