import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class MergeSort {
	
	void merge(int arr[], int l, int m, int r)
    {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;
  
        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];
  
        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
  
        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            }
            else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
  
   
    void sort(int arr[], int l, int r)
    {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;
  
            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);
  
            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

	public static void main(String args[]) throws Exception{
		int n = (int)Math.pow(2, 5);
		
		final String CUR_DIR = System.getProperty("user.dir");
		final String NUM_FILENAME = CUR_DIR + "/src/static/random_numbers.txt";
		final String RESULT_FILENAME = CUR_DIR + "/src/static/mergesort_sorted_numbers.txt";
		
		// create an array of random numbers
		Random rd = new Random();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = rd.nextInt(20000);
		}
		
		// save this array in a file
		File file = new File(NUM_FILENAME);
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		bw.write( Arrays
				.stream(arr)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(" "))
				);
		bw.close();
		
		
		// collect array from the file
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = br.readLine();	// array is in a single line
		br.close();
		
		arr = Arrays
				.stream(line.split(" "))		// splitting int string to array of string
				.mapToInt(Integer::parseInt)	// converting string to int
				.toArray();						// changing the stream to an array
		
		// sort this array
		MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);
        
        // store the sorted array in a new sorted file
        File resultFile = new File(RESULT_FILENAME);
        bw = new BufferedWriter(new FileWriter(resultFile));
        bw.write( Arrays
        		.stream(arr)
        		.mapToObj(String::valueOf)
        		.collect(Collectors.joining(" "))
        		);
        bw.close();
        
		
		Arrays.stream(arr).forEach(val -> System.out.print(val + " "));
	}
	
}
