package aStar;

import java.util.Random;
import java.util.Scanner;

public class MakeSample {
    private int input;
    private int[][] array1;
    private int[][] array2;

    public MakeSample(int input) {
        this.input = input;
        array1 = getArray();
        array2 = cloneArray();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MakeSample makeSample = new MakeSample(sc.nextInt());
        makeSample.solve();
    }

    private void solve() {

        for (int i = 0; i < 100; i++) {
            mix2();

        }

        printArray_CtrlCV(array1, "goalArray");
        printArray_CtrlCV(array2, "inputArray");
    }

    private int[][] cloneArray() {
        int startMixCount = 0;
        while(startMixCount++ <= 100) {
            mix1();
        }

        int[][] newArray = new int[input][input];

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                newArray[i][j] = array1[i][j];
            }
        }
        return newArray;
    }

    private int[][] getArray() {
        int[][] array = new int[input][input];

        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = num++;
            }
        }
        return array;
    }

    private void mix1() {
        int direction = new Random().nextInt(4);
        int x = -1, y = -1;
        for (int k = 0; k < array1.length; k++) {
            for (int j = 0; j < array1[k].length; j++) {
                if (array1[k][j] == 0) {
                    x = j;
                    y = k;
                }
            }
        }

        if (direction == 0) {
            if (x + 1 < array1.length) {
                int value = array1[y][x + 1];
                array1[y][x] = value;
                array1[y][x + 1] = 0;
            }
        }
        if (direction == 1) {
            if (x - 1 >= 0) {
                int value = array1[y][x - 1];
                array1[y][x] = value;
                array1[y][x - 1] = 0;
            }
        }
        if (direction == 2) {
            if (y + 1 < array1.length) {
                int value = array1[y + 1][x];
                array1[y][x] = value;
                array1[y + 1][x] = 0;
            }
        }
        if (direction == 3) {
            if (y - 1 >= 0) {
                int value = array1[y - 1][x];
                array1[y][x] = value;
                array1[y - 1][x] = 0;
            }
        }
    }

    private void mix2() {
        int direction = new Random().nextInt(4);

        int x = -1, y = -1;
        for (int k = 0; k < array2.length; k++) {
            for (int j = 0; j < array2[k].length; j++) {
                if (array2[k][j] == 0) {
                    x = j;
                    y = k;
                }
            }
        }
        if (direction == 0) {
            if (x + 1 < array2.length) {
                int value = array2[y][x + 1];
                array2[y][x] = value;
                array2[y][x + 1] = 0;
            }
        }
        if (direction == 1) {
            if (x - 1 >= 0) {
                int value = array2[y][x - 1];
                array2[y][x] = value;
                array2[y][x - 1] = 0;
            }
        }
        if (direction == 2) {
            if (y + 1 < array2.length) {
                int value = array2[y + 1][x];
                array2[y][x] = value;
                array2[y + 1][x] = 0;
            }
        }
        if (direction == 3) {
            if (y - 1 >= 0) {
                int value = array2[y - 1][x];
                array2[y][x] = value;
                array2[y - 1][x] = 0;
            }
        }
    }

    private void printArray_CtrlCV(int[][] array, String name) {

        System.out.printf("int[][] %s = {{ ",name);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (j == 0) {
                    System.out.print(array[i][j]);
                } else {
                    System.out.printf(", %d", array[i][j]);
                }
            }
            if (i != array.length - 1) {
                System.out.printf("}, {");
            }
        }
        System.out.println("}};");
    }

}
