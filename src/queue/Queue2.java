package queue;

import enhancedAStar.MyPriorityQueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Queue2 {

    private final int[][] GOAL_ARRAY; //    목표 Array
    private final int[][] INPUT_ARRAY;

    private int[][] resultArray;    //  결과값을 넣을 필드
    private String resultKey = "1";

    //  사용할 우선순위 큐!
    private MyPriorityQueue<Data> priorityQueue = new MyPriorityQueue<>(new Comparator<Data>() {
                @Override
                public int compare(Data o1, Data o2) {
                    int score = getTriageScore(o2) - getTriageScore(o1);
                    if (score == 0) {
                        return 0;
                    }
                    return (score < 0) ? 1 : -1;
                }
            });

    private boolean fistTime = true;

    public Queue2(int[][] goalArray, int[][] inputArray) {

        GOAL_ARRAY = goalArray;
        INPUT_ARRAY = inputArray;

        priorityQueue.offer(new Data("5", INPUT_ARRAY));
    }

    public static void main(String[] args) {

//        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
//        int[][] inputArray = {{2, 8, 3}, {1, 6, 4}, {7, 0, 5}};

//        int[][] goalArray = {{ 1, 2, 22, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, {20, 21, 23, 43, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39}, {40, 41, 42, 44, 0, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59}, {60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79}, {80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99}, {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119}, {120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139}, {140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159}, {160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179}, {180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199}, {200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219}, {220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239}, {240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259}, {260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279}, {280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299}, {300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319}, {320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339}, {340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359}, {360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379}, {380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399}};
//        int[][] inputArray = {{ 20, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19}, {40, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39}, {60, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59}, {61, 81, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79}, {80, 0, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99}, {100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119}, {120, 121, 122, 123, 124, 125, 126, 127, 128, 129, 130, 131, 132, 133, 134, 135, 136, 137, 138, 139}, {140, 141, 142, 143, 144, 145, 146, 147, 148, 149, 150, 151, 152, 153, 154, 155, 156, 157, 158, 159}, {160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179}, {180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199}, {200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219}, {220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239}, {240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255, 256, 257, 258, 259}, {260, 261, 262, 263, 264, 265, 266, 267, 268, 269, 270, 271, 272, 273, 274, 275, 276, 277, 278, 279}, {280, 281, 282, 283, 284, 285, 286, 287, 288, 289, 290, 291, 292, 293, 294, 295, 296, 297, 298, 299}, {300, 301, 302, 303, 304, 305, 306, 307, 308, 309, 310, 311, 312, 313, 314, 315, 316, 317, 318, 319}, {320, 321, 322, 323, 324, 325, 326, 327, 328, 329, 330, 331, 332, 333, 334, 335, 336, 337, 338, 339}, {340, 341, 342, 343, 344, 345, 346, 347, 348, 349, 350, 351, 352, 353, 354, 355, 356, 357, 358, 359}, {360, 361, 362, 363, 364, 365, 366, 367, 368, 369, 370, 371, 372, 373, 374, 375, 376, 377, 378, 379}, {380, 381, 382, 383, 384, 385, 386, 387, 388, 389, 390, 391, 392, 393, 394, 395, 396, 397, 398, 399}};

        int[][] goalArray = {{ 1, 5, 2, 3}, {0, 9, 7, 11}, {4, 13, 10, 6}, {8, 12, 14, 15}};
        int[][] inputArray = {{ 9, 2, 3, 0}, {1, 7, 6, 11}, {5, 8, 14, 10}, {13, 4, 12, 15}};
        Queue2 queue = new Queue2(goalArray, inputArray);
        queue.solve();
    }

    public void solve() {


        while (priorityQueue.size() != 0 && resultKey.length() == 1) {
            //  Data 빼옴
            Data x = priorityQueue.peek();
            priorityQueue.remove(x);

            //  0 의 위치 옮긴 후 Queue에 등록
            putNewMovedData_ToQueue(x);

            fistTime = false;
        }

        printResult();
    }

    private void putNewMovedData_ToQueue(Data data) {
        String key = data.getKey();
        int[][] array = data.getArray();

        //  0의 좌표값을 가져온다 (x, y)
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
            putData_InQueue(key + "1", array, x, y, x, y - 1);
            if (resultKey.length() != 1) {
                return;
            }
        }
        if (x + 1 < array.length) {
            putData_InQueue(key + "2", array, x, y, x + 1, y);
            if (resultKey.length() != 1) {
                return;
            }
        }
        if (y + 1 < array.length) {
            putData_InQueue(key + "3", array, x, y, x, y + 1);
            if (resultKey.length() != 1) {
                return;
            }
        }
        if (x - 1 >= 0) {
            putData_InQueue(key + "4", array, x, y, x - 1, y);
            if (resultKey.length() != 1) {
                return;
            }
        }

    }

    private void putData_InQueue(String newKey, int[][] array, int x, int y, int movedX, int movedY) {
        int[][] newArray = getMovedArray(x, y, movedX, movedY, array);

        //  INPUT_ARRAY와 일치하면 등록 x
        if (checkStop(newArray)) {
            return;
        }

        //  triageScore 가 이미 등록이 되어있으면 해당 map 에 추가
        priorityQueue.offer(new Data(newKey, newArray));
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

    //  움직인 결과 값이 시작값과 같을 때 체크
    private boolean checkStop(int[][] array) {
        int stopPoint = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == INPUT_ARRAY[i][j]) {
                    stopPoint++;
                }
            }
        }

        if (fistTime) {
            return false;
        }

        if (stopPoint == array.length * array.length) {
            return true;
        } else {
            return false;
        }
    }

    private int getTriageScore(Data data) {
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
    }

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
        System.out.printf("  Do  : %s\n------------------\n", direction);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.printf("% 4d ", array[i][j]);
            }
            System.out.println();
        }
        System.out.println("------------------");
    }


}
