package tool.calculator;

public interface LineCallback<T> {
    T doSomethingWithLine(String line, T value);
}
