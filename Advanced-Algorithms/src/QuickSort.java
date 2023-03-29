import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuickSort {
	
	int partition (int a[], int start, int end)  
	{  
	    int pivot = a[end]; // pivot element  
	    int i = (start - 1);  
	  
	    for (int j = start; j <= end - 1; j++)  
	    {  
	        // If current element is smaller than the pivot  
	        if (a[j] < pivot)  
	        {  
	            i++; // increment index of smaller element  
	            int t = a[i];  
	            a[i] = a[j];  
	            a[j] = t;  
	        }  
	    }  
	    int t = a[i+1];  
	    a[i+1] = a[end];  
	    a[end] = t;  
	    
	    return (i + 1);  
	}  
	
	void sort(int a[], int start, int end) /* a[] = array to be sorted, start = Starting index, end = Ending index */  
	{  
	    if (start < end)  
	    {  
	        int p = partition(a, start, end); //p is the partitioning index  
	        sort(a, start, p - 1);  
	        sort(a, p + 1, end);  
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

	public static void main(String[] args) throws IOException{
		// we can do sorting 2**10 (1024) size array 1024 times and finally merge the 
		// complete 1024 sorted arrays
		int n = (int)Math.pow(2, 10);
		

		final String CUR_DIR = System.getProperty("user.dir");
		final String NUM_FILENAME = CUR_DIR + "/src/static/quicksort_random_numbers.txt";
		final String RESULT_FILENAME = CUR_DIR + "/src/static/quicksort_sorted_numbers.txt";
		final String FINAL_FILENAME = CUR_DIR + "/src/static/quicksort_final.txt";
		
		
		// create an array of random numbers
		Random rd = new Random();
		int[] arr = new int[n];
		
		for(int i=0; i<n; i++) {
			arr[i] = rd.nextInt(20000);
		}
		
		// save this array in a file
		File file = new File(NUM_FILENAME);
		File resultFile = new File(RESULT_FILENAME);
		File finalFile = new File(FINAL_FILENAME);
		
		QuickSort ob = new QuickSort();
		
		
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
		
		
		double startTime = System.currentTimeMillis();
		
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
		
		double endTime = System.currentTimeMillis();
		
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
       
		System.out.println(endTime - startTime);

	}

}
