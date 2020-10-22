package TGrafo;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class TVertice<T> implements IVertice, IVerticeKevinBacon {

    private final Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;
    private int bacon = Integer.MAX_VALUE;

    public int numBp;
    public int numBajo;
    private int cantidadDescendientes;

    private TVertice predecesor;

    public Comparable getEtiqueta() {
        return etiqueta;
    }

    @Override
    public LinkedList<TAdyacencia> getAdyacentes() {
        return adyacentes;
    }

    public TVertice(Comparable unaEtiqueta) {
        this.etiqueta = unaEtiqueta;
        adyacentes = new LinkedList();
        visitado = false;
    }

    public void setVisitado(boolean valor) {
        this.visitado = valor;
    }

    public boolean getVisitado() {
        return this.visitado;
    }

    @Override
    public int getBacon() {
        return bacon;
    }

    @Override
    public void setBacon(int newBacon) {
        this.bacon = newBacon;
    }

    private void setNumero_bp(int i) {
        numBp = i;
    }

    private void setNumeroBajo(int i) {
        numBajo = i;
    }

    private int getNumero_bp() {
        return numBp;
    }

    private int getNumeroBajo() {
        return numBajo;
    }

    public TVertice getPredecesor() {
        return predecesor;
    }

    private int getCantidadDescendientes() {
        return cantidadDescendientes;
    }

    @Override
    public TAdyacencia buscarAdyacencia(TVertice verticeDestino) {
        if (verticeDestino != null) {
            return buscarAdyacencia(verticeDestino.getEtiqueta());
        }
        return null;
    }

    @Override
    public Double obtenerCostoAdyacencia(TVertice verticeDestino) {
        TAdyacencia ady = buscarAdyacencia(verticeDestino);
        if (ady != null) {
            return ady.getCosto();
        }
        return Double.MAX_VALUE;
    }

    @Override
    public boolean insertarAdyacencia(Double costo, TVertice verticeDestino) {
        if (buscarAdyacencia(verticeDestino) == null) {
            TAdyacencia ady = new TAdyacencia(costo, verticeDestino);
            return adyacentes.add(ady);
        }
        return false;
    }

    @Override
    public boolean eliminarAdyacencia(Comparable nomVerticeDestino) {
        TAdyacencia ady = buscarAdyacencia(nomVerticeDestino);
        if (ady != null) {
            adyacentes.remove(ady);
            return true;
        }
        return false;
    }

    @Override
    public TVertice primerAdyacente() {
        if (this.adyacentes.getFirst() != null) {
            return this.adyacentes.getFirst().getDestino();
        }
        return null;
    }

    @Override
    public TVertice siguienteAdyacente(TVertice w) {
        TAdyacencia adyacente = buscarAdyacencia(w.getEtiqueta());
        int index = adyacentes.indexOf(adyacente);
        if (index + 1 < adyacentes.size()) {
            return adyacentes.get(index + 1).getDestino();
        }
        return null;
    }

    @Override
    public TAdyacencia buscarAdyacencia(Comparable etiquetaDestino) {
        for (TAdyacencia adyacencia : adyacentes) {
            if (adyacencia.getDestino().getEtiqueta().compareTo(etiquetaDestino) == 0) {
                return adyacencia;
            }
        }
        return null;
    }

    /**
     *
     * @return
     */
    public T getDatos() {
        return datos;
    }

    @Override
    public void bpf(Collection<TVertice> visitados) {
        this.setVisitado(true);
        visitados.add(this);
        this.adyacentes.forEach((ad) -> {
            if (!ad.getDestino().getVisitado()) {
                ad.getDestino().bpf(visitados);
            }
        });
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
        this.setVisitado(true);
        this.getAdyacentes().forEach(adyacencia -> {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                }
            }
        });
        this.setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos, int largoMaximo) {
        if (caminoPrevio.getOtrosVertices().size() > 10) {
            return todosLosCaminos;
        }

        this.setVisitado(true);
        this.getAdyacentes().forEach((adyacencia) -> {
            TVertice destino = adyacencia.getDestino();
            if (!destino.getVisitado()) {
                if (destino.getEtiqueta().compareTo(etVertDest) == 0) {
                    TCamino copia = caminoPrevio.copiar();
                    copia.agregarAdyacencia(adyacencia);
                    todosLosCaminos.getCaminos().add(copia);
                } else {
                    caminoPrevio.agregarAdyacencia(adyacencia);
                    destino.todosLosCaminos(etVertDest, caminoPrevio, todosLosCaminos);
                    caminoPrevio.eliminarAdyacencia(adyacencia);
                }
            }
        });
        this.setVisitado(false);
        return todosLosCaminos;
    }

    @Override
    public boolean tieneCiclo(TCamino unCamino) {
        if (this.getVisitado() == true) {
            return unCamino.getOtrosVertices().contains(this.getEtiqueta());
        }

        this.setVisitado(true);

        for (TAdyacencia ad : this.adyacentes) {
            unCamino.agregarAdyacencia(ad);
            if (ad.getDestino().tieneCiclo(unCamino)) {
                return true;
            }
            unCamino.eliminarAdyacencia(ad);
        }

        return false;
    }

    @Override
    public void bea(Collection<TVertice> visitados) {
        this.setVisitado(true);
        LinkedList<TVertice> cola = new LinkedList<>();
        cola.add(this);
        while (!cola.isEmpty()) {
            TVertice vertice = cola.removeFirst();
            LinkedList<TAdyacencia> Adyacentes = vertice.getAdyacentes();
            for (TAdyacencia ad : Adyacentes) {
                TVertice destino = ad.getDestino();
                if (!destino.visitado) {
                    destino.setVisitado(true);
                    cola.add(destino);
                    visitados.add(destino);
                }
            }
        }
    }

    public int beaBacon(Comparable actor) {
        if (etiqueta.compareTo(actor) == 0) {   //En el caso de que el actor buscado sea Bacon.
            return 0;
        } else {
            Queue<TVertice> cola = new LinkedList<>();
            cola.add(this);
            setVisitado(true);
            setBacon(0);    //Le pongo cero ya que este es Bacon

            while (!cola.isEmpty()) {
                TVertice x = cola.remove();
                LinkedList<TAdyacencia> adyacentes = x.getAdyacentes();
                for (TAdyacencia ady : adyacentes) {
                    TVertice destino = ady.getDestino();
                    if (destino.getEtiqueta().equals(actor)) {
                        return x.getBacon() + 1;
                    } else {
                        if (!destino.getVisitado()) {
                            destino.setVisitado(true);
                            cola.add(destino);
                            destino.setBacon(x.getBacon() + 1);
                        }
                    }
                }
            }
            return -1;
        }
    }

    public void puntosArticulacion(Collection<TVertice> puntosDeArticulacion, int[] numerobp) {
        setVisitado(true);
        numerobp[0]++;
        setNumero_bp(numerobp[0]);
        setNumeroBajo(numerobp[0]);

//        int numerobpSiguiente = numerobp[0];
        LinkedList<TVertice> hijos = new LinkedList<>();
        for (TAdyacencia adyacente : adyacentes) {
            TVertice destino = adyacente.getDestino();
            if (!destino.getVisitado()) {
                destino.puntosArticulacion(puntosDeArticulacion, numerobp);
                hijos.add(destino); //Agrego nuevo hijo a la lista.
                if (destino.getNumeroBajo() < getNumeroBajo()) {
                    setNumeroBajo(destino.getNumeroBajo());
                }
            } else {
                if (destino.getNumero_bp() < getNumeroBajo()) {
                    setNumeroBajo(destino.getNumero_bp());
                }
            }
        }
        if (getNumero_bp() == 1) {
            if (hijos.size() >= 2) {
                puntosDeArticulacion.add(this);
            }
        } else {
            for (TVertice v : hijos) {
                if (v.getNumeroBajo() >= getNumero_bp()) {
                    puntosDeArticulacion.add(this);
                }
            }
        }
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void ordenTopologico() {
        setVisitado(true);
        for (TAdyacencia ady : this.getAdyacentes()) {
            if (!ady.getDestino().getVisitado()) {
                ady.getDestino().ordenTopologico();
            }
        }
        //setVisitado(false);                                                   //Esta linea hace todos los ordenes topologicos.
        System.out.println(getEtiqueta());
    }

    public void caminoMasCorto(Comparable etiquetaDestino) {
        Queue<TVertice> cola = new LinkedList<>();

        predecesor = null;
        if (etiquetaDestino.equals(etiqueta)) {
            return;
        }
        cola.add(this);

        while (!cola.isEmpty()) {
            TVertice primero = cola.poll();
            primero.setVisitado(true);

            LinkedList<TAdyacencia> adyacencias = primero.getAdyacentes();
            for (TAdyacencia adyacencia : adyacencias) {
                TVertice destino = adyacencia.getDestino();
                if (destino.getEtiqueta().equals(etiquetaDestino)) {
                    destino.predecesor = primero;
                    return;
                }
                if (!destino.getVisitado()) {
                    destino.predecesor = primero;
                    destino.setVisitado(true);
                    cola.add(destino);
                }
            }
        }
    }

    public TCamino caminoMenosCostoso(Comparable etiquetaDestino, TCamino caminoActual, TCamino mejorCamino) {
        setVisitado(true);

        for (TAdyacencia ady : adyacentes) {
            TVertice destino = ady.getDestino();
            if (!destino.getVisitado()) {
                caminoActual.agregarAdyacencia(ady);
                if (destino.getEtiqueta().equals(etiquetaDestino)) {
                    if (mejorCamino == null || caminoActual.getCostoTotal() < mejorCamino.getCostoTotal()) {
                        mejorCamino = caminoActual.copiar();
                    }
                }
                mejorCamino = destino.caminoMenosCostoso(etiquetaDestino, caminoActual, mejorCamino);
                caminoActual.eliminarAdyacencia(ady);
            }
        }
        setVisitado(false);
        return mejorCamino;
    }

    public void clasificarArcos(LinkedList<TArista> arcosArbol, LinkedList<TArista> arcosRetroceso, LinkedList<TArista> arcosAvance, LinkedList<TArista> arcosCruzados) {
        setVisitado(true);

        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            TArista arista = new TArista(this.getEtiqueta(), vertAdy.getEtiqueta(), adyacente.getCosto());
            if (!vertAdy.getVisitado()) {
                arcosArbol.add(arista);
                vertAdy.clasificarArcos(arcosArbol, arcosRetroceso, arcosAvance, arcosCruzados);
            } else {
                if (this.getNumero_bp() < vertAdy.getNumero_bp()) {
                    arcosAvance.add(arista);
                } else if (this.getNumero_bp() <= (vertAdy.getNumero_bp() + vertAdy.getCantidadDescendientes())) {
                    arcosRetroceso.add(arista);
                } else {
                    arcosCruzados.add(arista);
                }
            }
        }
    }

    public void bpfConNumero(int[] numerobp) {
        setVisitado(true);
        numerobp[0] = numerobp[0] + 1;
        setNumero_bp(numerobp[0]);

        for (TAdyacencia adyacente : adyacentes) {
            TVertice vertAdy = adyacente.getDestino();
            if (!vertAdy.getVisitado()) {

                vertAdy.bpfConNumero(numerobp);
                cantidadDescendientes += vertAdy.getCantidadDescendientes() + 1;
            }
        }
    }
}
