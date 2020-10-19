package ClassesAED1;

import InterfacesAED1.iBSTree;
import InterfacesAED1.iTreeNode;
import java.util.LinkedList;
import java.util.Queue;

public class BSTree<T> implements iBSTree<T> {

    protected iTreeNode<T> root;

    @Override
    public iTreeNode<T> getRoot() {
        return root;
    }

    @Override
    public boolean isEmpty() {
        return this.root == null;
    }

    @Override
    public void empty() {
        this.root = null;
    }

    @Override
    public int getSize() {
        return isEmpty() ? 0 : this.root.getSize();
    }

    @Override
    public int getHeight() {
        return isEmpty() ? -1 : this.root.getHeight();
    }

    @Override
    public String print() {
        return isEmpty() ? "" : this.root.print();
    }

    @Override
    public iTreeNode<T> search(Comparable Label) {
        return isEmpty() ? null : this.root.search(Label);
    }

    @Override
    public boolean insert(iTreeNode<T> TreeNode) {
        if (!isEmpty()) {
            return this.root.insert(TreeNode);
        }
        this.root = TreeNode;
        return true;
    }

    @Override
    public void remove(Comparable Label) {
        if (this.root != null) {
            this.root = this.root.remove(Label);
        }
    }

    @Override
    public Queue BSTtoQ() {
        if (isEmpty()) {
            return null;
        }
        Queue qNodes = new LinkedList();
        this.root.BSTtoQ(qNodes);
        return qNodes;
    }

    @Override
    public ClassesAED1.LinkedList inOrden() {
        if (isEmpty()) {
            return null;
        }

        ClassesAED1.LinkedList<T> linkedList = new ClassesAED1.LinkedList<>();
        this.root.inOrden(linkedList);

        return linkedList;
    }
}
