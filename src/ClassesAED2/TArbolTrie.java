package ClassesAED2;

import java.util.LinkedList;

public class TArbolTrie {

    private TNodoTrie raiz;

    public void insertar(String palabra) {
        if (raiz == null) {
            raiz = new TNodoTrie();
        }
        raiz.insertar(palabra);
    }

    public void imprimir() {
        if (raiz != null) {
            raiz.imprimir();
        }
    }

    /*public List<String> imprimir() {
        if (raiz != null) 
            raiz.imprimir();
    }*/
    public int buscar(String palabra) {
        if (raiz != null) {
            return raiz.buscar(palabra);
        }
        return 0;
    }

    public LinkedList<String> predecir(String prefijo) {
        LinkedList<String> resultado = new LinkedList<>();
        if (this.raiz != null) {
            this.raiz.predecir(prefijo, resultado);
        }
        return resultado;
    }
}
