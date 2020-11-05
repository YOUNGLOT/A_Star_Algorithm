package aStar;

import java.util.*;

public class AStar {

    private final int[][] GOAL_ARRAY;//  목표 Array
    private final int[][] INPUT_ARRAY;
    private Map<Integer, Map<String, int[][]>> triageMap = new HashMap<>();//  우선순위로 정렬 된 맵

    private String resultKey = "1"; // 결과값의 key값
    private int[][] resultArray;

    private boolean fistTime = true;

    public AStar(int[][] goalArray, int[][] inputArray) {

        this.GOAL_ARRAY = goalArray;
        this.INPUT_ARRAY = inputArray;

        //  inputArray를 처리하기 위해 Map에 등록
        Map<String, int[][]> map = new HashMap<>();
        map.put("5", inputArray);
        //  우선순위(TriageScore) 를 구한 후 맞는 Map에 Put
        triageMap.put(getTriageScore("5", inputArray), map);
    }

    public static void main(String[] args) {

        //region Sample Arrayas
        //3x3
//        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
//        int[][] inputArray = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};

        //4x4
//        int[][] goalArray = {{ 4, 1, 6, 2}, {8, 5, 0, 3}, {9, 10, 14, 7}, {12, 13, 15, 11}};
//        int[][] inputArray = {{ 4, 1, 2, 3}, {5, 6, 10, 7}, {8, 9, 11, 15}, {0, 12, 13, 14}};

        //5x5
//        int[][] goalArray = {{ 5, 2, 7, 3, 4}, {6, 1, 12, 9, 0}, {10, 16, 11, 8, 13}, {15, 17, 18, 19, 14}, {20, 21, 22, 23, 24}};
//        int[][] inputArray = {{ 5, 1, 2, 3, 4}, {6, 7, 8, 13, 9}, {10, 11, 12, 18, 14}, {15, 16, 22, 17, 19}, {0, 20, 21, 23, 24}};

        //6x6
//        int[][] goalArray = {{ 6, 2, 8, 3, 4, 5}, {0, 7, 1, 9, 10, 11}, {12, 13, 14, 15, 16, 17}, {18, 19, 20, 21, 22, 23}, {24, 25, 26, 27, 28, 29}, {30, 31, 32, 33, 34, 35}};
//        int[][] inputArray = {{ 1, 7, 2, 3, 4, 5}, {6, 13, 8, 9, 10, 11}, {12, 19, 14, 15, 16, 17}, {18, 20, 26, 21, 22, 23}, {24, 25, 27, 28, 34, 29}, {30, 31, 32, 33, 0, 35}};

        //7x7
//        int[][] goalArray = {{ 1, 2, 3, 4, 0, 5, 6}, {7, 8, 9, 10, 11, 12, 13}, {14, 15, 16, 17, 18, 19, 20}, {21, 22, 23, 24, 25, 26, 27}, {28, 29, 30, 31, 32, 33, 34}, {35, 36, 37, 38, 39, 40, 41}, {42, 43, 44, 45, 46, 47, 48}};
//        int[][] inputArray = {{ 7, 1, 2, 3, 4, 5, 6}, {8, 15, 9, 10, 11, 12, 13}, {14, 22, 16, 17, 18, 19, 20}, {21, 29, 23, 24, 25, 26, 27}, {28, 36, 30, 31, 32, 33, 34}, {35, 43, 37, 38, 39, 40, 41}, {42, 0, 44, 45, 46, 47, 48}};

        //8x8
//        int[][] goalArray = {{ 8, 1, 2, 3, 4, 14, 5, 7}, {9, 17, 10, 12, 13, 6, 15, 0}, {16, 25, 18, 11, 20, 21, 22, 23}, {24, 26, 34, 19, 28, 29, 30, 31}, {32, 33, 42, 27, 36, 37, 38, 39}, {40, 41, 43, 35, 44, 45, 46, 47}, {48, 49, 50, 51, 52, 53, 54, 55}, {56, 57, 58, 59, 60, 61, 62, 63}};
//        int[][] inputArray = {{ 1, 2, 10, 3, 4, 5, 6, 7}, {16, 8, 9, 11, 12, 13, 14, 15}, {24, 17, 18, 19, 20, 21, 22, 23}, {32, 25, 26, 27, 28, 29, 30, 31}, {40, 33, 34, 35, 36, 37, 38, 39}, {48, 41, 42, 43, 44, 45, 46, 47}, {56, 49, 50, 51, 52, 53, 54, 55}, {57, 58, 59, 0, 60, 61, 62, 63}};

        //9x9
//        int[][] goalArray = {{ 9, 1, 2, 3, 4, 5, 6, 7, 8}, {18, 10, 11, 12, 13, 14, 15, 16, 17}, {27, 19, 20, 21, 22, 23, 24, 25, 26}, {36, 28, 29, 30, 31, 32, 33, 34, 35}, {45, 37, 38, 39, 40, 41, 42, 43, 44}, {54, 46, 47, 48, 49, 50, 51, 52, 53}, {0, 55, 56, 57, 58, 59, 60, 61, 62}, {63, 64, 65, 66, 67, 68, 69, 70, 71}, {72, 73, 74, 75, 76, 77, 78, 79, 80}};
//        int[][] inputArray = {{ 1, 2, 11, 3, 4, 5, 6, 7, 8}, {9, 10, 12, 13, 14, 15, 16, 17, 26}, {18, 19, 20, 21, 22, 23, 24, 25, 35}, {27, 28, 29, 30, 31, 32, 33, 34, 44}, {36, 37, 38, 39, 40, 41, 42, 43, 53}, {45, 46, 47, 48, 49, 59, 50, 51, 52}, {54, 55, 65, 56, 57, 58, 60, 61, 62}, {63, 64, 74, 66, 67, 68, 69, 70, 71}, {72, 0, 73, 75, 76, 77, 78, 79, 80}};

        //endregion

//        int[][] goalArray = {{ 1, 7, 2, 3, 4, 5}, {6, 8, 14, 9, 10, 11}, {12, 19, 13, 15, 16, 17}, {18, 20, 26, 21, 23, 29}, {24, 25, 27, 28, 34, 0}, {30, 31, 32, 33, 35, 22}};
//        int[][] inputArray = {{ 6, 1, 2, 3, 4, 5}, {12, 7, 8, 9, 10, 11}, {13, 19, 14, 15, 16, 17}, {24, 18, 20, 21, 22, 23}, {25, 26, 32, 27, 28, 29}, {30, 31, 0, 33, 34, 35}};

        int[][] goalArray = {{ 4, 2, 7, 0}, {8, 1, 10, 6}, {9, 11, 15, 3}, {12, 5, 13, 14}};
        int[][] inputArray = {{ 1, 2, 6, 3}, {4, 9, 5, 7}, {8, 13, 10, 11}, {12, 14, 0, 15}};

        AStar aStar = new AStar(goalArray, inputArray);
        aStar.solve();

    }

