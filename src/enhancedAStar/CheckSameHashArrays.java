package enhancedAStar;

import queue.Data;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CheckSameHashArrays {
    Set<Data2> set = new HashSet<>();


    public static void main(String[] args) {
        CheckSameHashArrays checkSameHashArrays = new CheckSameHashArrays();
        checkSameHashArrays.solve();
    }

    class Data2 {
        private int key;
        private int[][] array;

        public Data2(int key, int[][] array) {
            this.key = key;
            this.array = array;
        }

        public int getKey() {
            return key;
        }

        public int[][] getArray() {
            return array;
        }

        @Override
        public String toString() {
            return "Data2{" +
                    "key=" + key +
                    ", array=" + Arrays.toString(array) +
                    '}';
        }
    }

    private void solve() {
        int[][] array = new int[4][4];

        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = num++;
            }
        }
        int count = 0;
        for (int i = 0; i < 1000000; i++) {
            count++;
            if(count%10000 == 0)
                System.out.println(count);
            set.add(new Data2(array.hashCode(), array));
            array = mix(array);
            for(Data2 data2 : set){
                if(data2.getKey() == array.hashCode()){
                    if(!data2.getArray().equals(array)){
                        System.out.println(array.hashCode());
                        printArray(array);
                    }
                }
            }
        }


    }

    private int[][] mix(int[][] array) {
        int direction = new Random().nextInt(4);

        int x = -1, y = -1;
        for (int k = 0; k < array.length; k++) {
            for (int j = 0; j < array[k].length; j++) {
                if (array[k][j] == 0) {
                    x = j;
                    y = k;
                }
            }
        }
        if (direction == 0) {
            if (x + 1 < array.length) {
                int value = array[y][x + 1];
                array[y][x] = value;
                array[y][x + 1] = 0;
            }
        }
        if (direction == 1) {
            if (x - 1 >= 0) {
                int value = array[y][x - 1];
                array[y][x] = value;
                array[y][x - 1] = 0;
            }
        }
        if (direction == 2) {
            if (y + 1 < array.length) {
                int value = array[y + 1][x];
                array[y][x] = value;
                array[y + 1][x] = 0;
            }
        }
        if (direction == 3) {
            if (y - 1 >= 0) {
                int value = array[y - 1][x];
                array[y][x] = value;
                array[y - 1][x] = 0;
            }
        }
        return array;
    }

    private void printArray(int[][] array) {
        System.out.printf("------------------\n");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int num = array[i][j];
                if ((num == 0)) {
                    System.out.printf("     ");
                } else {
                    System.out.printf("% 4d ", num);
                }
            }
            System.out.println();
        }
        System.out.println("------------------");
    }
}
