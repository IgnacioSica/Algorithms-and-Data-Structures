package ClassesAED1;

import InterfacesAED1.iListNode;
import InterfacesAED1.iStack;

public class Stack<T> extends LinkedList<T> implements iStack<T> {

    @Override
    public void push(T element) {
        iListNode<T> node = new ListNode<>("", element);
        node.setNext(this.head);
        this.head = node;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            return null;
        }
        iListNode<T> node = this.getHead();
        this.head = this.head.getNext();
        return node.getData();
    }

    @Override
    public T peek() {
        return isEmpty() ? null : this.getHead().getData();
    }
}
