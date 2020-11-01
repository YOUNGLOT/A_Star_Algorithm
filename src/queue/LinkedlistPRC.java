package queue;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class LinkedlistPRC {
    private Object head;
    private Object tail;
    private int size = 0;

    private class Object{
        private Object data;
        private Object next;

        public Object(Object input){
            this.data = input;
            this.next = null;
        }

        public String toString(){
            return String.valueOf(this.data);
        }

        public void addFirst(Object input){
            Object newObject = new Object(input);
            newObject.next = head;
            size++;
            if(head.next == null){
                tail = head;
            }
        }

        public void add(Object input){
            Object newObject = new Object(input);
            newObject.next = head;
            List<String> list = new ArrayList<>();
        }
    }

}
