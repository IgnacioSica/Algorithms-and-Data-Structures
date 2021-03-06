package TGrafo;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class TGrafoDirigido implements IGrafoDirigido {

    private final Map<Comparable, TVertice> vertices; // vertices del grafo.-
    protected TAristas lasAristas = new TAristas();

    public TGrafoDirigido(final Collection<TVertice> vertices, final Collection<TArista> aristas) {
        this.vertices = new HashMap<>();
        for (final TVertice vertice : vertices) {
            insertarVertice(vertice.getEtiqueta());
        }
        for (final TArista arista : aristas) {
            insertarArista(arista);
        }
    }

    public TAristas getLasAristas() {
        return lasAristas;
    }

    /**
     * Metodo encargado de eliminar una arista dada por un origen y destino. En
     * caso de no existir la adyacencia, retorna falso. En caso de que las
     * etiquetas sean invalidas, retorna falso.
     *
     */
    public boolean eliminarArista(final Comparable nomVerticeOrigen, final Comparable nomVerticeDestino) {
        if ((nomVerticeOrigen != null) && (nomVerticeDestino != null)) {
            final TVertice vertOrigen = buscarVertice(nomVerticeOrigen);
            if (vertOrigen != null) {
                lasAristas.remove(lasAristas.buscar(nomVerticeOrigen, nomVerticeDestino));
                return vertOrigen.eliminarAdyacencia(nomVerticeDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de una arista. Las etiquetas
     * pasadas por par�metro deben ser v�lidas.
     *
     * @return True si existe la adyacencia, false en caso contrario
     */
    public boolean existeArista(final Comparable etiquetaOrigen, final Comparable etiquetaDestino) {
        final TVertice vertOrigen = buscarVertice(etiquetaOrigen);
        final TVertice vertDestino = buscarVertice(etiquetaDestino);
        if ((vertOrigen != null) && (vertDestino != null)) {
            return vertOrigen.buscarAdyacencia(vertDestino) != null;
        }
        return false;
    }

    /**
     * Metodo encargado de verificar la existencia de un vertice dentro del
     * grafo.-
     *
     * La etiqueta especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return True si existe el vertice con la etiqueta indicada, false en caso
     * contrario
     */
    public boolean existeVertice(final Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta) != null;
    }

    /**
     * Metodo encargado de verificar buscar un vertice dentro del grafo.-
     *
     * La etiqueta especificada como parametro debe ser valida.
     *
     * @param unaEtiqueta Etiqueta del vertice a buscar.-
     * @return El vertice encontrado. En caso de no existir, retorna nulo.
     */
    public TVertice buscarVertice(final Comparable unaEtiqueta) {
        return getVertices().get(unaEtiqueta);
    }

    /**
     * Metodo encargado de insertar una arista en el grafo (con un cierto
     * costo), dado su vertice origen y destino.- Para que la arista sea valida,
     * se deben cumplir los siguientes casos: 1) Las etiquetas pasadas por
     * parametros son v�lidas.- 2) Los vertices (origen y destino) existen
     * dentro del grafo.- 3) No es posible ingresar una arista ya existente
     * (miso origen y mismo destino, aunque el costo sea diferente).- 4) El
     * costo debe ser mayor que 0.
     *
     * @return True si se pudo insertar la adyacencia, false en caso contrario
     */
    public boolean insertarArista(final TArista arista) {
        if ((arista.getEtiquetaOrigen() != null) && (arista.getEtiquetaDestino() != null)) {
            final TVertice vertOrigen = buscarVertice(arista.getEtiquetaOrigen());
            final TVertice vertDestino = buscarVertice(arista.getEtiquetaDestino());
            if ((vertOrigen != null) && (vertDestino != null)) {
                lasAristas.add(arista);
                return vertOrigen.insertarAdyacencia(arista.getCosto(), vertDestino);
            }
        }
        return false;
    }

    /**
     * Metodo encargado de insertar un vertice en el grafo.
     *
     * No pueden ingresarse vertices con la misma etiqueta. La etiqueta
     * especificada como par�metro debe ser v�lida.
     *
     * @param unaEtiqueta Etiqueta del vertice a ingresar.
     * @return True si se pudo insertar el vertice, false en caso contrario
     */
    public boolean insertarVertice(final Comparable unaEtiqueta) {
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            final TVertice vert = new TVertice(unaEtiqueta);
            getVertices().put(unaEtiqueta, vert);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    @Override

    public boolean insertarVertice(final TVertice vertice) {
        final Comparable unaEtiqueta = vertice.getEtiqueta();
        if ((unaEtiqueta != null) && (!existeVertice(unaEtiqueta))) {
            getVertices().put(unaEtiqueta, vertice);
            return getVertices().containsKey(unaEtiqueta);
        }
        return false;
    }

    public Object[] getEtiquetasOrdenado() {
        final TreeMap<Comparable, TVertice> mapOrdenado = new TreeMap<>(this.getVertices());
        return mapOrdenado.keySet().toArray();
    }

    /**
     * @return the vertices
     */
    public Map<Comparable, TVertice> getVertices() {
        return vertices;
    }

    @Override
    /**
     * Metodo encargado de encontrar el centro del grafo Obtiene la
     * excentricidad de cada vertice y se queda con la menor de todas Retorna la
     * etiqueta del vertice 'centro' de grafo si lo encuentra Retorna -1 si no
     * se ha podido encontrar
     */
    public Comparable centroDelGrafo() {
        Comparable[][] costos = floyd();
        Set<Comparable> etiquetas = vertices.keySet();

        int indice = 0;
        Double costoMaxGrafo = Double.MAX_VALUE;
        for (int j = 0; j < etiquetas.size(); j++) {
            Double costoMax = 0d;
            for (int i = 0; i < etiquetas.size(); i++) {
                if ((Double) costos[i][j] > costoMax) {
                    costoMax = (Double) costos[i][j];
                }
            }
            if (costoMax < costoMaxGrafo) {
                indice = j;
                costoMaxGrafo = costoMax;
            }
        }

        Comparable etiquetaGrafo = "";
        for (Comparable etiqueta : etiquetas) {
            if (indice == 0) {
                etiquetaGrafo = etiqueta;
                break;
            }
            indice--;
        }
        return etiquetaGrafo;
    }

    @Override
    public Comparable[][] floyd() {
        int cantidad = vertices.size();
        Double[][] A = UtilGrafos.obtenerMatrizCostos(vertices);
        for (int k = 0; k < cantidad; k++) {
            for (int i = 0; i < cantidad; i++) {
                if (k != i) {
                    for (int j = 0; j < cantidad; j++) {
                        if (A[i][j] > A[i][k] + A[k][j]) {
                            A[i][j] = A[i][k] + A[k][j];
                        }
                    }
                }
            }
        }
        return A;
    }

    /**
     * Método encargado de obtener la excentricidad dado una etiqueta de vertice
     * Retorna la excentricidad del vertice pasado por parametro si este se
     * encuentra en el grafo. Retorna -1 si no se puede conseguir ese vertice o
     * no hay vertices adyacentes
     *
     * @param etiquetaVertice
     * @return
     */
    @Override
    public Comparable obtenerExcentricidad(Comparable etiquetaVertice) {
        Comparable[][] costos = floyd();
        Set<Comparable> etiquetas = vertices.keySet();
        int indice = 0;
        for (Comparable vertice : etiquetas) {
            if (etiquetaVertice.compareTo(vertice) == 0) {
                break;
            }
            indice++;
        }

        Double costoMax = 0d;
        for (int i = 0; i < costos[0].length; i++) {
            if ((Double) costos[i][indice] > costoMax) {
                costoMax = (Double) costos[i][indice];
            }
        }

        return costoMax == Double.MAX_VALUE ? -1 : costoMax;
    }

    @Override
    public boolean[][] warshall() {
        int cantidad = vertices.size();
        Double[][] C = UtilGrafos.obtenerMatrizCostos(vertices);
        boolean[][] A = new boolean[cantidad][cantidad];
        for (int i = 0; i < cantidad; i++) {
            for (int j = 0; j < cantidad; j++) {
                if (i != j) {
                    A[i][j] = C[i][j] < Double.MAX_VALUE;
                }
            }
        }
        for (int k = 0; k < cantidad; k++) {
            for (int i = 0; i < cantidad; i++) {
                if (k != i) {
                    for (int j = 0; j < cantidad; j++) {
                        if (i != j && !A[i][j]) {
                            A[i][j] = A[i][k] && A[k][j];
                        }
                    }
                }
            }
        }
        return A;
    }

    @Override
    public void eliminarVertice(Comparable nombreVertice) {
        vertices.remove(nombreVertice);
        lasAristas.forEach((arista) -> {
            if (arista.etiquetaDestino.equals(nombreVertice)) {
                lasAristas.remove(arista);
            }
            if (arista.etiquetaOrigen.equals(nombreVertice)) {
                lasAristas.remove(arista);
            }
        });

        vertices.values().forEach((vertice) -> {
            LinkedList<TAdyacencia> adyacencias = vertice.getAdyacentes();
            for (TAdyacencia ad : adyacencias) {
                if (ad.getDestino().getEtiqueta().equals(nombreVertice)) {
                    vertice.getAdyacentes().remove(ad);
                }
            }
        });
    }

    @Override
    public Collection<TVertice> bpf() {
        Collection<TVertice> result = new LinkedList<>();
        desvisitarVertices();

        this.vertices.entrySet().forEach((v) -> {
            if (!v.getValue().getVisitado()) {
                v.getValue().bpf(result);
            }
        });

        return result;
    }

    @Override
    public Collection<TVertice> bpf(Comparable etiquetaOrigen) {
        Collection<TVertice> result = new LinkedList<>();
        desvisitarVertices();

        TVertice vOrigen = this.buscarVertice(etiquetaOrigen);
        if (vOrigen != null) {
            vOrigen.bpf(result);
        }

        this.vertices.entrySet().forEach((v) -> {
            if (!v.getValue().getVisitado()) {
                v.getValue().bpf(result);
            }
        });

        return result;
    }

    @Override
    public Collection<TVertice> bpf(TVertice vertice) {
        Collection<TVertice> result = new LinkedList<>();
        desvisitarVertices();

        if (vertice != null) {
            vertice.bpf(result);
        }

        this.vertices.entrySet().forEach((v) -> {
            if (!v.getValue().getVisitado()) {
                v.getValue().bpf(result);
            }
        });

        return result;
    }

    @Override
    public void desvisitarVertices() {
        this.vertices.entrySet().forEach((v) -> {
            v.getValue().setVisitado(false);
            v.getValue().numBajo = 0;
            v.getValue().numBp = 1;
        });
    }

    public void camino(TVertice origen, TVertice destino, TCaminos caminos, TCamino elCamino) {
        origen.setVisitado(true);
        LinkedList<TAdyacencia> adyacencias = origen.getAdyacentes();
        for (TAdyacencia adyacente : adyacencias) {
            elCamino.agregarAdyacencia(adyacente);
            if (adyacente.getDestino().equals(destino)) {
                caminos.agregarCaminos(elCamino.copiar()); //El camino lo copia porque luego voy a modificarlo.
            } else {
                if (!adyacente.getDestino().getVisitado()) {
                    camino(adyacente.getDestino(), destino, caminos, elCamino);
                }
            }
            elCamino.eliminarAdyacencia(adyacente);
        }
        origen.setVisitado(false);  // Lo desvisito porque desde A capaz puedo acceder desde otro nodo a B.
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TVertice verticeOrigen = buscarVertice(etiquetaOrigen);
        if (verticeOrigen != null) {
            return verticeOrigen.todosLosCaminos(etiquetaDestino, new TCamino(verticeOrigen), new TCaminos());
        }
        return null; // El vertice origen no existe.
    }

    @Override
    public boolean tieneCiclo() {
        desvisitarVertices();

        for (TVertice v : this.vertices.values()) {
            if (!v.getVisitado()) {
                TCamino camino = new TCamino(v);
                if (v.tieneCiclo(camino)) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public Collection<TVertice> bea(Comparable etiquetaOrigen) {
        LinkedList<TVertice> resultado = new LinkedList<>();
        TVertice origen = buscarVertice(etiquetaOrigen);

        if (origen != null) {
            origen.bea(resultado);
        }

        return resultado;
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void ordenacionTopologica(Comparable etiquetaDestino) {
        desvisitarVertices();
        TAristas aristas = new TAristas();
        this.lasAristas.forEach((arista) -> {
            aristas.add(arista.aristaInversa());
        });
        TGrafoDirigido grafo = new TGrafoDirigido(getVertices().values(), aristas);
        TVertice v = grafo.buscarVertice(etiquetaDestino);
        if (v != null) {
            v.ordenTopologico();
        }
    }

    public Collection<TVertice> caminoMasCorto(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        LinkedList<TVertice> camino = new LinkedList<>();
        TVertice origen = buscarVertice(etiquetaOrigen);
        if (origen != null) {
            desvisitarVertices();
            origen.caminoMasCorto(etiquetaDestino);
        }

        TVertice verticeActual = buscarVertice(etiquetaDestino);
        while (verticeActual != null) {
            camino.addFirst(verticeActual);
            verticeActual = verticeActual.getPredecesor();
        }

        return camino;
    }

    public TCamino caminoMenosCostoso(Comparable etiquetaOrigen, Comparable etiquetaDestino) {
        TCamino caminoActual, mejorCamino = null;

        TVertice origen = buscarVertice(etiquetaOrigen);

        if (etiquetaOrigen.equals(etiquetaDestino)) {
            return new TCamino(origen);
        }

        if (origen != null) {
            desvisitarVertices();
            caminoActual = new TCamino(origen);
            mejorCamino = origen.caminoMenosCostoso(etiquetaDestino, caminoActual, mejorCamino);
        }

        return mejorCamino;
    }

    public void clasificarArcos(TVertice verticeOrigen, LinkedList<TArista> arcosArbol, LinkedList<TArista> arcosRetroceso, LinkedList<TArista> arcosAvance, LinkedList<TArista> arcosCruzados) {
        desvisitarVertices();
        if (verticeOrigen != null) {
            verticeOrigen.bpfConNumero(new int[1]);
            desvisitarVertices();
            verticeOrigen.clasificarArcos(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
        }
    }
}
