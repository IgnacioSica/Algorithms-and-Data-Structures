package InterfacesAED1;

import java.util.Queue;

public interface iTreeNode<T> extends iNode<T>{
    public iTreeNode<T> getLeft();
    public iTreeNode<T> getRight();
    public void setLeft(iTreeNode<T> Left);
    public void setRight(iTreeNode<T> Right);
    
    public boolean insert(iTreeNode<T> node);
    public iTreeNode<T> remove(Comparable label);
    public iTreeNode<T> search(Comparable label);
    
    public int getSize();
    public int getHeight();
    
    public iTreeNode<T> insertAVL(iTreeNode<T> node, Comparable key);
    public iTreeNode<T> removeAVL(Comparable key);
    public void setAVLHeight(int newHeight);
    public int getAVLHeight();
    
    public int getCost(int[] pi, int[] qi, int[] indices, int level);
    public int getBalance();
    
    public String print();
    public String preOrder();
    public void inOrden(ClassesAED1.LinkedList<T> list);
    public void BSTtoQ(Queue que);
    
    //public String printBySubsidiary (String Label);
}