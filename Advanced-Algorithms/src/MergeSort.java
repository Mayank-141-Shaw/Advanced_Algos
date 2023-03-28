import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    
    List<Integer> mergeArrayToList(List<Integer> res, int[] arr) {
    	int i=0, j=0;
    	List<Integer> res2 = new ArrayList<>();
    	while(i<res.size() || j<arr.length) {
    		if(res.get(i) <= arr[j]) res2.add(res.get(i++));
    		else res2.add(arr[j++]);
    		
    		if(i==res.size()) {
    			while(j<arr.length) {
    				res2.add(arr[j++]);
    			}
    		}
    		
    		if(j==arr.length) {
    			while(i<res.size()) {
    				res2.add(res.get(i++));
    			}
    		}
    	}
    	
    	return res2;
    }

	public static void main(String args[]) throws Exception{
		
		// we can do sorting 2**10 (1024) size array 1024 times and finally merge the 
		// complete 1024 sorted arrays
		int n = (int)Math.pow(2, 10);
		
		final String CUR_DIR = System.getProperty("user.dir");
		final String NUM_FILENAME = CUR_DIR + "/src/static/mergesort_random_numbers.txt";
		final String RESULT_FILENAME = CUR_DIR + "/src/static/mergesort_sorted_numbers.txt";
		final String FINAL_FILENAME = CUR_DIR + "/src/static/mergesort_final.txt";
		
		// create an array of random numbers
		Random rd = new Random();
		int[] arr = new int[n];
		
		
		
		// save this array in a file
		File file = new File(NUM_FILENAME);
		File resultFile = new File(RESULT_FILENAME);
		File finalFile = new File(FINAL_FILENAME);
		
		MergeSort ob = new MergeSort();
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(file));
		for(int line=0; line<1024; line++ ) {
			for(int i=0; i<n; i++) {
				arr[i] = rd.nextInt(20000);
			}
			String numLine = Arrays
					.stream(arr)
					.mapToObj(String::valueOf)
					.collect(Collectors.joining(" "));
			numLine += "\n";
			bw.write(numLine);
		}
		bw.close();
		
		
		// collect array from the file
		// read one line, sort it and add it into the result file line by line
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		
		
		bw = new BufferedWriter(new FileWriter(resultFile));
		
		
		
		for(int line=0; line<1024; line++) {
			String line_arr = br.readLine();	// array is in a single line
			arr = Arrays
					.stream(line_arr.replace("\n", "").split(" "))		// splitting int string to array of string
					.mapToInt(Integer::parseInt)	// converting string to int
					.toArray();						// changing the stream to an array
			
			// sort this array
	        ob.sort(arr, 0, arr.length - 1);
	        
	        // store the sorted array in a new sorted file
	        bw.write( Arrays
	        		.stream(arr)
	        		.mapToObj(String::valueOf)
	        		.collect(Collectors.joining(" "))
	        		);
	        bw.newLine();
		}
		
		br.close();
		bw.close();
		
		
		// merging the 1024 arrays
		
		// read the result file
		BufferedReader br1 = new BufferedReader(new FileReader(resultFile));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter(finalFile));
		List<Integer> res= new ArrayList<>();
		
		arr = Arrays
				.stream(br1.readLine().replace("\n", "").split(" "))		
				.mapToInt(Integer::parseInt)
				.toArray();
		
		// added all items to the list
		for(int item:arr) res.add(item);
		
		for(int i=1; i<1024; i++) {
			arr = Arrays
					.stream(br1.readLine().replace("\n", "").split(" "))
					.mapToInt(Integer::parseInt)
					.toArray();
			
			// merge this arr to res list
			res = ob.mergeArrayToList(res, arr);
		}
		
		// after all the merging, print the list into the result file after clearing the entire file
		
        // clear the result file
		bw1.flush();
		
		// add this arraylist as array of string in the file
		String array = Arrays
				.stream(
						res
						.stream()
						.mapToInt(Integer::intValue)
						.toArray()
						)
				.mapToObj(String::valueOf)
				.collect(Collectors.joining(" "));
		
        bw1.write( array );
        
        br1.close();
        bw1.close();
       
		System.out.println(res.get(res.size()-1)+ " "+res.get(0));
	}
	
}
