package ClassesAED2;

import java.util.LinkedList;

public class TNodoTrie {
    
    private static final int CANT_CHR_ABECEDARIO = 26;
    private final TNodoTrie[] hijos;
    private boolean esPalabra;

    public TNodoTrie() {
        hijos = new TNodoTrie[CANT_CHR_ABECEDARIO];
        esPalabra = false;
    }

    public void insertar(String unaPalabra) {
        TNodoTrie nodo = this;
        for (int c = 0; c < unaPalabra.length(); c++) {
            int indice = unaPalabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null) 
                nodo.hijos[indice] = new TNodoTrie();
            nodo = nodo.hijos[indice];
        }
        nodo.esPalabra = true;
    }

    private void imprimir(String s, TNodoTrie nodo){
        if (nodo != null) {
            if (nodo.esPalabra) 
                System.out.println(s);
            for (int c = 0; c < CANT_CHR_ABECEDARIO; c++) {
                if (nodo.hijos[c] != null) 
                    imprimir(s + (char) (c + 'a'), nodo.hijos[c]);     
            }
        }
    }

    public void imprimir() {
        imprimir("", this);
    }

    public int buscar(String palabra){
        int comparaciones = 0;
        TNodoTrie nodo = this;
        for (int c = 0; c < palabra.length(); c++) {
            comparaciones++;
            int indice = palabra.charAt(c) - 'a';
            if (nodo.hijos[indice] == null)
                return 0;
            nodo = nodo.hijos[indice];
        }
        return comparaciones;
    }
    
    public void predecir(String prefijo, LinkedList<String> palabras) {
        TNodoTrie nodoActual = this;
        for(int c = 0; c <= prefijo.length(); c++){
            int indice = prefijo.charAt(c) - 'a';
            if(nodoActual.hijos[indice] == null)
                return;
            nodoActual = nodoActual.hijos[indice];
        }
        predecir("", prefijo, palabras, nodoActual);
    }
    
    private void predecir(String s, String prefijo, LinkedList<String> palabras, TNodoTrie nodo) {
        for(int i = 0; i <= 26; i++){
            if (nodo.hijos[i] == null)
                continue;
            if(nodo.hijos[i].esPalabra)
                palabras.add(prefijo + s + (char)('a' + i));
            predecir(s + (char)('a' + i), prefijo, palabras, nodo.hijos[i]);
        }
    }
}