package Test;

import java.util.Arrays;
import java.util.Objects;

public class OMG {
    private String history;
    private int[][] array;

    public OMG(){}
    public OMG(String history, int[][] array) {
        this.history = history;
        this.array = array;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
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
        OMG omg = (OMG) o;
        return Objects.equals(history, omg.history) &&
                Arrays.equals(array, omg.array);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(history);
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }

    @Override
    public String toString() {
        String strArray = "";
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                strArray += array[i][j];
            }
            strArray += "\n";
        }
        return "OMG{" +
                "history='" + history + '\'' +
                ", array=\n" + strArray +
                "}";
    }


}
