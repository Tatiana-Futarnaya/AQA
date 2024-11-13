package ru.astondevs.lab10.task2.shapes;

/**
 * @author Tatiana Futarnaya
 */
public class Rectangle implements Shape {
    private double width;
    private double height;
    private Color fillColor;
    private Color borderColor;

    public Rectangle(double width, double height, Color fillColor, Color borderColor) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Ширина и высота должны быть положительными числами.");
        }
        if (fillColor == null) {
            throw new IllegalArgumentException("Цвет заливки не может быть null.");
        }
        if (borderColor == null) {
            throw new IllegalArgumentException("Цвет границы не может быть null.");
        }
        this.width = width;
        this.height = height;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }

    @Override
    public double calculateArea() {
        return width * height;
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
