package TGrafo;

import java.util.Collection;
import java.util.LinkedList;

public class TGrafoNoDirigido extends TGrafoDirigido implements IGrafoNoDirigido, IGrafoKevinBacon {

    protected TAristas lasAristas = new TAristas();

    public TGrafoNoDirigido(Collection<TVertice> vertices, Collection<TArista> aristas) {
        super(vertices, aristas);
        lasAristas.insertarAmbosSentidos(aristas);
    }

    @Override
    public boolean insertarArista(TArista arista) {
        boolean tempbool = false;
        TArista arInv = new TArista(arista.getEtiquetaDestino(), arista.getEtiquetaOrigen(), arista.getCosto());
        tempbool = (super.insertarArista(arista) && super.insertarArista(arInv));
        return tempbool;
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    @Override
    public TGrafoNoDirigido Prim() {
        Collection<Comparable> universo = new LinkedList<>();
        Collection<Comparable> vertices = getVertices().keySet();
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(this.getVertices().values(), new TAristas());
        universo.add(getLasAristas().getFirst().getEtiquetaOrigen());
        while (!vertices.isEmpty()) {
            TArista arista = lasAristas.buscarMin(universo, vertices);
            universo.add(arista.getEtiquetaDestino());
            grafo.insertarArista(arista);
            grafo.lasAristas.add(arista);
            vertices.removeAll(universo);
        }
        return grafo;
    }

    @Override
    public TGrafoNoDirigido Kruskal() {
        Collection<Comparable> vertices = getVertices().keySet();
        TGrafoNoDirigido grafo = new TGrafoNoDirigido(getVertices().values(), new TAristas());
        TAristas aristas = (TAristas) this.lasAristas.clone();
        while (grafo.lasAristas.size() < getVertices().size() - 1) {
            TArista aristaMinima = aristas.buscarMin(vertices, vertices);
            aristas.remove(aristaMinima);
            if (grafo.todosLosCaminos(aristaMinima.etiquetaOrigen, aristaMinima.etiquetaDestino).getCaminos().isEmpty()) {
                grafo.insertarArista(aristaMinima);
                grafo.lasAristas.add(aristaMinima);
            }
        }
        return grafo;
    }

    /*@Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        LinkedList<TVertice> resultado = new LinkedList<>();
        TVertice origen = buscarVertice(etiquetaOrigen);
        
        if (origen != null)
            origen.bea(resultado);
       
        return resultado;
    }*/

    @Override
    public int numBacon(Comparable actor) {
        desvisitarVertices();
        TVertice verticeBacon = buscarVertice("Kevin_Bacon");
        if (verticeBacon != null) {
            return verticeBacon.beaBacon(actor);
        }
        return -1;
    }

    LinkedList<TVertice> puntosArticulacion(Comparable etOrigen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
