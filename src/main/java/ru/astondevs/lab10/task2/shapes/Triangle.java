package ru.astondevs.lab10.task2.shapes;

/**
 * @author Tatiana Futarnaya
 */
public class Triangle implements Shape {
    private double sideA;
    private double sideB;
    private double sideC;
    private Color fillColor;
    private Color borderColor;

    public Triangle(double sideA, double sideB, double sideC, Color fillColor, Color borderColor) {
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            throw new IllegalArgumentException("Стороны треугольника должны быть положительными числами.");
        }
        if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
            throw new IllegalArgumentException("Стороны не могут образовывать треугольник.");
        }
        if (fillColor == null) {
            throw new IllegalArgumentException("Цвет заливки не может быть null.");
        }
        if (borderColor == null) {
            throw new IllegalArgumentException("Цвет границы не может быть null.");
        }
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
        this.fillColor = fillColor;
        this.borderColor = borderColor;
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }

    @Override
    public double calculateArea() {
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
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

