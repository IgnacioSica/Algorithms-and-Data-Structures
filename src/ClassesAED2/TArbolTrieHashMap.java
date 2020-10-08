package ClassesAED2;

import java.util.LinkedList;

public class TArbolTrieHashMap{
    private TNodoTrieHashMap raiz;

    public int insertar(String palabra, int pagina) {
        if (raiz == null) {
            raiz = new TNodoTrieHashMap();
        }
        return raiz.insertar(palabra, pagina);
    }

    public int buscar(String palabra) {
        return raiz == null ? 0 : raiz.buscar(palabra);
    }

    public TNodoTrieHashMap buscarNodo(String palabra) {
        return raiz == null ? null : raiz.buscarNodo(palabra);
    }

    public LinkedList<String> predecir(String palabra) {
        return raiz == null ? null : raiz.predecir(palabra);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }
}
