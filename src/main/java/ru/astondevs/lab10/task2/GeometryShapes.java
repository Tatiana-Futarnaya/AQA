package ru.astondevs.lab10.task2;


import ru.astondevs.lab10.task2.shapes.*;

/**
 * @author Tatiana Futarnaya
 */
public class GeometryShapes {
    public static void main(String[] args) {
        try {
            // Правильные параметры
            Shape circle = new Circle(5, Color.RED, Color.BLACK);
            Shape rectangle = new Rectangle(4, 6, Color.GREEN, Color.BLUE);
            Shape triangle = new Triangle(3, 4, 5, Color.YELLOW, Color.ORANGE);

            circle.printShapeDetails();
            rectangle.printShapeDetails();
            triangle.printShapeDetails();

            // Примеры с ошибками
            Shape invalidCircle = new Circle(-5, Color.RED, Color.BLACK);
            Shape invalidRectangle = new Rectangle(0, 6, null, Color.BLUE);
            Shape invalidTriangle = new Triangle(3, -4, 5, Color.YELLOW, Color.ORANGE);
            Shape invalidTriangle2 = new Triangle(1, 2, 3, Color.BLUE, Color.BLACK);

            // Печать характеристик для неверных фигур (не будет выполнено из-за исключений)
            invalidCircle.printShapeDetails();
            invalidRectangle.printShapeDetails();
            invalidTriangle.printShapeDetails();
            invalidTriangle2.printShapeDetails();

        } catch (IllegalArgumentException e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
