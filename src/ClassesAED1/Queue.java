package ClassesAED1;

import InterfacesAED1.iListNode;
import InterfacesAED1.iQueue;

public class Queue<T> extends LinkedList<T> implements iQueue<T>{  
    @Override
    public void add(T element) {
        this.insert(new ListNode<>("", element));
        size++;
    }

    @Override
    public T remove() {
        if(isEmpty())
            return null;
        iListNode<T> node = this.head;
        this.head = this.head.getNext();
        size--;
        return node.getData();
    }

    @Override
    public T element() {
        return isEmpty() ? null :this.head.getData();
    }
}