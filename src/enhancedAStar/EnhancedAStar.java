package enhancedAStar;

import queue.Data;

import java.util.Comparator;

public class EnhancedAStar {
    private final int[][] GOAL_ARRAY; //    목표 Array
    private final int[][] INPUT_ARRAY;

    private String resultKey = "6"; //  결과값을 넣을 필드
    private int[][] resultArray;
    //  우선순위 큐
    private MyPriorityQueue<Data> mypriorityQueue = new MyPriorityQueue<>(new Comparator<Data>() {
        //  큐 내부에서 우선순위를 매길 때 사용하는 함수
        @Override
        public int compare(Data data1, Data data2) {
            //  두 Data 를 비교해 우선순위 를 int 값으로 return
            int score = getTriageScore_Difference(data1, data2);
            //  2증 삼항 연산자 사용해 봤습니다.
            return (score == 0) ? 0 : (score > 0) ? 1 : -1;
//            if (score == 0) {
//                return 0;
//            }
//            return (score > 0) ? 1 : -1;
        }
    });


    //  생성자
    public EnhancedAStar(int[][] goalArray, int[][] inputArray) {
        //  인풋값으로 필드 생성
        GOAL_ARRAY = goalArray;
        INPUT_ARRAY = inputArray;
        //  큐에 처음에 실행 될 Input_Array를 넣는다 (Key = 5 는 unique한 숫자라 넣음)
        mypriorityQueue.offer(new Data("5", INPUT_ARRAY));
    }

    public static void main(String[] args) {

        //region   샘플용 Arrays
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

//        int[][] goalArray = {{ 1, 5, 2, 3}, {0, 9, 7, 11}, {4, 13, 10, 6}, {8, 12, 14, 15}};
//        int[][] inputArray = {{ 9, 2, 3, 0}, {1, 7, 6, 11}, {5, 8, 14, 10}, {13, 4, 12, 15}};

        //endregion

        int[][] goalArray = {{1, 0, 6, 3}, {4, 2, 9, 7}, {12, 8, 5, 11}, {13, 14, 10, 15}};
        int[][] inputArray = {{14, 4, 6, 3}, {9, 1, 7, 15}, {2, 0, 11, 5}, {12, 8, 13, 10}};

        //  객체 생성
        EnhancedAStar enhancedAStar = new EnhancedAStar(goalArray, inputArray);
        enhancedAStar.solve();
    }

    public void solve() {
        //  Queue에 처리 할 값이 있고, 결과값이 없을 때 : 반복
        while (mypriorityQueue.size() != 0 && resultKey.length() == 1) {
            //  Data 를 Peek!!!!
            Data data = mypriorityQueue.peek();
            mypriorityQueue.remove(data);

            String key = data.getKey();
            int[][] array = data.getArray();

            //  0의 좌표값을 가져온다 (x, y)
            int x = -1, y = -1;
            outer:  //  outer : 다중 반복문을 한번에 나오는 키워드!
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    if (array[i][j] == 0) {
                        x = j;
                        y = i;
                        break outer;
                    }
                }
            }

            //이전 작업과 반대 되는 작업을 막기 위해  Key의 끝자리를 cut!
            String lastLet = key.substring(key.length() - 1);

            // {상, 우, 하, 좌} 로 빈 공간이 이동한 경우
            if (y - 1 >= 0 && !lastLet.equals("3")) {   //  움직일 수 없거나, 이전 작업과 반대되는 작업을 피하는 조건문
                //  Array를 만들고 triageMap에 넣는 작업     *offer() 함수를 Overriding 해서 중복 제거를 함
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

        //  결과를 출력
//        if (resultKey.length() == 1) {
//            System.out.println("이동 가능한 경로가 없습니다.");
//        } else {
//            //  목표 Array 출력
//            printDirectionAndArray("목표 Array", GOAL_ARRAY);
//            //  재귀함수로 그동안의 과정을 구현
//            printProcess_recursive();
//        }
    }

    //  movedX, movedY 로 빈칸이 움직인 Array를 반환 (조건은 상위 코드에서 충족)
    //  print 에서 재사용 됩니다.
    private int[][] getMovedArray(int x, int y, int movedX, int movedY, int[][] array) {
        //  DeepClone 한 Array 생성
        int[][] newArray = cloneArray(array);
        //  움직일 좌표의 value를 0의 좌표에 넣고, 움직일 좌표에 0을 대입
        int value = array[movedY][movedX];
        newArray[movedY][movedX] = 0;
        newArray[y][x] = value;

        return newArray;
    }

    //region DeepClone 함수

    //  DeepClone 함수 (2차 배열은 DeepClone 함수가 없더라구요;;;
    private int[][] cloneArray(int[][] array) {
        int[][] newArray = new int[array.length][array.length];
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                newArray[i][j] = array[i][j];
            }
        }
        return newArray;
    }

    //endregion

    //region TriageScore 함수 (우선순위 함수)

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

    //  원래 1개의 Data에 맞는 TriageScore를 Return 하였으나 , 2중 for문이 있기 때문에 Triage의 비교에 사용 할 때 한번에 두 값을 get
    private int getTriageScore_Difference(Data data1, Data data2) {
        String key1 = data1.getKey(), key2 = data2.getKey();
        int[][] array1 = data1.getArray(), array2 = data2.getArray();

        int score1 = 0, score2 = 0;

        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[i].length; j++) {
                if (GOAL_ARRAY[i][j] != array1[i][j]) {
                    score1++;
                }
                if (GOAL_ARRAY[i][j] != array2[i][j]) {
                    score2++;
                }
            }
        }

        if (score1 == 0) {
            resultKey = key1;
            resultArray = array1;
        }
        if (score2 == 0) {
            resultKey = key2;
            resultArray = array2;
        }

        return (score1 + key1.length()) - (score2 + key2.length());
    }

    //endregion

    //region Print 함수

    //  풀이 과정을 프린트 하는 함수
    private void printProcess_recursive() {

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
            printDeMovedArray_By_LastLet(resultKey.substring(resultKey.length() - 1));
            //  매개변수 대신 멤버변수 를 사용 (재귀함수의 매개변수와 같은 역할)
            resultKey = resultKey.substring(0, resultKey.length() - 1);
            //  재귀 함수 호출
            printProcess_recursive();
        }
    }

    //  매개변수의 String에 따라 움직인 Array를 출력해주는 함수
    private void printDeMovedArray_By_LastLet(String string) {
        //  0의 좌표를 구한 후
        int x = -1, y = -1;
        for (int i = 0; i < resultArray.length; i++) {
            for (int j = 0; j < resultArray[i].length; j++) {
                if (resultArray[i][j] == 0) {
                    x = j;
                    y = i;
                }
            }
        }

        //  Key의 Last Letter 에 맞게 반대로 움직인 후 -> 필드에 저장 -> 출력
        if (string.equals("1")) {
            printDirectionAndArray("up", resultArray = getMovedArray(x, y, x, y + 1, resultArray));
            return;
        }
        if (string.equals("2")) {
            printDirectionAndArray("right", resultArray = getMovedArray(x, y, x - 1, y, resultArray));
            return;
        }
        if (string.equals("3")) {
            printDirectionAndArray("down", resultArray = getMovedArray(x, y, x, y - 1, resultArray));
            return;
        }
        if (string.equals("4")) {
            printDirectionAndArray("left", resultArray = getMovedArray(x, y, x + 1, y, resultArray));
            return;
        }
    }

    //  Key 와 Array를 print 하는 함수
    private void printDirectionAndArray(String direction, int[][] array) {
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

    //endregion


}
