package TGrafoDirigido;


import java.util.Collection;
import java.util.LinkedList;

public class TCaminos {// contendrá elementos del tipo TCamino

    private LinkedList<TCamino> Caminos;

    public TCaminos() {
        Caminos = new LinkedList<>();
    } 
    
    public void agregarCaminos(TCamino nuevoCamino){
        Caminos.add(nuevoCamino);
    }

    public void imprimir() {
        for(TCamino camino : Caminos){
            camino.ImprimirEtiquetas();
        }
    } 
    
    public Collection<TCamino> getCaminos(){
        return Caminos;
    }
}
