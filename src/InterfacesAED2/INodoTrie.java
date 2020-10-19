package InterfacesAED2;

import java.util.LinkedList;

public interface INodoTrie {

    int buscar(String s);

    void imprimir();

    void insertar(String unaPalabra);

    public void predecir(String prefijo, LinkedList<String> palabras);
}
