package enhancedAStar;

import queue.Data;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MyPriorityQueue<E> extends PriorityQueue<E> {
    //  Queue 에 들어온 Data의 (int) Array.HashCode(); 값을 저장하는 Set
    private Set<Integer> data_ArrayHashCodeSet;
    private Set<int[][]> data_ArraySet;

    public MyPriorityQueue(){};
    //  compare 메소드를 사용 Class 에서 재 정의 하기 위해 매개변수로 Comparator를 받음
    public MyPriorityQueue(Comparator comparator) { // 생성자
        super(comparator);
        data_ArrayHashCodeSet = new HashSet<>();
        data_ArraySet = new HashSet<>();
    }

    /*
    //  Queue Class 에서 재 정의 할때 : 이렇게 하면 됩니다
    public MyPriorityQueue(){
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
    }
    */

    //  Data 를 offer (add) 할 때, 중복 확인 후 상위클래스 offer 호출
    @Override
    public boolean offer(E data) {
        //  매개변수로 Data 가 들어 온 것을 확인
        if (data instanceof Data) {
            //  HashCode 추출 후
            int arrayHashCode = ((Data) data).getArray().hashCode();
            //  Set에서 확인함
            if (data_ArrayHashCodeSet.contains(arrayHashCode)) {
                //  HashCode가 중복되면 Array 자체를 비교
                int[][] dataArray = ((Data) data).getArray();
                if (data_ArraySet.contains(dataArray)) {
                    return false;
                }
                data_ArraySet.add(dataArray);
            }
            //  Set에 없을 경우 등록
            data_ArrayHashCodeSet.add(arrayHashCode);
            //  HashCode 가 겹친 이력이 있는 Array를 적재 하는게 (메모리 && 시간) 이득이였음 -> 주석처리함
            //  data_ArraySet.add(((Data) data).getArray());
        }
        //  상위 클래스 offer 호출
        return super.offer(data);
    }
}
