package tool.calculator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
    public Integer calcSum(String filepath) throws IOException {
        LineCallback<Integer> callback = new LineCallback<>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value + Integer.parseInt(line);
            }
        };

        return lineReadTemplate(filepath, callback, 0);
    }

    public Integer calcMultiply(String filepath) throws IOException {
        LineCallback<Integer> callback = new LineCallback<>() {
            @Override
            public Integer doSomethingWithLine(String line, Integer value) {
                return value * Integer.parseInt(line);
            }
        };

        return lineReadTemplate(filepath, callback, 1);
    }

    public String concatenate(String filepath) throws IOException {
        LineCallback<String> callback = new LineCallback<>() {
            @Override
            public String doSomethingWithLine(String line, String value) {
                return value + line;
            }
        };

        return lineReadTemplate(filepath, callback, "");
    }

    public <T> T fileReadTemplate(String filepath, BufferedReaderCallback<T> callback) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filepath));
            T ret = callback.doSomethingWithReader(br);
            return ret;
        } catch (IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) { try { br.close(); } catch(IOException e) {} }
        }
    }

    public <T> T lineReadTemplate(String filepath, LineCallback<T> callback, T initVal) throws IOException {
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(filepath));
            T res = initVal;
            String line = null;
            while((line = br.readLine()) != null) {
                res = callback.doSomethingWithLine(line, res);
            }

            return res;
        } catch(IOException e) {
            System.out.println(e.getMessage());
            throw e;
        } finally {
            if (br != null) { try { br.close(); } catch(IOException e) {} }
        }
    }
}
