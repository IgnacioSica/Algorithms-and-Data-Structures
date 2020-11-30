package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
        int[] gaps = new int[]{3223, 301, 132, 57, 23, 10, 4, 1};
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

        for (int a : arr) {
            k = k > a ? k : a;
        }

        int[] count = new int[k + 1];

        for (int x : arr) {
            ++count[x];
        }

        for (int i = 1; i <= k; ++i) {
            count[i] += count[i - 1];
        }

        int[] output = new int[n];

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            --count[arr[i]];
        }

        for (int i = 0; i < n; ++i) {
            arr[i] = output[i];
        }

        return arr;
    }

    int[] bucketSort(int[] arr) {
        int n = arr.length;
        ArrayList<Integer>[] bucket = new ArrayList[10];

        int k = 0;
        for (int a : arr) {
            k = k > a ? k : a;
        }
        
        int range = getBSRange(k);
        
        for (int i = 0; i < 10; i++) {
            bucket[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            bucket[getMSD(arr[i], range)].add(arr[i]);
        }

        for (int i = 0; i < 10; i++) {
            Collections.sort((bucket[i]));
        }

        int index = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
                arr[index++] = bucket[i].get(j);
            }
        }
        
        return arr;
    }
    
    private int getBSRange(int k){
        int div = 1;
        for(int i = 0; i < String.valueOf(k).length(); i++)
            div *= 10;
        return div;
    }
    
    private int getMSD (int num, int div){
        return num / div;
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
        quicksort(arr, 0, arr.length - 1);
        return arr;
    }

    private void quicksort(int[] arr, int i, int j) {
        int left = i;
        int right = j;

        int pivot = findPivot(left, right, arr); // find pivot with left and right. 

        if (pivot >= 0) {
            while (left <= right) {
                while ((arr[left] < pivot) && (left < j)) {
                    left++;
                }
                while ((pivot < arr[right]) && (right > i)) {
                    right--;
                }
                if (left <= right) {
                    swap(arr, right, left);
                    left++;
                    right--;
                }
            }
            if (i < right) {
                quicksort(arr, i, left - 1);
            }
            if (left < j) {
                quicksort(arr, left, j);
            }
        }
    }

    protected int findPivot(int i, int j, int[] arr) {
        return arr[(i + j) / 2];
    }

    int[] radixSort(int[] arr) {
        int k = 0;
        int n = arr.length;

        for (int a : arr) {
            k = k > a ? k : a;
        }

        for (int place = 1; k / place > 0; place *= 10) {
            countingSort(arr, n, place);
        }

        return arr;
    }

    int[] countingSort(int[] arr, int size, int place) {
        int k = 0;

        for (int a : arr) {
            k = k > a ? k : a;
        }

        int[] count = new int[k + 1];

        for (int x : arr) {
            ++count[(x / place) % 10];
        }

        for (int i = 1; i <= k; ++i) {
            count[i] += count[i - 1];
        }

        int[] output = new int[size];

        for (int i = size - 1; i >= 0; i--) {
            output[count[(arr[i] / place) % 10] - 1] = arr[i];
            --count[(arr[i] / place) % 10];
        }

        for (int i = 0; i < size; ++i) {
            arr[i] = output[i];
        }

        return arr;
    }

    public String[] radixSortSTR(String[] arr) {
        String abc = "0123456789abcdefghijklmnopqrstuvwxyz";
        LinkedList[] buckets = new LinkedList[abc.length()];
        LinkedList<String> output = new LinkedList<>();
        int max = -1;

        for (String n : arr) {
            output.add(n);
            if (n.length() > max) {
                max = n.length();
            }
        }

        for (int d = max - 1; d >= 0; d--) {
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList();
            }
            for (String elem : output) {
                Character c = elem.length() > d ? elem.charAt(d) : '0';
                int indice = abc.indexOf(c);
                buckets[indice].addLast(elem);
            }
            output = new LinkedList();
            for (LinkedList bucket : buckets) {
                output.addAll(bucket);
            }
        }

        String[] sorted = new String[arr.length];
        int i = 0;
        for (String n : output) {
            sorted[i++] = n;
        }

        return sorted;
    }

    private static HashMap<Comparable, Integer> getMap() {
        HashMap<Comparable, Integer> mapa = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            mapa.put(Character.toChars('0' + i)[0], i);
        }
        for (int i = 0; i < 26; i++) {
            mapa.put(Character.toChars('a' + i)[0], i + 10);
        }
        return mapa;
    }

    public String[] radixSortSTRM(String[] arr, Map<Comparable, Integer> map) {
        LinkedList[] buckets = new LinkedList[map.size()];
        LinkedList<String> output = new LinkedList<>();
        int max = -1;
        for (String n : arr) {
            output.add(n);
            if (n.length() > max) {
                max = n.length();
            }
        }
        for (int d = max - 1; d >= 0; d--) {
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList();
            }
            for (String elem : output) {
                Character c = elem.length() > d ? elem.charAt(d) : '0';
                int indice = map.get(c);
                buckets[indice].addLast(elem);
            }
            output = new LinkedList();
            for (LinkedList bucket : buckets) {
                output.addAll(bucket);
            }
        }
        String[] sorted = new String[arr.length];
        int i = 0;
        for (String n : output) {
            sorted[i++] = n;
        }
        return sorted;
    }

    int[] mergeSort(int[] arr) {
        mergesort(arr, 0, arr.length - 1);
        return arr;
    }

    private void mergesort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergesort(arr, l, m);
            mergesort(arr, m + 1, r);

            merge(arr, l, m, r);
        }
    }

    private void merge(int arr[], int p, int q, int r) {
        int n1 = q - p + 1;
        int n2 = r - q;

        int L[] = new int[n1];
        int M[] = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[p + i];
        }
        for (int j = 0; j < n2; j++) {
            M[j] = arr[q + 1 + j];
        }

        int i, j, k;
        i = 0;
        j = 0;
        k = p;

        while (i < n1 && j < n2) {
            if (L[i] <= M[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = M[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            arr[k] = M[j];
            j++;
            k++;
        }
    }
}
