package TGrafoDirigido;

import java.util.LinkedList;

public class TCamino {

    public TVertice origen;
    public LinkedList<Comparable> otrosVertices;
    private Double costoTotal;

    public TCamino(TVertice v) {
        this.origen = v;
        this.otrosVertices = new LinkedList<>();
        this.costoTotal = 0.0;
    }
    
    public TVertice getOrigen() {
        return origen;
    }

    public LinkedList<Comparable> getOtrosVertices() {
        return otrosVertices;
    }

    public Double getCostoTotal() {
        return costoTotal;
    }
    
    public void ImprimirEtiquetas() {
        System.out.print(origen.getEtiqueta() + ", ");
        System.out.println(otrosVertices.toString().substring(1, otrosVertices.toString().length()-1));
    }
    
    public String devolverCamino(){
        return origen.getEtiqueta() + ", " + otrosVertices.toString();
    }

    public boolean agregarAdyacencia(TAdyacencia adyacenciaActual) {
        if (adyacenciaActual.getDestino() != null) {
            costoTotal = costoTotal + ((Number) adyacenciaActual.getCosto()).doubleValue();
            return otrosVertices.add(adyacenciaActual.getDestino().getEtiqueta());
        }
        return false;
    }

    public boolean eliminarAdyacencia(TAdyacencia adyacenciaActual) {
        if (otrosVertices.contains(adyacenciaActual.getDestino().getEtiqueta())) {
            costoTotal = costoTotal - ((Number) adyacenciaActual.getCosto()).doubleValue();
            return (otrosVertices.remove(adyacenciaActual.getDestino().getEtiqueta()));
        }
        return false;
    }

    public TCamino copiar() {
        TVertice origen = new TVertice(this.getOrigen().getEtiqueta());
        TCamino copia = new TCamino(origen);
        origen.getAdyacentes().addAll(this.getOrigen().getAdyacentes());
        copia.getOtrosVertices().addAll(this.getOtrosVertices());
        return copia;
    }

}