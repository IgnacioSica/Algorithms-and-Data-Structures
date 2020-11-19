package sorting;

import java.util.ArrayList;
import java.util.Collections;

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

    int[] shellSort(int[] arr) {
        int j;
        int[] gaps = new int[]{3223, 358, 51, 10, 3, 1};
        for (int gap : gaps) {
            if (gap <= (arr.length / 2)) {
                for (int i = gap; i < arr.length; i++) {
                    j = i - gap;
                    while (j >= 0) {
                        if (arr[j] > arr[j + gap]) {
                            swap(arr, j, j + gap);
                        }
                        j -= gap;
                    }
                }
            }
        }
        return arr;
    }

    int[] countingSort(int[] arr) {
        int k = 0;
        int n = arr.length;
        
        for (int a : arr) 
            k = k > a ? k : a;

        int[] count = new int[k + 1];

        for (int x : arr) 
            ++count[x];

        for (int i = 1; i <= k; ++i) 
            count[i] += count[i - 1];
        
        int[] output = new int[n];
        
        for (int i = n - 1; i >= 0; i--) { 
            output[count[arr[i]] - 1] = arr[i]; 
            --count[arr[i]]; 
        }
        
        for (int i = 0; i < n; ++i) 
            arr[i] = output[i];

        return arr;
    }

    int[] bucketSort(int[] arr) {
        int n = arr.length;
        ArrayList<Integer>[] bucket = new ArrayList[n];
        
        for (int i = 0; i < n; i++)
            bucket[i] = new ArrayList<>();
        
        for (int i = 0; i < n; i++) 
            bucket[arr[i]].add(arr[i]);
        
        for (int i = 0; i < n; i++) 
            Collections.sort((bucket[i]));
    
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
            arr[index++] = bucket[i].get(j);
            }
        }
        return arr;
    }

    int[] radixSort(int[] arr) {
        return arr;
    }
    
    int[] heapSort(int[] arr) {
        int n = arr.length;
        
        for (int i = (n - 1) / 2; i >= 0; i--) {
            heapify(arr, i, n - 1);
        }
        
        for (int i = n - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapify(arr, 0, i - 1);
        }
        
        return arr;
    }
    
     private void heapify(int[] arr, int first, int last) {
        if (first < last) {
            int r = first;
            while (r <= last / 2) {
                if (last == 2 * r) {
                    if (arr[r] < arr[r * 2]) {
                        swap(arr, r, 2 * r);
                    }
                    r = last;
                } else {
                    int swapPosition;
                    if (arr[2 * r] > arr[2 * r + 1]) {
                        swapPosition = 2 * r;
                    } else {
                        swapPosition = 2 * r + 1;
                    }
                    if (arr[r] < arr[swapPosition]) {
                        swap(arr, r, swapPosition);
                        r = swapPosition;
                    } else {
                        r = last;
                    }
                }
            }
        }
    }
    
    int[] quickSort(int[] arr) {
        return arr;
    }

    int[] mergeSort(int[] arr) {
        return arr;
    }
}