    public void solve() {
        //  결과값이 정해 지면 멈춤
        while (resultKey.length() == 1) {
            //  우선 순위가 가장 높은 경우의 TriageScore의 Map 들을 가져온다. (TriageScore가 가장 낮은값)
            int minTriageScore = Collections.min(triageMap.keySet());
            Map<String, int[][]> map_In_TriageScore = triageMap.get(minTriageScore);

            //  for 문을 위한 KeySet
            //  clone 이유 : 향상된 for 문은 iterator 를 이용하기 때문에 동기화를 지원한다.
            Set<String> clonedKeySet = cloneSet(map_In_TriageScore.keySet());
            //Set<String> clonedKeySet = map_In_TriageScore.keySet();
            //  Exception in thread "main" java.util.ConcurrentModificationException
//                  at java.base/java.util.HashMap$HashIterator.nextNode(HashMap.java:1493)
//                  at java.base/java.util.HashMap$KeyIterator.next(HashMap.java:1516)
//                  at aStar.AStar.solve(AStar.java:78)
//                  at aStar.AStar.main(AStar.java:63)
            for (String key : clonedKeySet) {

                //  Array를 가져와서
                int[][] array = map_In_TriageScore.get(key);

                //  빈칸을 움직인 Array를 triageMap 넣는다
                putMovedArray_toTriageMap(key, array);

                //  처리된 map Value를 map에서 제거
                map_In_TriageScore.remove(key);

                //  triageMap에 min_TriageMapKey의 Value의 맵이 null이면 제거
                if (triageMap.get(minTriageScore).size() == 0) {
                    triageMap.remove(minTriageScore);
                }

                //  답이 나왔을 때 탈출 조건
                if (resultKey.length() != 1) {
                    break;
                }
            }
            fistTime = false;
        }

        //  결과를 프린트 (풀이 과정)
        //printResult();
    }

    //region Array Control Methods

