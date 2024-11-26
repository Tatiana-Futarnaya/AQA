package ru.astondevs.test_lab14_testng;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.astondevs.lab14_testng.FactorialCalculator;

/**
 * @author Tatiana Futarnaya
 */public class FactorialCalculatorTest {

    @Test
    public void testFactorialOfZero() {
        Assert.assertEquals(FactorialCalculator.factorial(0), 1);
    }

    @Test
    public void testFactorialOfPositiveNumber() {
        Assert.assertEquals(FactorialCalculator.factorial(5), 120);
        Assert.assertEquals(FactorialCalculator.factorial(10), 3628800);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFactorialOfNegativeNumber() {
        FactorialCalculator.factorial(-1);
    }
}
