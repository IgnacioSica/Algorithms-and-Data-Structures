package ClassesAED2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class TNodoTrieHashMap {

    private boolean esPalabra;
    private final HashMap<Character, TNodoTrieHashMap> hijos;
    private final static int DEFAULTSIZE = 26;
    private final LinkedList<Integer> paginas;

    public TNodoTrieHashMap() {
        esPalabra = false;
        hijos = new HashMap<>(DEFAULTSIZE * 10 / 9);
        paginas = new LinkedList<>();
    }

    public int insertar(String palabra, int posicion) {
        TNodoTrieHashMap nodoCaracter = this;
        int comparaciones = 0;
        for (int c = 0; c < palabra.length(); c++) {
            TNodoTrieHashMap hijo = nodoCaracter.hijos.get(palabra.charAt(c));
            comparaciones++;
            if (hijo == null) {
                hijo = new TNodoTrieHashMap();
                nodoCaracter.hijos.put(palabra.charAt(c), hijo);
            }
            nodoCaracter = hijo;
        }
        nodoCaracter.esPalabra = true;
        nodoCaracter.paginas.add(posicion);
        return comparaciones;
    }

    public int buscar(String palabra) {
        TNodoTrieHashMap nodoCaracter = this;
        int comparaciones = 0;
        for (int c = 0; c < palabra.length(); c++) {
            TNodoTrieHashMap hijo = nodoCaracter.hijos.get(palabra.charAt(c));
            comparaciones++;
            if (hijo == null) {
                return -comparaciones;
            }
            nodoCaracter = hijo;
        }
        return nodoCaracter.esPalabra ? comparaciones : -comparaciones;
    }

    public TNodoTrieHashMap buscarNodo(String palabra) {
        TNodoTrieHashMap nodoCaracter = this;
        for (int c = 0; c < palabra.length(); c++) {
            TNodoTrieHashMap hijo = nodoCaracter.hijos.get(palabra.charAt(c));
            if (hijo == null) {
                return null;
            }
            nodoCaracter = hijo;
        }
        return nodoCaracter;
    }

    public LinkedList<String> predecir(String prefijo) {
        TNodoTrieHashMap nodo = buscarNodo(prefijo);
        LinkedList<String> lista = new LinkedList<>();
        if (nodo != null) {
            predecir("", prefijo, nodo, lista);
        }
        return lista;
    }

    private void predecir(String s, String prefijo, TNodoTrieHashMap nodo, LinkedList<String> lista) {
        if (nodo.esPalabra) {
            lista.add(prefijo + s + ": " + nodo.paginas.toString());
        }
        nodo.hijos.entrySet().forEach((entry) -> {
            predecir(s + entry.getKey(), prefijo, entry.getValue(), lista);
        });
    }

    public void imprimir() {
        imprimir("", this);
    }

    private void imprimir(String s, TNodoTrieHashMap nodo) {
        if (nodo.esPalabra) {
            System.out.println(s);
        }
        for (Map.Entry<Character, TNodoTrieHashMap> entry : nodo.hijos.entrySet()) {
            imprimir(s + entry.getKey(), entry.getValue());
        }
    }
}
