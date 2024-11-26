package ru.astondevs.test_lab14_junit_5;

import org.junit.jupiter.api.Test;
import ru.astondevs.lab14_junit_5.Factorial;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Tatiana Futarnaya
 */
public class FactorialTest {

    @Test
    public void testFactorialOfZero() {
        assertEquals(1, Factorial.factorial(0));
    }

    @Test
    public void testFactorialOfOne() {
        assertEquals(1, Factorial.factorial(1));
    }

    @Test
    public void testFactorialOfTwo() {
        assertEquals(2, Factorial.factorial(2));
    }

    @Test
    public void testFactorialOfThree() {
        assertEquals(6, Factorial.factorial(3));
    }

    @Test
    public void testFactorialOfFour() {
        assertEquals(24, Factorial.factorial(4));
    }

    @Test
    public void testFactorialOfFive() {
        assertEquals(120, Factorial.factorial(5));
    }

    @Test
    public void testFactorialOfNegativeNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            Factorial.factorial(-1);
        });
        assertEquals("Факториал не определен для отрицательных чисел", exception.getMessage());
    }
}
