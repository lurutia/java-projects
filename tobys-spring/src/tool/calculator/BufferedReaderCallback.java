package tool.calculator;

import java.io.BufferedReader;
import java.io.IOException;

public interface BufferedReaderCallback<T> {
    T doSomethingWithReader(BufferedReader br) throws IOException;
}
