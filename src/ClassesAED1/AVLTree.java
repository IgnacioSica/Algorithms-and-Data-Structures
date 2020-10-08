package ClassesAED1;

import InterfacesAED1.iAVLTree;
import InterfacesAED1.iTreeNode;

public class AVLTree<T> extends BSTree<T> implements iAVLTree<T>{
    
    @Override
    public boolean insert(iTreeNode<T> TreeNode) {
        if(!isEmpty()){
            this.root = this.root.insertAVL(TreeNode, TreeNode.getLabel());
        } else {
            this.root = TreeNode;
        }
        return true; 
    } 
    
    @Override
    public void remove(Comparable Label) {
        if(this.root != null)
            this.root = this.root.removeAVL(Label);     
    }
    
    @Override
    public T getSmallest() {
        if(isEmpty())
            return null;
        iTreeNode<T> aux = this.root;
        while(aux.getLeft()!= null)
            aux = aux.getLeft();
        return aux.getData();
    }

    @Override
    public T getLargest() {
        if(isEmpty())
            return null;
        iTreeNode<T> aux = this.root;
        while(aux.getRight()!= null)
            aux = aux.getRight();
        return aux.getData();    
    }
    
    @Override
    public String printPO() { return isEmpty() ? "" : this.root.preOrder(); }
    
    //@Override
    //public String printBySubsidiary(String Label){ return isEmpty() ? "" : this.root.printBySubsidiary(Label); }
}
