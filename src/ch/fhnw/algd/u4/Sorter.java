package ch.fhnw.algd.u4;

public class Sorter {
    public static void main(String args[]) {
        double[] list1 = {1,3343,3,23,4,1234,3,2,344,123,43,3};
        printArray(insertionSort(list1));
        System.out.println(binarySearch(list1, 3343));
        
        double[] list2 = {3};
        printArray(insertionSort(list2));
        System.out.println(binarySearch(list2, 3));
        
        double[] list3 = {4,3,2,5,4,12345,45,2345,4,4,45,43,45,45,2435};
        printArray(insertionSort(list3));
        System.out.println(binarySearch(list3, 7));
    }
    
    public static double[] insertionSort(double[] list) {
        for (int i = 1; i < list.length; i++) {
            int j = i - 1;
            while (j >= 0 && list[j] > list[j + 1]) {
                double d = list[j];
                list[j] = list[j+1];
                list[j+1] = d;
                j--;
            }            
        }
        return list;
    }
    
    public static int binarySearch(double[] array, double d) {
        int first = 0;
        int last = array.length;
        while (first < last) {
            int mitte = (first + last) / 2;
            if (array[mitte] > d) {
                last = mitte - 1;
            } else if (array[mitte] < d) {
                first = mitte + 1;
            } else {
                return mitte;
            }
        }
        return -1;
    }
    
    public static void printArray(double[] array) {
        for (double d : array) {
            System.out.print(d + ", ");
        }
        System.out.print("\n");
    }
}
