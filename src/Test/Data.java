package Test;

import java.util.Arrays;
import java.util.Objects;

public class Data {
    private int match;
    private int count;
    private int[][] array;

    public Data(){}
    public Data(int match, int count, int[][] array) {
        this.match = match;
        this.count = count;
        this.array = array;
    }

    public int getMatch() {
        return match;
    }

    public void setMatch(int match) {
        this.match = match;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] array) {
        this.array = array;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Data data = (Data) o;
        return match == data.match &&
                count == data.count &&
                Arrays.equals(array, data.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(match, count);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        String arrayStr = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                arrayStr += array[i][j];
            }
            arrayStr += "\n";
        }
        arrayStr += "\n";
        return "Data{" +
                "match=" + match +
                ", count=" + count +
                "array=\n"+
                arrayStr;
    }
}
