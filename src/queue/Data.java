package queue;

public class Data extends Object {
    private String key;
    private int[][] array;

    public Data(String key, int[][] array) {
        this.key = key;
        this.array = array;
    }

    public String getKey() {
        return key;
    }

    public int[][] getArray() {
        return array;
    }

}
