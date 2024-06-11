import java.util.Arrays;

public class RadixSort {

    // Function to perform counting sort based on the digit represented by exp1
    public static void countingSort(int[] arr, int exp1) {
        int n = arr.length;
        int[] output = new int[n]; // Output array
        int[] count = new int[10]; // Count array to store occurrences of digits

        // Store count of occurrences of digits
        for (int i = 0; i < n; i++) {
            int index = (arr[i] / exp1) % 10;
            count[index]++;
        }

        // Change count[i] so that it contains the actual position of the digit in output[]
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (int i = n - 1; i >= 0; i--) {
            int index = (arr[i] / exp1) % 10;
            output[count[index] - 1] = arr[i];
            count[index]--;
        }

        // Copy the output array to arr[], so that arr[] now contains sorted numbers
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Function to perform radix sort
    public static void radixSort(int[] arr) {
        // Find the maximum number to know the number of digits
        int max1 = Arrays.stream(arr).max().getAsInt();

        // Do counting sort for every digit
        for (int exp = 1; max1 / exp > 0; exp *= 10) {
            countingSort(arr, exp);
        }
    }

    public static void main(String[] args) {
        int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};

        radixSort(arr);

        // Print the sorted array
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}