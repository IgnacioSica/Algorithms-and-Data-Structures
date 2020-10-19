package ClassesAED1;

public class Pair<K, T> {

    private final K element0;
    private final T element1;

    public Pair(K Element0, T Element1) {
        this.element0 = Element0;
        this.element1 = Element1;
    }

    public K getElement0() {
        return element0;
    }

    public T getElement1() {
        return element1;
    }
}
