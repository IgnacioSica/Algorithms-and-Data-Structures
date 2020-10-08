package HashMap;

public class NodoHash<T> {

    public T dato;

    private final int hashCode;

    public NodoHash(T dato) {
        this.dato = dato;
        hashCode = 0;
    }

    @Override
    public int hashCode() {
        return dato.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        NodoHash nodo = (NodoHash) obj;
        return dato.equals(nodo.dato);
    }
}
