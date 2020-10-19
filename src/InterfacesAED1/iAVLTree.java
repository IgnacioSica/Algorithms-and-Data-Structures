package InterfacesAED1;

public interface iAVLTree<T> extends iBSTree<T> {

    public T getSmallest();

    public T getLargest();

    public String printPO();

    //public String printBySubsidiary(String Label);
}
