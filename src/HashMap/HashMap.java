package HashMap;

public class HashMap<T> {

    private final NodoHash<T>[] tabla;

    public HashMap(int cantElem, int maxPercent) {
        tabla = new NodoHash[encontrarProximoPrimo((int) Math.ceil((float) cantElem * 100 / maxPercent))];
    }

    public int getTamaño() {
        return tabla.length;
    }

    public int insertar(T valor) {
        int hash = hash(valor);
        int comparaciones = 1;
        while (tabla[hash] != null) {
            comparaciones++;
            hash = (hash + 1) % tabla.length;
        }
        tabla[hash] = new NodoHash(valor);
        return comparaciones;
    }

    private int hash(Object obj) {
        int hash = obj.hashCode() % tabla.length;
        return hash < 0 ? hash + tabla.length : hash;
    }

    public int buscar(T valor) {
        int hash = hash(valor);
        int comparaciones = 1;
        while (tabla[hash] != null) {
            comparaciones++;
            if (tabla[hash].dato.equals(valor)) {
                return comparaciones;
            }
            hash = (hash + 1) % tabla.length;
        }
        return -comparaciones;
    }

    private boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private int encontrarProximoPrimo(int unEntero) {
        int k = unEntero;
        if (k % 2 == 0) {
            k++;
        }
        for (; !isPrime(k); k += 2) {
        }
        return k;
    }
}
