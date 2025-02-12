import java.util.Scanner;

class HorizontalCrossing {
    public static void main(String[] args) throws Exception {
        // Create scanner
        Scanner read = new Scanner(System.in);
        // Read the size
        int polygon_size = Integer.parseInt(read.nextLine());
        // Read the numbers
        int[] y_values = new int[polygon_size];
        for(int i = 0; i < polygon_size; i++) {
            // Skip x values
            read.nextInt();
            y_values[i] = read.nextInt();
        } 
        read.close();
        // Create a 2D array to store each vertex and its type
        // Array is double the size of the polygon to store both the start and end of each edge
        int[][] vertices = new int[2 * polygon_size][2];
        // Create a counter to track the the position in the vertex array without offsetting the index counter
        int position = 0;
        for(int i = 0; i < polygon_size; i++) {
            int current = y_values[i];
            // Use modulo to create a closed polygon
            int next = y_values[(i+1) % polygon_size];
            if(current != next) {
                // Use min and max to find the start and end of each edge
                vertices[position][0] = Math.min(current, next);
                vertices[position][1] = 1;
                position++;
                vertices[position][0] = Math.max(current, next);
                vertices[position][1] = -1;
                position++;
            }
        }
        // Use modified merge sort to sort the vertices by y value
        sort(vertices, 0, 2 * polygon_size - 1);
        // Create counters to track the number of intersections and the maximum number of intersections
        int intersections = 0;
        int max_intersections = 0;
        // Increment through each vertex and update the counters
        for(int[] vertex : vertices) {
            intersections = intersections + vertex[1];
            if(intersections > max_intersections) {
                max_intersections = intersections;
            }
        }
        // Print the max
        System.out.println(max_intersections);
    }
    
    // MERGE SORT FROM PREVIOUS HOMEWORK
    // Modified to take a 2D array and sort by its first element unless the first elements are equal, in which case it sorts by the second element
    static void merge(int[][] parent_array, int left, int midpoint, int right) {
        // Find array sizes
        int left_size = midpoint - left + 1;
        int right_size = right - midpoint;
        // Create left and right arrays
        int left_array[][] = new int[left_size][];
        int right_array[][] = new int[right_size][];
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
        // MODIFIED TO SORT BY SECOND ELEMENT IF FIRST ELEMENTS ARE EQUAL
        while (i < left_size && j < right_size) {
            if (left_array[i][0] < right_array[j][0] || (left_array[i][0] == right_array[j][0] && left_array[i][1] <= right_array[j][1])) {
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

    // Modified to take 2D array
    static void sort(int[][] parent_array, int left, int right) {
        if (left < right) {
            int midpoint = left + (right - left) / 2;
            sort(parent_array, left, midpoint);
            sort(parent_array, midpoint + 1, right);
            merge(parent_array, left, midpoint, right);
        }
    }
}