    //  triageMap에 빈 칸이 움직인 Array 넣기
    private void putMovedArray_toTriageMap(String key, int[][] array) {
        //  빈 공간(숫자 0으로 치환) 의 값을 넣을 변수 (default -1)
        int x = -1, y = -1;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    x = j;
                    y = i;
                }
            }
        }

        // {상, 우, 하, 좌} 로 빈 공간이 이동한 경우
        if (y - 1 >= 0) {
            //  Array를 만들고 triageMap에 넣는 작업
            makeArray_PutMap(key + "1", array, x, y, x, y - 1);
            if (resultKey.length() != 1) {
                return;
            }
        }
        if (x + 1 < array.length) {
            makeArray_PutMap(key + "2", array, x, y, x + 1, y);
            if (resultKey.length() != 1) {
                return;
            }
        }
        if (y + 1 < array.length) {
            makeArray_PutMap(key + "3", array, x, y, x, y + 1);
            if (resultKey.length() != 1) {
                return;
            }
        }
        if (x - 1 >= 0) {
            makeArray_PutMap(key + "4", array, x, y, x - 1, y);
            if (resultKey.length() != 1) {
                return;
            }
        }
    }

    //  Array를 만들고 triageMap에 넣는 작업, (Key는 위에서 만들어줌)
    private void makeArray_PutMap(String newKey, int[][] array, int x, int y, int movedX, int movedY) {
        int[][] newArray = getMovedArray(x, y, movedX, movedY, array);
        int triageScore = getTriageScore(newKey, newArray);

        //  INPUT_ARRAY와 일치하면 등록 x
        if(checkStop(newArray)){ return; }

        //  triageScore 가 이미 등록이 되어있으면 해당 map 에 추가
        if (triageMap.keySet().contains(triageScore)) {
            Map<String, int[][]> map = triageMap.get(triageScore);
            map.put(newKey, newArray);
            triageMap.replace(triageScore, map);
        } else {
            Map<String, int[][]> newMap = new HashMap<>();
            newMap.put(newKey, newArray);
            triageMap.put(triageScore, newMap);
        }
    }

    //  움직인 결과 값이 시작값과 같을 때 체크
    private boolean checkStop(int[][] array){
        int stopPoint = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(array[i][j] == INPUT_ARRAY[i][j]){
                    stopPoint++;
                }
            }
        }

        if(fistTime){
            return false;
        }

        if(stopPoint == array.length * array.length){
            return true;
        }else{
            return false;
        }
    }

    //  x,y 위치의 value 와 movedX, movedY 위치의 value 를 바꾼 array를 return
    private int[][] getMovedArray(int x, int y, int movedX, int movedY, int[][] array) {
        int[][] newArray = cloneArray(array);
        int value = array[movedY][movedX];

        newArray[movedY][movedX] = 0;
        newArray[y][x] = value;

        return newArray;
    }

    //  GOAL_ARRAY와 비교 후 서로 다른 Value의 갯수 return
    private int getTriageScore(String key, int[][] array) {
        // default 0
        int matchPoint = 0;

        //  다른 항목이 있을 때 마다 matchPolong++
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (GOAL_ARRAY[i][j] != array[i][j]) {
                    matchPoint++;
                }
            }
        }

        //  value가 같은 Array 일 경우 결과값 등록!
        if (matchPoint == 0) {
            resultKey = key;
            resultArray = array;
        }

        return matchPoint + key.length();
    }

    //endregion

    //region DeepClone Methods

    //  참조값이 다른 복재된 함수가 필요했습니다.
    private int[][] cloneArray(int[][] array) {
        int[][] newArray = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    private Set<String> cloneSet(Set<String> set) {
        Set<String> newSet = new HashSet<>();
        for (String key : set) {
            newSet.add(key);
        }
        return newSet;
    }

    //endregion

    //region 프린트 Methods

    private void printResult() {
        if (resultKey.length() == 1 || resultKey.equals("99")) {
            System.out.println("이동 가능한 경로가 없습니다.");
        } else {
            printKeyArray("목표 Array", GOAL_ARRAY);
            printProcess(resultKey);
        }
    }

    //  풀이 과정을 프린트 하는 함수
    private void printProcess(String resultKey) {

        /*  재귀함수로 구현해 보았습니다.

        풀이 과정 Array를 저장 할 수도 있지만
        우리의 메모리는 소중하기 때문에

        *풀이과정 Array는 과감히 버리고

        결과값 resultKey (수행 횟수와 , 빈칸의 이동 방향의 정보 내포) 로
        풀이과정 Array를 구해보았습니다.
        */

        //  매개변수로 받는 key값 출력
        System.out.printf("  Key : %s\n", resultKey);
        //  시작 Array의 키값을 1로 지정함 -> 1에서 멈춤
        if (resultKey.length() == 1) {
            return;
        } else {
            //  Array를 Key값에 따라 움직인 후 출력
            setArray(resultKey.substring(resultKey.length() - 1));
            printProcess(resultKey.substring(0, resultKey.length() - 1));
        }
    }

    //  빈칸이 반대로 이동한 Array
    private void setArray(String string) {
        int x = -1, y = -1;

        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[i].length; j++) {
                if (resultArray[i][j] == 0) {
                    x = j;
                    y = i;
                }
            }
        }

        if (string.equals("1")) {
            resultArray = getMovedArray(x, y, x, y + 1, resultArray);
            printKeyArray("up", resultArray);
        }
        if (string.equals("2")) {
            resultArray = getMovedArray(x, y, x - 1, y, resultArray);
            printKeyArray("right", resultArray);
        }
        if (string.equals("3")) {
            resultArray = getMovedArray(x, y, x, y - 1, resultArray);
            printKeyArray("down", resultArray);
        }
        if (string.equals("4")) {
            resultArray = getMovedArray(x, y, x + 1, y, resultArray);
            printKeyArray("left", resultArray);
        }
    }

    //  Key 와 Array를 print 하는 함수
    private void printKeyArray(String direction, int[][] array) {
        System.out.printf("  Do  : %s\n----------------\n", direction);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("% 3d ", array[i][j]);
            }
            System.out.println();
        }
        System.out.println("----------------");
    }
    //endregion
}
