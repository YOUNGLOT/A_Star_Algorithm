package queue;

import java.util.*;

public class Queue1{
    PriorityQueue<Data> priorityQueue = new PriorityQueue<>();


    public static void main(String[] args) {
        Queue1 queue = new Queue1();
        queue.solve();
    }

    private void solve() {
        int[][] goalArray = {{1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        priorityQueue.offer(new Data("1", goalArray));
        priorityQueue.offer(new Data("3", goalArray));
        priorityQueue.offer(new Data("2", goalArray));
        priorityQueue.offer(new Data("8", goalArray));
        priorityQueue.offer(new Data("10", goalArray));
        priorityQueue.offer(new Data("6", goalArray));
        priorityQueue.offer(new Data("60", goalArray));

        while(priorityQueue.size() != 0){
            var x = priorityQueue.peek();
            System.out.println(x.toString());
            priorityQueue.remove(x);
        }
    }




}