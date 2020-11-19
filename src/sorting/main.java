package sorting;

public class main {
    public static void main(String[] args){
        TClasificador clasif = new TClasificador();
        GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
        int[] arr = gdg.generarDatosAleatorios(1500);
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i]+" ");
        clasif.mergeSort(arr);
        System.out.println("");
        for(int i = 0; i < arr.length; i++)
            System.out.print(arr[i]+" "); 
        
        String[] str = {"sfasdf","fffgdgfd","bxvbxv","asdffd","fgrgqew","rewq","vbzcx","rtqe","xbvc","heqwtyr"};
        System.out.println("");
        for(int i = 0; i < str.length; i++)
            System.out.print(str[i]+" ");
        String[] ord = clasif.radixSortSTR(str);
        System.out.println("");
        for(int i = 0; i < ord.length; i++)
            System.out.print(ord[i]+" ");
    }
}
