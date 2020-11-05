package Compare_Memories_Time;

import A_Star_Algorithm.EnhancedAStar1;
import aStar.Array_Array;
import aStar.MakeSample;

public class Time {
    public static void main(String[] args) {
        Time time = new Time();
        time.solve();
    }

    private void solve() {
        int toTime = 10;
        for (int i = 0; i < 1000; i++) {
            if (i % 10 == 0) {
                toTime += 10;
            }
            Array_Array array_array = new MakeSample(4).makeArraySet(toTime);
            String[] count_ActualCount = new EnhancedAStar1(array_array.getArray1(), array_array.getArray2()).solve_Return().split(",");
            System.out.println(String.format("mixTime : %d\ncount   : %s\nactual  : %s\n\n", toTime, count_ActualCount[0], count_ActualCount[1]));
        }
    }
}
