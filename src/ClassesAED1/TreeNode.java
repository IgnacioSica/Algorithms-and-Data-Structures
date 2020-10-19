package ClassesAED1;

//import CaseStudy2.Product;
import InterfacesAED1.iTreeNode;
import java.util.Queue;

public class TreeNode<T> extends Node<T> implements iTreeNode<T> {

    protected iTreeNode<T> left;
    protected iTreeNode<T> right;
    private int height;

    public TreeNode(Comparable Label, T Data) {
        super(Label, Data);
        this.height = 1;
    }

    @Override
    public iTreeNode<T> getLeft() {
        return this.left;
    }

    @Override
    public iTreeNode<T> getRight() {
        return this.right;
    }

    @Override
    public void setLeft(iTreeNode<T> Left) {
        this.left = Left;
    }

    @Override
    public void setRight(iTreeNode<T> Right) {
        this.right = Right;
    }

    @Override
    public int getAVLHeight() {
        return this.height;
    }

    @Override
    public void setAVLHeight(int newHeight) {
        this.height = newHeight;
    }

    @Override
    public boolean insert(iTreeNode<T> node) {
        if (node.getLabel().equals(this.getLabel())) {
            return false;
        }

        if (node.getLabel().compareTo(this.getLabel()) < 0) {
            if (getLeft() != null) {
                return getLeft().insert(node);
            }
            setLeft(node);
        } else {
            if (getRight() != null) {
                return getRight().insert(node);
            }
            setRight(node);
        }

        return true;
    }

    @Override
    public iTreeNode<T> search(Comparable label) {
        if (label.equals(getLabel())) {
            return this;
        }

        if (label.compareTo(getLabel()) < 0) {
            if (getLeft() != null) {
                return getLeft().search(label);
            }
        } else {
            if (getRight() != null) {
                return getRight().search(label);
            }
        }

        return null;
    }

    @Override
    public iTreeNode<T> remove(Comparable Label) {
        if (Label.compareTo(this.getLabel()) < 0) {
            if (this.left != null) {
                this.left = this.left.remove(Label);
            }
            return this;
        }
        if (Label.compareTo(this.getLabel()) > 0) {
            if (this.right != null) {
                this.right = this.right.remove(Label);
            }
            return this;
        }
        return this.removeNode();
    }

    private iTreeNode<T> removeNode() {
        if (this.left == null) {
            return this.right;
        }
        if (this.right == null) {
            return this.left;
        }

        iTreeNode<T> father = this;
        iTreeNode<T> son = getLeft();

        while (son.getRight() != null) {
            father = son;
            son = son.getRight();
        }

        if (!father.getLabel().equals(this.getLabel())) {
            father.setRight(son.getLeft());
            son.setLeft(this.getLeft());
        }

        son.setRight(this.getRight());
        return son;
    }

    @Override
    public int getSize() {
        if (this.left == null && this.right == null) {
            return 1;
        }

        int size = 1;

        if (this.left != null) {
            size += this.left.getSize();
        }
        if (this.right != null) {
            size += this.right.getSize();
        }

        return size;
    }

    @Override
    public int getHeight() {
        if (this.right == null && this.left == null) {
            return 1;
        }

        int Height = 1;

        if (this.left != null) {
            Height = Math.max(Height, this.left.getHeight());
        }

        if (this.right != null) {
            Height = Math.max(Height, this.right.getHeight());
        }

        Height++;

        return Height;
    }

    @Override
    public int getCost(int[] pi, int[] qi, int[] indices, int level) {
        int cost = 0;

        if (this.left != null) {
            cost += this.left.getCost(pi, qi, indices, level + 1);
        } else {
            cost += qi[indices[1]] * (level + 1);
            indices[1]++;
        }

        cost += pi[indices[0] + 1] * level;
        indices[0]++;

        if (this.right != null) {
            cost += this.right.getCost(pi, qi, indices, level + 1);
        } else {
            cost += qi[indices[1]] * (level + 1);
            indices[1]++;
        }

        return cost;
    }

    @Override
    public String print() {
        String text = "";

        if (getLeft() != null) {
            text += getLeft().print();
        }

        text += this.getLabel() + ", " + this.getData().toString() + "\n";

        if (getRight() != null) {
            text += getRight().print();
        }

        return text;
    }

    @Override
    public String preOrder() {
        String text = this.getLabel() + ",";

        if (getLeft() != null) {
            text += getLeft().preOrder();
        }

        if (getRight() != null) {
            text += getRight().preOrder();
        }

        return text;
    }

    @Override
    public void inOrden(ClassesAED1.LinkedList<T> list) {

        if (getLeft() != null) {
            getLeft().inOrden(list);
        }

        list.insert(new ListNode<>(this.getLabel(), this.getData()));

        if (getRight() != null) {
            getRight().inOrden(list);
        }
    }

