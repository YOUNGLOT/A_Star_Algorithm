package AStar;

import java.util.*;

public class AStar {

    private int[][] GOAL_ARRAY;//  목표 Array
    private Map<Integer, Map<Long, int[][]>> queueMap = new HashMap<>();//  우선순위로 정렬 된 맵
    private Map<Long, int[][]> historyMap = new HashMap<>();//  식별 Key 와 Array
    private long resultKey = -1; // 결과값의 key값 (default => -1)

    public AStar(int[][] goalArray, int[][] inputArray) {
        //  목표 Array
        this.GOAL_ARRAY = goalArray;

        //  input Array 를 key = 1 로 Queue 에 put
        Map<Long, int[][]> map = new HashMap<>();
        map.put((long) 1, inputArray);
        queueMap.put(getTriageScore(1, inputArray), map);
    }

    public static void main(String[] args) {
        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        int[][] inputArray = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};

        //int[][] goalArray = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {0, 13, 14, 15}};
        //int[][] inputArray = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};

        AStar aStar = new AStar(goalArray, inputArray);
        aStar.solve();

    }

    private void solve() {
        while (resultKey == -1) {
            logic();
        }

        //결과를 프린트
        printResult();
    }

    //  알고리즘 로직
    private void logic() {
        //  우선 순위가 가장 높은 경우의 Map을 가져온다. (TriageScore가 가장 낮은값)
        int minKey = Collections.min(queueMap.keySet());
        Map<Long, int[][]> map = queueMap.get(minKey);

        //  for 문을 위한 KeySet
        Set<Long> clonedKeySet = cloneSet(map.keySet());

        for (Long key : clonedKeySet) {
            //  답이 나왔을 때 탈출 조건
            if (resultKey != -1) {
                break;
            }

            //  key
            int[][] array = map.get(key);

            //  처리 후 결과 출력을 위해 보관
            historyMap.put(key, array);

            //  처리
            putMovedArray_toQueueMap(key, array);

            //  처리된 map Value를 map에서 제거
            map.remove(key);

            //  queueMap에 min_QueueMapKey의 Value의 맵이 null이면 제거
            if (queueMap.get(minKey).size() == 0) {
                queueMap.remove(minKey);
            }
        }
    }

    private Set<Long> cloneSet(Set<Long> set) {
        Set<Long> newSet = new HashSet<>();
        for (Long key : set) {
            newSet.add(key);
        }
        return newSet;
    }

    private void putMovedArray_toQueueMap(long key, int[][] array) {
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
            if (resultKey != -1) {
                return;
            }
        }
        if (x + 1 < array.length) {
            makeArray_PutMap(key + "2", array, x, y, x + 1, y);
            if (resultKey != -1) {
                return;
            }
        }
        if (y + 1 < array.length) {
            makeArray_PutMap(key + "3", array, x, y, x, y + 1);
            if (resultKey != -1) {
                return;
            }
        }
        if (x - 1 >= 0) {
            makeArray_PutMap(key + "4", array, x, y, x - 1, y);
            if (resultKey != -1) {
                return;
            }
        }
    }

    //  Array를 만들고 QueueMap에 넣는 작업
    private void makeArray_PutMap(String key, int[][] array, int x, int y, int movedX, int movedY) {
        long newKey = Long.parseLong(key);
        int[][] newArray = getMovedArray(x, y, movedX, movedY, array);

        int triageScore = getTriageScore(newKey, newArray);

        if (queueMap.keySet().contains(triageScore)) {
            Map<Long, int[][]> map = queueMap.get(triageScore);
            map.put(newKey, newArray);
            queueMap.replace(triageScore, map);
        } else {
            Map<Long, int[][]> newMap = new HashMap<>();
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

    //   Value는 같지만 참조값이 다른 Array 생성
    private int[][] cloneArray(int[][] array) {
        int[][] newArray = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    //  결과를 출력하는 함수
    private void printResult() {
        String resultKey = Long.toString(this.resultKey);
        while(resultKey.length() != 0){
            printKeyArray(resultKey, historyMap.get(Long.parseLong(resultKey)));
            resultKey = resultKey.substring(0, resultKey.length() - 1);
        }
    }

    //  Key 와 Array를 prlong 하는 함수
    private void printKeyArray(String resultKey, int[][] array) {
        System.out.printf("KEY : %s\n--------------\n", resultKey);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("% 3d ", array[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------");
    }

    //  GOAL_ARRAY와 비교 후 서로 다른 Value의 갯수 return
    private int getTriageScore(long key, int[][] array) {
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
            historyMap.put(resultKey, array);
        }

        return matchPolong + Long.toString(key).length();
    }
}
