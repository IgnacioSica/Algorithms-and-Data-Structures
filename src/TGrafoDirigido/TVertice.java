package TGrafoDirigido;


import java.util.Collection;
import java.util.LinkedList;

public class TVertice<T> implements IVertice {

    private final Comparable etiqueta;
    private LinkedList<TAdyacencia> adyacentes;
    private boolean visitado;
    private T datos;

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
        this.adyacentes.forEach( (ad) -> {
            if(!ad.getDestino().getVisitado()){
                ad.getDestino().bpf(visitados);
            }
        });
    }
    
    @Override
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos) {
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
    public TCaminos todosLosCaminos(Comparable etVertDest, TCamino caminoPrevio, TCaminos todosLosCaminos, int largoMaximo) {
        if(caminoPrevio.getOtrosVertices().size() > 10)
            return todosLosCaminos;
        
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
    public boolean tieneCiclo (TCamino unCamino){
        if(this.getVisitado() == true){
            return unCamino.getOtrosVertices().contains(this.getEtiqueta());
        } 
        
        this.setVisitado(true);
        
        for(TAdyacencia ad : this.adyacentes){
            unCamino.agregarAdyacencia(ad);
            if(ad.getDestino().tieneCiclo(unCamino))
                return true;
            unCamino.eliminarAdyacencia(ad);
        }
        
        return false;
    }
    
    @Override
    public void bea(Collection<TVertice> visitados) 
    {
        this.setVisitado(true);
        LinkedList<TVertice> cola = new LinkedList<>();
        cola.add(this);
        Comparable tempstr = this.etiqueta;
        while (!cola.isEmpty()) 
        {
            TVertice x = cola.removeFirst();
            LinkedList<TAdyacencia> linkedAdy = x.getAdyacentes();
            for (TAdyacencia ady : linkedAdy) 
            {
                TVertice verticeAdy = ady.getDestino();
                if (!verticeAdy.visitado) 
                {
                    verticeAdy.setVisitado(true);
                    tempstr = tempstr.toString() + verticeAdy.etiqueta;
                    visitados.add(verticeAdy);
                }
            }
        }
    }
}
