package ClassesAED1;

import InterfacesAED1.iListNode;

public class ListNode<T> extends Node<T> implements iListNode<T> {
    private iListNode<T> next;
    
    public ListNode(Comparable Label, T Data) {
        super(Label, Data);
    }

    @Override 
    public iListNode<T> getNext()           { return this.next; }
    
    @Override 
    public void setNext(iListNode<T> Next)  { this.next = Next; }
}