package test;

import org.junit.Before;
import tool.calculator.Calculator;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalcSumTest {
    Calculator calculator;
    String numFilepath;

    @Before
    public void setUp() {
        this.calculator = new Calculator();
        this.numFilepath = getClass().getResource("/numbers.txt").getPath();
    }

    @Test
    public void sumOfNumbers() throws IOException {
        int sum = this.calculator.calcSum(this.numFilepath);
        assertThat(sum, is(10));
    }

    @Test
    public void multiplyOfNumbers() throws IOException {
        assertThat(calculator.calcMultiply(this.numFilepath), is(24));
    }

    @Test
    public void concatenateOfNumbers() throws IOException {
        String str = this.calculator.concatenate(this.numFilepath);
        assertThat(str, is("1234"));
    }
}
