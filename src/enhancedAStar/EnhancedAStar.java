package enhancedAStar;

import queue.Data;

import java.util.Comparator;

public class EnhancedAStar {
    private final int[][] GOAL_ARRAY; //    목표 Array
    private final int[][] INPUT_ARRAY;

    private String resultKey = "1"; //  결과값을 넣을 필드
    private int[][] resultArray;

    private MyPriorityQueue<Data> mypriorityQueue = new MyPriorityQueue<>(new Comparator<Data>() {
        @Override
        public int compare(Data o1, Data o2) {
            int score = getTriageScore_Difference(o1, o2);
            if (score == 0) {
                return 0;
            }
            return (score > 0) ? 1 : -1;
        }
    });


    public EnhancedAStar(int[][] goalArray, int[][] inputArray) {

        GOAL_ARRAY = goalArray;
        INPUT_ARRAY = inputArray;

        mypriorityQueue.offer(new Data("5", INPUT_ARRAY));
    }

    public static void main(String[] args) {

//        int[][] goalArray = {{ 1, 5, 2, 3}, {0, 9, 7, 11}, {4, 13, 10, 6}, {8, 12, 14, 15}};
//        int[][] inputArray = {{ 9, 2, 3, 0}, {1, 7, 6, 11}, {5, 8, 14, 10}, {13, 4, 12, 15}};

        int[][] goalArray = {{ 1, 0, 6, 3}, {4, 2, 9, 7}, {12, 8, 5, 11}, {13, 14, 10, 15}};
        int[][] inputArray = {{ 14, 4, 6, 3}, {9, 1, 7, 15}, {2, 0, 11, 5}, {12, 8, 13, 10}};

        EnhancedAStar enhancedAStar = new EnhancedAStar(goalArray, inputArray);
        enhancedAStar.solve();
    }

    public void solve() {
        while (mypriorityQueue.size() != 0 && resultKey.length() == 1) {
            //  Data 빼옴
            Data data = mypriorityQueue.peek();
            mypriorityQueue.remove(data);

            String key = data.getKey();
            int[][] array = data.getArray();

            //  0의 좌표값을 가져온다 (x, y)
            int x = -1, y = -1;
            outer:
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == 0) {
                        x = j;
                        y = i;
                        break outer;
                    }
                }
            }

            String lastLet = key.substring(key.length()-1);
            // {상, 우, 하, 좌} 로 빈 공간이 이동한 경우,  Array를 만들고 triageMap에 넣는 작업
            if (y - 1 >= 0 && !lastLet.equals("3")) {
                mypriorityQueue.offer(new Data(key + "1", getMovedArray(x, y, x, y - 1, array)));
            } //상
            if (x + 1 < array.length && !lastLet.equals("4")) {
                mypriorityQueue.offer(new Data(key + "2", getMovedArray(x, y, x + 1, y, array)));
            } //우
            if (y + 1 < array.length && !lastLet.equals("1")) {
                mypriorityQueue.offer(new Data(key + "3", getMovedArray(x, y, x, y + 1, array)));
            } //하
            if (x - 1 >= 0 && !lastLet.equals("2")) {
                mypriorityQueue.offer(new Data(key + "4", getMovedArray(x, y, x - 1, y, array)));
            } //좌
        }

        printResult();

    }


    private int[][] getMovedArray(int x, int y, int movedX, int movedY, int[][] array) {
        int[][] newArray = cloneArray(array);

        int value = array[movedY][movedX];
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


    private void printResult() {
        if (resultKey.length() == 1) {
            System.out.println("이동 가능한 경로가 없습니다.");
        } else {
            printKeyArray("목표 Array", GOAL_ARRAY);
            printProcess();
        }
    }

    //  풀이 과정을 프린트 하는 함수
    private void printProcess() {

        /*  재귀함수로 구현해 보았습니다.

        풀이 과정 Array를 저장 할 수도 있지만
        우리의 메모리는 소중하기 때문에

        *풀이과정 Array는 과감히 버리고

        결과값 resultKey (수행 횟수와 , 빈칸의 이동 방향의 정보 내포) 로
        풀이과정 Array를 구해보았습니다.
        */

        //  매개처럼 활용 된 key값 출력
        System.out.printf("  Key    : %s\n", resultKey);
        //  시작 Array의 키값 (length == 1) 일때 멈춤
        if (resultKey.length() == 1) {
            return;
        } else {
            //  Array를 Key값에 따라 움직인 후 출력
            setArray(resultKey.substring(resultKey.length() - 1));
            //  매개변수 대신 멤버변수 를 사용 (재귀함수의 매개변수와 같은 역할)
            resultKey = resultKey.substring(0, resultKey.length() - 1);
            //  재귀 함수 호출
            printProcess();
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
            printKeyArray("up", resultArray = getMovedArray(x, y, x, y + 1, resultArray));
            return;
        }
        if (string.equals("2")) {
            printKeyArray("right", resultArray = getMovedArray(x, y, x - 1, y, resultArray));
            return;
        }
        if (string.equals("3")) {
            printKeyArray("down", resultArray = getMovedArray(x, y, x, y - 1, resultArray));
            return;
        }
        if (string.equals("4")) {
            printKeyArray("left", resultArray = getMovedArray(x, y, x + 1, y, resultArray));
            return;
        }
    }

    //  Key 와 Array를 print 하는 함수
    private void printKeyArray(String direction, int[][] array) {
        System.out.printf("  direct : %s\n------------------\n", direction);
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

    /*private int getTriageScore(Data data) {
        String key = data.getKey();
        int[][] array = data.getArray();
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
    }*/

    private int getTriageScore_Difference(Data data1, Data data2){
        String key1 = data1.getKey(), key2 = data2.getKey();
        int[][] array1 = data1.getArray(), array2 = data2.getArray();

        int score1 = 0, score2 = 0;

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if(GOAL_ARRAY[i][j] != array1[i][j]){
                    score1++;
                }
                if(GOAL_ARRAY[i][j] != array2[i][j]){
                    score2++;
                }
            }
        }

        if (score1 == 0){
            resultKey = key1;
            resultArray = array1;
        }
        if (score2 == 0){
           resultKey = key2;
           resultArray = array2;
        }

        return (score1 + key1.length()) - (score2 + key2.length());
    }
}
