package ClassesAED1;

import InterfacesAED1.iNode;

public class Node<T> implements iNode<T> {

    private final Comparable label;
    private T data;

    public Node(Comparable Label, T Data) {
        this.label = Label;
        data = Data;
    }

    @Override
    public Comparable getLabel() {
        return label;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T Data) {
        this.data = Data;
    }
}
