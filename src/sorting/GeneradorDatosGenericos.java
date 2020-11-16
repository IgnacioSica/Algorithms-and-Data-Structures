package sorting;

import java.util.Random;

public class GeneradorDatosGenericos {

    private static int TAMANIO_MAX = 3200;

    public int[] generarDatosAleatorios() {
        return generarDatosAleatorios(TAMANIO_MAX);
    }

    public int[] generarDatosAleatorios(int tamaño) {
        Random rnd = new Random();
        int[] datosGenerados = new int[tamaño];
        boolean[] datosUtilizados = new boolean[tamaño];
        for (int i = 0; i < datosGenerados.length; i++) {
            int j = rnd.nextInt(tamaño);
            while (datosUtilizados[j]) {
                j = (j + 1) % tamaño;
            }
            datosGenerados[j] = i;
            datosUtilizados[j] = true;
        }
        return datosGenerados;
    }

    public int[] generarDatosAscendentes() {
        return generarDatosAscendentes(TAMANIO_MAX);
    }

    public int[] generarDatosAscendentes(int tamaño) {
        int[] copiaAscendente = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            copiaAscendente[i] = i;
        }
        return copiaAscendente;
    }

    public int[] generarDatosDescendentes() {
        return generarDatosDescendentes(TAMANIO_MAX);
    }

    public int[] generarDatosDescendentes(int tamaño) {
        int[] copiaDescendente = new int[tamaño];
        for (int i = 0; i < tamaño; i++) {
            copiaDescendente[i] = tamaño - i;
        }
        return copiaDescendente;
    }
}
