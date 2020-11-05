package Compare_Memories_Time;

import A_Star_Algorithm.EnhancedAStar1;
import aStar.AStar;
import enhancedAStar.EnhancedAStar;
import queue.Queue1;

import java.util.ArrayList;
import java.util.List;

public class Compare {

    private int[][] goalArray = {{1, 5, 2, 3}, {0, 9, 7, 11}, {4, 13, 10, 6}, {8, 12, 14, 15}};
    private int[][] inputArray = {{9, 2, 3, 0}, {1, 7, 6, 11}, {5, 8, 14, 10}, {13, 4, 12, 15}};

    public static void main(String[] args) {
        Compare compare = new Compare();
        compare.solve1();
    }

    private void solve1(){

        System.out.println("         Astar Memory : " + mem1()/1024 + " Kbyte");
//        System.out.println("enhanced Astar1Memory : " + mem11()/1024 + " Kbyte");
//        System.out.println("queue     Astar Memory : " + mem2()/1024 + " Kbyte");
//        System.out.println("useMap    Astar Memory : " + mem3()/1024 + " Kbyte");
        System.out.println("         Astar Time   : " +time1() + " ns");
//        System.out.println("enhanced Astar1Time   : " +time11() + " ns");
//        System.out.println("queue     Astar Time   : " +time2() + " ns");
//        System.out.println("useMap    Astar Time   : " +time3() + " ns");
    }

    private void solve() {
        List<Long> list1 = new ArrayList<>();
        List<Long> list2 = new ArrayList<>();
        List<Long> list3 = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            list1.add(time1() / 10);
        }
        for (int i = 0; i < 10; i++) {
            //list2.add(time2() / 10);
        }
        for (int i = 0; i < 10; i++) {
            //list3.add(time3() / 10);
        }

        long time1 = 0;
        long time2 = 0;
        long time3 = 0;

        for (int i = 0; i < 10; i++) {
            time1 += list1.get(i);
            //time2 += list2.get(i);
            //time3 += list3.get(i);
        }

        System.out.println(time1);
        //  101502362 6229980110
        //  99300350  8441033400

        //  82103705
        //  83991143
        System.out.println(time2);
        //  162162482  4266253640
        //  159383755
        System.out.println(time3);
        //  157810689  4814461930
        //  155608679

    }


    private long time1() {
        long startTime = System.nanoTime();

        EnhancedAStar time1 = new EnhancedAStar(goalArray, inputArray);
        time1.solve();

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private long time11() {
        long startTime = System.nanoTime();

        EnhancedAStar1 time1 = new EnhancedAStar1(goalArray, inputArray);
        time1.solve();

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private long time2() {
        long startTime = System.nanoTime();

        Queue1 time2 = new Queue1(goalArray, inputArray);
        time2.solve();

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private long time3() {
        long startTime = System.nanoTime();

        AStar time2 = new AStar(goalArray, inputArray);
        time2.solve();

        long endTime = System.nanoTime();
        return endTime - startTime;
    }

    private long mem1() {
        long startTime = getNowMemory();

        EnhancedAStar time1 = new EnhancedAStar(goalArray, inputArray);
        time1.solve();

        long endTime = getNowMemory();
        return endTime - startTime;
    }

    private long mem11() {
        long startTime = getNowMemory();

        EnhancedAStar1 time1 = new EnhancedAStar1(goalArray, inputArray);
        time1.solve();

        long endTime = getNowMemory();
        return endTime - startTime;
    }

    private long mem2() {
        long startTime = getNowMemory();

        Queue1 time2 = new Queue1(goalArray, inputArray);
        time2.solve();

        long endTime = getNowMemory();
        return endTime - startTime;
    }

    private long mem3() {
        long startTime = getNowMemory();

        AStar time2 = new AStar(goalArray, inputArray);
        time2.solve();

        long endTime = getNowMemory();
        return endTime - startTime;
    }

    protected static long getNowMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }


}
