package InterfacesAED2;

import java.util.LinkedList;

public interface IArbolTrie {
    void imprimir();
    int buscar(String palabra);
    void insertar(String palabra);
    LinkedList<String> predecir(String prefijo);
}
