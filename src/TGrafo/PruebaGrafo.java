package TGrafo;

import java.util.LinkedList;

public class PruebaGrafo {

    public static void main(String[] args) {
        TGrafoDirigido gd = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/aeropuertos.txt", "./src/conexiones.txt",
                false, TGrafoDirigido.class);

        TGrafoDirigido gd2 = (TGrafoDirigido) UtilGrafos.cargarGrafo("./src/aeropuertos_2.txt", "./src/conexiones_2.txt",
                false, TGrafoDirigido.class);

        Object[] etiquetasarray = gd.getEtiquetasOrdenado();

        Double[][] matriz = UtilGrafos.obtenerMatrizCostos(gd.getVertices());
        UtilGrafos.imprimirMatrizMejorado(matriz, gd.getVertices(), "Matriz");
        Comparable[][] mfloyd = gd.floyd();
        UtilGrafos.imprimirMatrizMejorado(mfloyd, gd.getVertices(), "Matriz luego de FLOYD");

        boolean[][] mWharshall = gd.warshall();
        UtilGrafos.imprimirMatrizMejoradoBoolean(mWharshall, gd.getVertices(), "Matriz luego de WHARSHALL");

        System.out.println("-----------------------EJERCICIO 3-----------------------");
        //TVertice[] verticesImprimir = gd.bpf().toArray();
        for (TVertice v : gd.bpf()) {
            System.out.println(v.getEtiqueta());
        }
        System.out.println("-------------Con origen en Montevideo----------");
        for (TVertice v : gd.bpf("Montevideo")) {
            System.out.println(v.getEtiqueta());
        }

        System.out.println("-------------Todos los caminos Posibles desde Montevideo a Rio de Janeiro----------");
        TCaminos caminos = new TCaminos();
        TCamino elCamino = new TCamino(gd2.buscarVertice("1"));
        gd2.camino(gd2.buscarVertice("1"), gd2.buscarVertice("3"), caminos, elCamino);
        caminos.imprimir();
        System.out.println("----------------------------------------------------------");

        for (int i = 0; i < etiquetasarray.length; i++) {
            System.out.println("excentricidad de " + etiquetasarray[i] + " : " + gd.obtenerExcentricidad((Comparable) etiquetasarray[i]));
        }
        System.out.println();
        System.out.println("Centro del grafo: " + gd.centroDelGrafo());
        System.out.println();
    }
}
