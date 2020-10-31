package Test;

import java.util.*;

public class Puzzle2 {
    private final int[][] goalArray;
    private final int[][] inputArray;
    private final int arrayLength;
    private List<int[][]> resultList;

    public Puzzle2(int[][] goalArray, int[][] inputArray) {
        this.goalArray = goalArray;
        this.inputArray = inputArray;
        this.arrayLength = goalArray.length;
    }

    public static void main(String[] args) {
        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        int[][] inputAray = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};

        Puzzle2 puzzle = new Puzzle2(goalArray, inputAray);
        puzzle.solve();
    }

    private void solve() {
        int[][] nowArray = cloneArray(inputArray);

        int count = 0;

        System.out.println("count = " + (count));
        Map<Integer, Set<int[][]>> map = getMovedMapByMatchPoint(nowArray, count);
        printMap(map, count);

        count++;

        System.out.println("count = " + (count));
        for(Integer integer : map.keySet()){
            Set<int[][]> set = map.get(integer);
            for(int[][] array : set){
                Map<Integer, Set<int[][]>> map1 = getMovedMapByMatchPoint(array, count);
                printMap(map1, count);
            }
        }





    }

    private Map<Integer, Set<int[][]>> getMovedMapByMatchPoint(int[][] nowArray,int count){
        Map<Integer, Set<int[][]>> map = new HashMap<>();

        int x = -1;
        int y = -1;

        for (int i = 0; i < nowArray.length; i++) {
            for (int j = 0; j < nowArray[i].length; j++) {
                if (nowArray[i][j] == 0) {
                    x = j;
                    y = i;
                }
            }
        }

        if (x + 1 < arrayLength) {
            //int[][] array = getMovedArray1(x, y, nowArray);
            int[][] array = getMovedArray(x,y,x+1,y,nowArray);
            int matchPoint = getMatchPoint(array);

            if(map.keySet().contains(matchPoint)){
                Set<int[][]> set = map.get(matchPoint);
                set.add(array);
                map.replace(matchPoint, set);
            }else{
                Set<int[][]> set = new HashSet<>();
                set.add(array);
                map.put(matchPoint, set);
            }
        }

        if (x - 1 >= 0) {
            int[][] array = getMovedArray(x, y,x-1, y, nowArray);
            int matchPoint = getMatchPoint(array);

            if(map.keySet().contains(matchPoint)){
                Set<int[][]> set = map.get(matchPoint);
                set.add(array);
                map.replace(matchPoint, set);
            }else{
                Set<int[][]> set = new HashSet<>();
                set.add(array);
                map.put(matchPoint, set);
            }
        }

        if (y + 1 < arrayLength) {
            int[][] array = getMovedArray(x, y,x,y+1, nowArray);
            int matchPoint = getMatchPoint(array);

            if(map.keySet().contains(matchPoint)){
                Set<int[][]> set = map.get(matchPoint);
                set.add(array);
                map.replace(matchPoint, set);
            }else{
                Set<int[][]> set = new HashSet<>();
                set.add(array);
                map.put(matchPoint, set);
            }
        }

        if (y - 1 >= 0) {
            int[][] array = getMovedArray(x, y,x,y-1, nowArray);
            int matchPoint = getMatchPoint(array);

            if(map.keySet().contains(matchPoint)){
                Set<int[][]> set = map.get(matchPoint);
                set.add(array);
                map.replace(matchPoint, set);
            }else{
                Set<int[][]> set = new HashSet<>();
                set.add(array);
                map.put(matchPoint, set);
            }
        }
        return map;
    }

    private void printList(List<int[][]> arraysList) {
        for (int k = 0; k < arraysList.size(); k++) {
            int[][] array = arraysList.get(k);

            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    System.out.print(array[i][j]);
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    private int getMatchPoint(int[][] array) {
        int matchPoint = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(goalArray[i][j] != array[i][j]){
                    matchPoint++;
                }
            }
        }
        if(matchPoint == 0){
            System.out.println("정답이 나왔어!!");
            //하면서 모든것이 멈추면 되는뎅
        }
        return matchPoint;
    }

    private int[][] getMovedArray(int x, int y, int movedX, int movedY, int[][] nowArray) {
        int[][] newArray = cloneArray(nowArray);

        int value = nowArray[movedY][movedX];

        newArray[movedY][movedX] = 0;
        newArray[y][x] = value;

        return newArray;
    }

    private int[][] cloneArray(int[][] array) {
        int[][] newArray = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    private void printArray(int[][] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private void printMap(Map<Integer, Set<int[][]>> map, int count){
        for( Integer integer : map.keySet()){
            for(int[][] array : map.get(integer)){
                System.out.printf("match : %d , count : %d\n", integer, count);
                printArray(array);
            }
        }
    }

    private void registResultList(int[][] array, int count){
        try{
            resultList.remove(count);
        }catch(Exception e){

        }finally {
            resultList.add(count, array);
        }
    }
}
