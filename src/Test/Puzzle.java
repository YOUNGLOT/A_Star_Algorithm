package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Puzzle {
    private final int[][] goalArray;
    private final int[][] inputArray;
    private final int arrayLength;

    public Puzzle(int[][] goalArray, int[][] inputArray) {
        this.goalArray = goalArray;
        this.inputArray = inputArray;
        this.arrayLength = goalArray.length;
    }

    public static void main(String[] args) {
        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        int[][] inputAray = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};

        Puzzle puzzle = new Puzzle(goalArray, inputAray);

        puzzle.solve();
    }

    private void solve() {
        int[][] nowArray = inputArray.clone();

        int matchPoint = getMatchPoint(nowArray);

        List<int[][]> movedArray = moveArray(nowArray);

        for (int i = 0; i < movedArray.size(); i++) {

            int[][] ar = movedArray.get(i);

            System.out.println("movedArray 배열 프린트 합니당~~~");

            for (int j = 0; j < ar.length; j++) {
                for (int k = 0; k < ar[j].length; k++) {
                    System.out.print(ar[j][k]);
                }
                System.out.println();
            }
            System.out.println();
        }


    }

    private List<int[][]> moveArray(int[][] nowArray) {
        List<int[][]> movedArrays = new ArrayList<>();

        int x = -1;
        int y = -1;

        for (int i = 0; i < nowArray.length; i++) {
            for (int j = 0; j < nowArray[i].length; j++) {
                if (nowArray[j][i] == 0) {
                    x = j;
                    y = i;
                }
            }
        }

        System.out.println(String.format("0의 좌푯값 x : %d, y : %d", y, x));

        if (y == -1 || x == -1) {
            System.out.println("moveArray에서 문제가 있어요 ㅠㅠ");
        }


        if (y + 1 < arrayLength) {
            int[][] newArray = nowArray.clone();
            int value = newArray[x][y+1];

            newArray[x][y+1] = 0;
            newArray[x][y] = value;

            movedArrays.add(newArray);
        }






        return movedArrays;

    }


    private int getMatchPoint(int[][] movedArray) {
        int matchPoint = 0;
        for (int i = 0; i < movedArray.length; i++) {
            matchPoint += Arrays.compare(movedArray[i], goalArray[i]);
        }
        return matchPoint;
    }
}
