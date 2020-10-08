package ClassesAED1;

import InterfacesAED1.iListNode;

public class OrderedList<T> extends LinkedList<T>{
    @Override
    public boolean insert(iListNode<T> node) {
        if(isEmpty()){
            this.head = node;
            this.size++;
            return true;
        }
        
        if(this.contains(node.getLabel())){
            System.out.println("Este nodo ya está incluido en la lista");
            return false;
        }
        
        if(node.getLabel().compareTo(this.head.getLabel()) < 0){
            node.setNext(this.head);
            this.head = node;
            this.size++;
            return true;
        }
        
        iListNode<T> aux = this.getHead();
        while(aux.getNext() != null && node.getLabel().compareTo(aux.getNext().getLabel()) > 0)
            aux = aux.getNext();
        
        node.setNext(aux.getNext());
        aux.setNext(node);
        this.size++;
        return true;
    }
}