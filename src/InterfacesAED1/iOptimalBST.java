package InterfacesAED1;

public interface iOptimalBST<T> {

    public iTreeNode<T> getRoot();

    public boolean isEmpty();

    public void empty();

    public iTreeNode<T> search(Comparable Label);

    public int getSize();

    public int getHeight();

    public int getCost(int[] pi, int[] qi);

    public String print();

    public boolean initialize(Comparable[] Keys, int[] pi, int[] qi, int n);
}
