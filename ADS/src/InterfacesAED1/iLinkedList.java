package InterfacesAED1;

public interface iLinkedList<T> {
    public iListNode<T> getHead();
   
    public boolean insert(iListNode<T> node);
    public boolean remove(Comparable Label);
    public iListNode<T> search(Comparable Label);
    
    public boolean isEmpty();
    public void empty();
    public int getSize();
    
    public String printLabels();
    
    @Override
    public String toString();
    public boolean contains(Comparable Label);
}
