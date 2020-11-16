package sorting;

public class TClasificador {

    private void swap(int[] vector, int pos1, int pos2) {
        int temp = vector[pos2];
        vector[pos2] = vector[pos1];
        vector[pos1] = temp;
    }
    
    int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int jMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                if(arr[j] < arr[jMin])
                    jMin = j;
            }
            if(jMin != i)
                swap(arr, jMin, i);
        }
        return arr;
    }
    
}
