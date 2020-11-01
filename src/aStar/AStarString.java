package aStar;

import java.util.*;

public class AStarString {

    private final int[][] GOAL_ARRAY;//  목표 Array
    private Map<Integer, Map<String, int[][]>> queueMap = new HashMap<>();//  우선순위로 정렬 된 맵
    private String resultKey = "1"; // 결과값의 key값 (default => -1)
    private int[][] resultArray;

    public AStarString(int[][] goalArray, int[][] inputArray) {
        //  목표 Array
        this.GOAL_ARRAY = goalArray;

        //  결과 Array
        this.resultArray = new int[goalArray.length][goalArray.length];

        //  inputArray 를 queueMap 에 넣음 , key는 1로 임의로 지정
        Map<String, int[][]> map = new HashMap<>();
        map.put("5", inputArray);
        queueMap.put(getTriageScore("5", inputArray), map);
    }

    public static void main(String[] args) {

        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        int[][] inputArray = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};


        //int[][] goalArray = {{ 2, 3, 4, 1}, {5, 6, 7, 8}, {9, 10, 11, 12}, {0, 13, 14, 15}};
        //int[][] inputArray = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

        AStarString aStarString = new AStarString(goalArray, inputArray);
        aStarString.solve();

    }

    private void solve() {
        //  결과값이 정해 지면 멈춤
        while (resultKey.length() == 1) {
            logic();
        }

        //  결과를 프린트 (풀이 과정)
        printResult();
    }

    //  알고리즘 로직
    private void logic() {
        //  우선 순위가 가장 높은 경우의 TriageScore의 Map 들을 가져온다. (TriageScore가 가장 낮은값)
        int minTriageScore = Collections.min(queueMap.keySet());
        Map<String, int[][]> map_In_TriageScore = queueMap.get(minTriageScore);

        //  for 문을 위한 KeySet
        //  clone 이유 : 향상된 for 문은 iterator 를 이용하기 때문에 동기화를 지원한다.
        Set<String> clonedKeySet = cloneSet(map_In_TriageScore.keySet());
        //
        for (String key : clonedKeySet) {
            //  답이 나왔을 때 탈출 조건
            if (resultKey.length() != 1) {
                break;
            }

            //  Array를 가져와서
            int[][] array = map_In_TriageScore.get(key);

            //  빈칸을 움직인 Array를 queueMap에 넣는다
            putMovedArray_toQueueMap(key, array);

            //  처리된 map Value를 map에서 제거
            map_In_TriageScore.remove(key);

            //  queueMap에 min_QueueMapKey의 Value의 맵이 null이면 제거
            if (queueMap.get(minTriageScore).size() == 0) {
                queueMap.remove(minTriageScore);
            }
        }
    }

    //region Array Control Methods

    //  queueMap에 빈 칸이 움직인 Array 넣기
    private void putMovedArray_toQueueMap(String key, int[][] array) {
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
            //  Array를 만들고 QueueMap에 넣는 작업
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

    //  Array를 만들고 QueueMap에 넣는 작업, (Key는 위에서 만들어줌)
    private void makeArray_PutMap(String newKey, int[][] array, int x, int y, int movedX, int movedY) {
        int[][] newArray = getMovedArray(x, y, movedX, movedY, array);

        int triageScore = getTriageScore(newKey, newArray);

        //  triageScore 가 이미 등록이 되어있으면 해당 map 에 추가
        if (queueMap.keySet().contains(triageScore)) {
            Map<String, int[][]> map = queueMap.get(triageScore);
            map.put(newKey, newArray);
            queueMap.replace(triageScore, map);
        } else {
            Map<String, int[][]> newMap = new HashMap<>();
            newMap.put(newKey, newArray);
            queueMap.put(triageScore, newMap);
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
        int matchPolong = 0;

        //  다른 항목이 있을 때 마다 matchPolong++
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (GOAL_ARRAY[i][j] != array[i][j]) {
                    matchPolong++;
                }
            }
        }

        //  value가 같은 Array 일 경우 결과값 등록!
        if (matchPolong == 0) {
            resultKey = key;
            resultArray = array;
        }

        return matchPolong + key.length();
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
        if (resultKey.length() == 1) {
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
        System.out.printf("  Do  : %s\n--------------\n", direction);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("% 3d ", array[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------");
    }
    //endregion
}
