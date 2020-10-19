package ClassesAED1;

import InterfacesAED1.iLinkedList;
import InterfacesAED1.iListNode;

public class LinkedList<T> implements iLinkedList<T> {

    protected iListNode<T> head;
    protected int size;

    @Override
    public iListNode<T> getHead() {
        return this.head;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void empty() {
        this.head = null;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean contains(Comparable Label) {
        return this.search(Label) != null;
    }

    @Override
    public boolean insert(iListNode<T> node) {
        if (isEmpty()) {
            this.head = node;
            this.size++;
            return true;
        }

        if (this.contains(node.getLabel())) {
            return false;
        }

        iListNode<T> aux = this.getHead();
        while (aux.getNext() != null) {
            aux = aux.getNext();
        }

        aux.setNext(node);
        this.size++;

        return true;
    }

    @Override
    public boolean remove(Comparable Label) {
        if (isEmpty()) {
            return false;
        }

        if (this.head.getLabel().equals(Label)) {
            this.head = this.head.getNext();
            size--;
            return true;
        }

        iListNode<T> aux = this.getHead();
        while (aux.getNext() != null) {
            if (aux.getNext().getLabel().equals(Label)) {
                aux.setNext(aux.getNext().getNext());
                size--;
                return true;
            }
            aux = aux.getNext();
        }

        return false;
    }

    @Override
    public iListNode<T> search(Comparable Label) {
        if (isEmpty()) {
            return null;
        }

        iListNode<T> aux = this.head;
        while (aux != null) {
            if (aux.getLabel().equals(Label)) {
                return aux;
            }
            aux = aux.getNext();
        }

        return null;
    }

    @Override
    public String printLabels() {
        if (isEmpty()) {
            return "";
        }

        String labels = "";
        iListNode<T> node = this.head;
        while (node != null) {
            labels += node.getLabel() + ", ";
            node = node.getNext();
        }

        return labels.substring(0, labels.length() - 2);
    }
}
