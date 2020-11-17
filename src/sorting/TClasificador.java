package sorting;

public class TClasificador {

    private void swap(int[] arr, int p1, int p2) {
        int temp = arr[p2];
        arr[p2] = arr[p1];
        arr[p1] = temp;
    }

    int[] selectionSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int jMin = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[jMin]) {
                    jMin = j;
                }
            }
            if (jMin != i) {
                swap(arr, jMin, i);
            }
        }
        return arr;
    }

    int[] insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            while ((j >= 0) && (arr[j] > arr[j + 1])) {
                swap(arr, j, j + 1);
                j--;
            }
        }
        return arr;
    }

    int[] bubbleSort(int[] arr) {
        int n = arr.length;
        while (n >= 1) {
            int newN = 0;
            for (int i = 1; i < n; i++) {
                if (arr[i - 1] > arr[i]) {
                    swap(arr, i - 1, i);
                    newN = i;
                }
            }
            n = newN;
        }
        return arr;
    }
    
    int[] shellSort(int[] arr){
        return arr;
    }
    
    int[] quickSort(int[] arr){
        return arr;
    }

}
