package InterfacesAED1;

import java.util.Queue;

public interface iBSTree<T>{
    public iTreeNode<T> getRoot();
    
    public boolean isEmpty();
    public void empty();
    
    public boolean insert(iTreeNode<T> TreeNode);
    public void remove(Comparable Label);
    public iTreeNode<T> search(Comparable Label);
    
    public int getSize();
    public int getHeight();
    
    public ClassesAED1.LinkedList inOrden();
    public String print();
    public Queue BSTtoQ();
}
