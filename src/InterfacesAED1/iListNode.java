package InterfacesAED1;

public interface iListNode<T> extends iNode<T> {

    public iListNode<T> getNext();

    public void setNext(iListNode<T> Next);
}
