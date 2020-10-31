package Test;

import java.util.*;

public class AStar {
    private final int[][] goalArray;
    private final int arrayLength;

    private Map<Integer, Set<OMG>> queueMap =  new HashMap<>();;
    private Set<OMG> historys = new HashSet<OMG>();
    private boolean stopSign = false;

    public AStar(int[][] goalArray, int[][] inputArray) {
        this.goalArray = goalArray;
        this.arrayLength = goalArray.length;

        OMG omg = new OMG("1", cloneArray(inputArray));
        Set<OMG> set = new HashSet<>();
        set.add(omg);
        queueMap.put(getMatchPoint(omg), set);
    }

    public static void main(String[] args) {
        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        int[][] inputAray = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};

        AStar aStar = new AStar(goalArray, inputAray);
        aStar.solve();
    }

    private void solve() {

        while(stopSign == false){
            logic();
            if(stopSign == true){
                break;
            }
        }

    }

    private void logic() {
        int minimum_QueueMapKey = Collections.min(queueMap.keySet());
        Set<OMG> omgs = queueMap.get(minimum_QueueMapKey);
        for (OMG omg : omgs){
            historys.add(omg);
            putMovedOMGs_toQueueMap(omg);

            //처리된 omg를 set에서 빼고 set 업데이트, set 이 비었을 경우 key value 지우기
            omgs.remove(omg);
            if(omgs.size() != 0) {
                queueMap.replace(minimum_QueueMapKey, omgs);
            }else{
                queueMap.remove(minimum_QueueMapKey);
            }
        }

    }

    private void putMovedOMGs_toQueueMap(OMG omg) {
        int[][] nowArray = omg.getArray();
        int count = 1;
        int x = -1, y = -1;

        for (int i = 0; i < nowArray.length; i++) {
            for (int j = 0; j < nowArray[i].length; j++) {
                if (nowArray[i][j] == 0) {
                    x = j;
                    y = i;
                }
            }
        }

        if (x + 1 < arrayLength) {
            int[][] array = getMovedArray(x,y,x+1,y,nowArray);
            OMG newomg = new OMG(omg.getHistory()+String.valueOf(count++),array);
            int matchPoint = getMatchPoint(newomg);

            if(queueMap.keySet().contains(matchPoint)){
                Set<OMG> set = queueMap.get(matchPoint);
                set.add(newomg);
                queueMap.replace(matchPoint, set);
            }else{
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                queueMap.put(matchPoint, set);
            }
        }

        if(stopSign == true){
            return;
        }

        if (x - 1 >= 0) {
            int[][] array = getMovedArray(x, y,x-1, y, nowArray);
            OMG newomg = new OMG(omg.getHistory()+String.valueOf(count++),array);
            int matchPoint = getMatchPoint(newomg);

            if(queueMap.keySet().contains(matchPoint)){
                Set<OMG> set = queueMap.get(matchPoint);
                set.add(newomg);
                queueMap.replace(matchPoint, set);
            }else{
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                queueMap.put(matchPoint, set);
            }
        }

        if(stopSign == true){
            return;
        }

        if (y + 1 < arrayLength) {
            int[][] array = getMovedArray(x, y,x,y+1, nowArray);
            OMG newomg = new OMG(omg.getHistory()+String.valueOf(count++),array);
            int matchPoint = getMatchPoint(newomg);

            if(queueMap.keySet().contains(matchPoint)){
                Set<OMG> set = queueMap.get(matchPoint);
                set.add(newomg);
                queueMap.replace(matchPoint, set);
            }else{
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                queueMap.put(matchPoint, set);
            }
        }

        if(stopSign == true){
            return;
        }

        if (y - 1 >= 0) {
            int[][] array = getMovedArray(x, y, x, y - 1, nowArray);
            OMG newomg = new OMG(omg.getHistory()+String.valueOf(count++),array);
            int matchPoint = getMatchPoint(newomg);

            if(queueMap.keySet().contains(matchPoint)){
                Set<OMG> set = queueMap.get(matchPoint);
                set.add(newomg);
                queueMap.replace(matchPoint, set);
            }else{
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                queueMap.put(matchPoint, set);
            }
        }

        if(stopSign == true){
            return;
        }
    }

    private void printMap(Map<Integer, Set<OMG>> map){
        for(Integer integer : map.keySet()){
            System.out.println("\nKEY : "+integer);
            String stromg = map.get(integer).toString();
            System.out.println(stromg);
        }
    }

    private Map<Integer, Set<OMG>> getMap_Moved(OMG omg) {
        Map<Integer, Set<OMG>> map = new HashMap<>();
        int[][] nowArray = omg.getArray();
        int count1 = 1;
        int x = -1, y = -1;

        for (int i = 0; i < nowArray.length; i++) {
            for (int j = 0; j < nowArray[i].length; j++) {
                if (nowArray[i][j] == 0) {
                    x = j;
                    y = i;
                }
            }
        }

        if (x + 1 < arrayLength) {
            int[][] array = getMovedArray(x,y,x+1,y,nowArray);
            OMG newomg = new OMG(omg.getHistory()+String.valueOf(count1++),array);
            int matchPoint = getMatchPoint(newomg);

            if(map.keySet().contains(matchPoint)){
                Set<OMG> set = map.get(matchPoint);
                set.add(newomg);
                map.replace(matchPoint, set);
            }else{
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                map.put(matchPoint, set);
            }
        }

        if (x - 1 >= 0) {
            int[][] array = getMovedArray(x, y,x-1, y, nowArray);
            OMG newomg = new OMG(omg.getHistory()+String.valueOf(count1++),array);
            int matchPoint = getMatchPoint(newomg);

            if(map.keySet().contains(matchPoint)){
                Set<OMG> set = map.get(matchPoint);
                set.add(newomg);
                map.replace(matchPoint, set);
            }else{
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                map.put(matchPoint, set);
            }
        }

        if (y + 1 < arrayLength) {
            int[][] array = getMovedArray(x, y,x,y+1, nowArray);
            OMG newomg = new OMG(omg.getHistory()+String.valueOf(count1++),array);
            int matchPoint = getMatchPoint(newomg);

            if(map.keySet().contains(matchPoint)){
                Set<OMG> set = map.get(matchPoint);
                set.add(newomg);
                map.replace(matchPoint, set);
            }else{
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                map.put(matchPoint, set);
            }
        }

        if (y - 1 >= 0) {
            int[][] array = getMovedArray(x, y, x, y - 1, nowArray);
            OMG newomg = new OMG(omg.getHistory() + String.valueOf(count1++), array);
            int matchPoint = getMatchPoint(newomg);

            if (map.keySet().contains(matchPoint)) {
                Set<OMG> set = map.get(matchPoint);
                set.add(newomg);
                map.replace(matchPoint, set);
            } else {
                Set<OMG> set = new HashSet<>();
                set.add(newomg);
                map.put(matchPoint, set);
            }
        }
        return map;
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

    private int[][] getMovedArray(int x, int y, int movedX, int movedY, int[][] nowArray) {
        int[][] newArray = cloneArray(nowArray);

        int value = nowArray[movedY][movedX];

        newArray[movedY][movedX] = 0;
        newArray[y][x] = value;

        return newArray;
    }

    private int getMatchPoint(OMG omg) {
        int[][] array = omg.getArray();
        int matchPoint = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if(goalArray[i][j] != array[i][j]){
                    matchPoint++;
                }
            }
        }
        if(matchPoint == 0){
            stopSign = true;
            System.out.println("정답이 나왔어!!");
            omg.toString();
            //하면서 모든것이 멈추면 되는뎅
        }
        return matchPoint;
    }
}
