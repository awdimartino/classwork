import java.util.Scanner;

public class FindMaxPairsDouble {
    public static void main(String[] args) throws Exception {
        // Create scanner
        Scanner read = new Scanner(System.in);
        // Read the size
        int numbers_size = Integer.parseInt(read.nextLine());
        // Read the numbers
        double[] numbers = new double[numbers_size];
        for (int i = 0; i < numbers_size; i++) {
            numbers[i] = read.nextDouble();
        }
        // Merge sort the input
        sort(numbers, 0, numbers_size - 1);
        // System.out.println(Arrays.toString(numbers)); // Test function
        // Create an array which stores all sums
        double sums[] = new double[(numbers_size * (numbers_size - 1)) / 2];
        int pos = 0;
        // Generate all pairs and calculate their sums
        // Left value is always smaller than right as list is sorted
        for(int i = 0; i < numbers_size; i++) {
            for(int j = i + 1; j < numbers_size; j++) {
                sums[pos++] = numbers[i] + numbers[j];
            }
        }
        // Sort the sums
        sort(sums, 0, sums.length - 1);

        double max_sum = Double.MAX_VALUE;
        int max_freq = 0;
        int current_freq = 1;
        
        // Count the frequencies of each sum
        for (int i = 1; i < sums.length; i++) {
            if (sums[i] == sums[i - 1]) {
                current_freq++;
            } else {
                // If a greater frequency is found, replace the max frequency
                if (current_freq > max_freq || (current_freq == max_freq && sums[i - 1] < max_sum)) {
                    max_freq = current_freq;
                    max_sum = sums[i - 1];
                }
                current_freq = 1;
            }
        }
        // Check end of array
        if (current_freq > max_freq || (current_freq == max_freq && sums[sums.length - 1] < max_sum)) {
            max_freq = current_freq;
            max_sum = sums[sums.length - 1];
        }
        // Print the frequency and sum
        System.out.printf("%d %.6f\n", max_freq, max_sum);        
        read.close();
    }

    // Merge sort functions
    static void merge(double[] parent_array, int left, int midpoint, int right) {
        // Find array sizes
        int left_size = midpoint - left + 1;
        int right_size = right - midpoint;
        // Create left and right arrays
        double left_array[] = new double[left_size];
        double right_array[] = new double[right_size];
        // Split parent array
        for(int i = 0; i < left_size; ++i) { 
            left_array[i] = parent_array[left + i];
        }
        for(int j = 0; j < right_size; ++j) { 
            right_array[j] = parent_array[midpoint + 1 + j];
        }
        // Initialize indexes for parent and children arrays
        int i = 0;
        int j = 0;
        int k = left;
        // Sort
        while (i < left_size && j < right_size) {
            if (left_array[i] <= right_array[j]) {
                parent_array[k] = left_array[i];
                i++;
            } else {
                parent_array[k] = right_array[j];
                j++;
            }
            k++;
        }
        // Handle remaining elements
        while (i < left_size) {
            parent_array[k] = left_array[i];
            i++;
            k++;
        }
        while (j < right_size) {
            parent_array[k] = right_array[j];
            j++;
            k++;
        }
    }

    static void sort(double[] parent_array, int left, int right) {
        if (left < right) {
            int midpoint = left + (right - left) / 2;
            sort(parent_array, left, midpoint);
            sort(parent_array, midpoint + 1, right);
            merge(parent_array, left, midpoint, right);
        }
    }
}
