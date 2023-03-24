import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public class QuickSort {
	
	int partition(int[] arr, int l, int r) {
		return 0;
	}
	
	void sort(int[] arr, int l, int r) {
		if(l<r) {
			int pivot = partition(arr, l, r);
			
			sort(arr, l, pivot-1);
			sort(arr, pivot, r);
		}
	}

	public static void main(String[] args) throws IOException{
		int n = (int)Math.pow(2, 5);
		
		final String CUR_DIR = System.getProperty("user.dir");
		final String NUM_FILENAME = CUR_DIR + "/src/static/quicksort_random_numbers.txt";
		final String RESULT_FILENAME = CUR_DIR + "/src/static/quicksort_sorted_numbers.txt";
		
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
		QuickSort ob = new QuickSort();
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