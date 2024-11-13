package ru.astondevs.lab10.task2.shapes;

/**
 * @author Tatiana Futarnaya
 */
public enum Color {
    RED, GREEN, BLUE, BLACK, YELLOW, ORANGE, WHITE, PURPLE, CYAN, MAGENTA;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
