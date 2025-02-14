import java.util.Scanner;

public class WeightedBySumInversions {
    public static void main(String[] args) throws Exception {
        // Create scanner
        Scanner read = new Scanner(System.in);
        // Read the size
        int size = read.nextInt();
        long[] numbers = new int[size + 1];
        numbers[0] = 0;
        for(int i = 1; i < size; i++) {
            numbers[i] = read.nextInt();
        }

    }

    public static long[] sort_and_count(int size, long[] numbers) {
        if(size == 1) {
            return numbers;
        }
        int midpoint = size / 2; 
        int left_size = midpoint;
        int right_size = size - midpoint;
        long left_array[] = new long[left_size + 1];
        long right_array[] = new long[right_size + 1];
        for(int i = 1; i < left_size; ++i) { 
            left_array[i] = numbers[left_size + i];
        }
        for(int j = 1; j < right_size; ++j) { 
            right_array[j] = numbers[midpoint + 1 + j];
        }
        long left_inversion = sort_and_count(left_size, left_array);
        long right_inversion = sort_and_count(right_ size, right_array);
        long middle_inversion = merge_and_count(left_array, right_array);

        long inversions = left_inversion[0] + right_inversion[0] + middle_inversion[0];
        middle_inversion[0] = inversions;
        return middle_inversion;
    }
}