    @Override
    public void BSTtoQ(Queue queue) {
        if (getLeft() != null) {
            getLeft().BSTtoQ(queue);
        }

        queue.add(this.getData());

        if (getRight() != null) {
            getRight().BSTtoQ(queue);
        }
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public iTreeNode<T> insertAVL(iTreeNode<T> node, Comparable key) {
        if (this.getLabel().equals(key)) {
            return this;
        }

        if (node.getLabel().compareTo(this.getLabel()) < 0) {
            if (getLeft() != null) {
                this.setLeft(getLeft().insertAVL(node, key));
            } else {
                this.setLeft(node);
            }
        } else {
            if (getRight() != null) {
                this.setRight(getRight().insertAVL(node, key));
            } else {
                this.setRight(node);
            }
        }

        this.height = max(Height(this.left), Height(this.right)) + 1;

        int balance = this.getBalance();

        //Left Left Case
        if (balance > 1 && this.getLeft() != null && this.getLeft().getLabel().compareTo(key) >= 0) {
            return rightRotate(this);
        }

        //Right Right Case
        if (balance < -1 && this.getRight() != null && this.getRight().getLabel().compareTo(key) <= 0) {
            return leftRotate(this);
        }

        //Left Right Case
        if (balance > 1 && this.getLeft() != null && this.getLeft().getLabel().compareTo(key) <= 0) {
            this.setLeft(leftRotate(this.getLeft()));
            return rightRotate(this);
        }

        //Right Left Case
        if (balance < -1 && this.getRight() != null && this.getRight().getLabel().compareTo(key) >= 0) {
            this.setRight(rightRotate(this.getRight()));
            return leftRotate(this);
        }

        return this;
    }

    private static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    @Override
    public int getBalance() {
        return Height(this.left) - Height(this.right);
    }

    private static int Height(iTreeNode node) {
        return node == null ? 0 : node.getAVLHeight();
    }

    private static iTreeNode rightRotate(iTreeNode nodeY) {
        iTreeNode nodeX = nodeY.getLeft();
        iTreeNode T2 = nodeX.getRight();

        nodeX.setRight(nodeY);
        nodeY.setLeft(T2);

        nodeY.setAVLHeight(max(Height(nodeY.getLeft()), Height(nodeY.getRight())) + 1);
        nodeX.setAVLHeight(max(Height(nodeX.getLeft()), Height(nodeX.getRight())) + 1);

        return nodeX;
    }

    private static iTreeNode leftRotate(iTreeNode nodeX) {
        iTreeNode nodeY = nodeX.getRight();
        iTreeNode T2 = nodeY.getLeft();

        nodeY.setLeft(nodeX);
        nodeX.setRight(T2);

        nodeX.setAVLHeight(max(Height(nodeX.getLeft()), Height(nodeX.getRight())) + 1);
        nodeY.setAVLHeight(max(Height(nodeY.getLeft()), Height(nodeY.getRight())) + 1);

        return nodeY;
    }

    @Override
    public iTreeNode<T> removeAVL(Comparable Label) {
        if (this.getLabel().equals(Label)) {
            return this.removeNode();
        }

        if (Label.compareTo(this.getLabel()) < 0) {
            if (this.left != null) {
                this.left = this.left.removeAVL(Label);
            }
        }
        if (Label.compareTo(this.getLabel()) > 0) {
            if (this.right != null) {
                this.right = this.right.removeAVL(Label);
            }
        }

        this.height = max(Height(this.left), Height(this.right)) + 1;

        int balance = this.getBalance();

        //Left Left Case
        if (balance > 1 && this.getLeft() != null && this.getLeft().getBalance() >= 0) {
            return rightRotate(this);
        }

        //Left Right Case
        if (balance > 1 && this.getLeft() != null && this.getLeft().getBalance() < 0) {
            this.setLeft(leftRotate(this.getLeft()));
            return rightRotate(this);
        }

        //Right Right Case
        if (balance < -1 && this.getRight() != null && this.getRight().getBalance() <= 0) {
            return leftRotate(this);
        }

        //Right Left Case
        if (balance < -1 && this.getRight() != null && this.getRight().getBalance() > 0) {
            this.setRight(rightRotate(this.getRight()));
            return leftRotate(this);
        }

        return this;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /*@Override
    public String printBySubsidiary (String Label){
        String text = "";
        
        if(getLeft() != null)
            text += getLeft().printBySubsidiary(Label);
        
        Product product = (Product) this.getData();
        if(product.containsSubsidiary(Label))
            text += product.getName()+", "+product.getSubsidiaryStock(Label)+"\n";
        
        if(getRight() != null)
            text += getRight().printBySubsidiary(Label);
        
        return text;
    }*/
}
