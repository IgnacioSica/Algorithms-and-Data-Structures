package InterfacesAED1;

public interface iStack<T> {
    public void push(T element);
    public T pop();
    public T peek();
    
    public boolean isEmpty();
    //public boolean contains(Comparable Label);
    //public void empty();
}
