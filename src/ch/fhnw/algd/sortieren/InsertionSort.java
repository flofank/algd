package ch.fhnw.algd.sortieren;


public class InsertionSort {
    public static void main(String[] args) {
        double[] a = {4,23,5664,2,3,6,2456,7,3456,3,56,3456,35};
//        double[] a = {1,3,2};
        betterSort(a);
        System.out.println("----------");
        for (double b : a) {
            System.out.println(b + ",");
        }
    }
    
    public static void sort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            double x = a[i];
            int k = 0;
            while (x > a[k] && k++ < i) {
            }
            for (int j = i - 1; j >= k; j--) {
                a[j + 1] = a[j];
            }
            a[k] = x;
        }
    }
    
    public static void betterSort(double[] a) {
        for (int i = 1; i < a.length; i++) {
            double x = a[i];
            int k = i - 1;
            while (k >=0 && x < a[k]) {
                a[k+1] = a[k];
                k--;
            }
            a[k+1] = x;
        }
    }
}
