import java.util.Scanner;
import java.util.Arrays;
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
        long[] result = sort_and_count(size, numbers);
        System.out.println(Arrays.toString(result));
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
        long[] left_inversion = sort_and_count(left_size, left_array);
        long[] right_inversion = sort_and_count(right_ size, right_array);
        long[] middle_inversion = merge_and_count(size, left_array, right_array);

        long inversions = left_inversion[0] + right_inversion[0] + middle_inversion[0];
        middle_inversion[0] = inversions;
        return middle_inversion;
    }

    public static long[] merge_and_count(int size, long[] left_array, long[] right_array) {
        long[] merged_array = new long[size + 1];
        int i = 1;
        int j = 1;
        long inversions = 0;
        for(int k = 1; k < size; k++) {
            if(j > size || left_array[i] < right_array[j]) {
                merged_array[k] = left_array[i];
                i++;
            } else {
                merged_array = right_array[j];
                j++;
                inversions += size + 1 - i;
            }
        }
        merged_array[0] = inversions;
        return merged_array;
    }
}