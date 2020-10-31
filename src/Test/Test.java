package Test;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.solve();
    }

    public void solve() {
        int[][] array1 = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        printArray(array1);

        //int[][] array2 = new int[array1.length][array1.length];
        int[][] array2 = array1.clone();
        array2[1][1] = 0;

        printArray(array1);
    }

    private void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
}
