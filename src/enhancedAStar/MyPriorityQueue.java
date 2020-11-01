package enhancedAStar;

import queue.Data;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MyPriorityQueue<E> extends PriorityQueue<E> {
    private Set<Integer> set;

    public MyPriorityQueue(Comparator comparator) {
        super(comparator);
        set = new HashSet<>();
    }

    @Override
    public boolean offer(E data) {
        if (data instanceof Data) {
            int arrayHashCode = ((Data) data).getArray().hashCode();

            if (set.contains(arrayHashCode)) {
                return false;
            }

            set.add(arrayHashCode);
        }
        return super.offer(data);
    }
}
