import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

class Job{
	int id;
	int deadline;
	int profit;
	Job(int id, int d, int p){
		this.id = id;
		this.deadline = d;
		this.profit = p;
	}
}

public class JobSequencing {
	
	static void printJobSchedule(Job[] arr, int n) {
		Arrays.sort(arr, (a,b) -> a.deadline - b.deadline);
		
		ArrayList<Job> res = new ArrayList<>();
		PriorityQueue<Job> pq = new PriorityQueue<>(
				(a,b) -> b.profit - a.profit
			);
		
		for(int i=n-1; i>=0; i--) {
			int slot_available;
			
			// calculate slot between two deadlines
			if(i==0) {
				slot_available = arr[i].deadline;
			}
			else {
				slot_available = arr[i].deadline - arr[i-1].deadline;
			}
			
			// including profit of job as priority
			pq.add(arr[i]);
			
			while(slot_available > 0 && pq.size() > 0) {
				// get job with max profit
				Job job = pq.poll();
				
				// reduce the slots
				slot_available--;
				
				// include job in result array
				res.add(job);
			}
		}
		
		// jobs included might be shuffled
		// so we sort them by their deadlines
		Collections.sort(res, (a,b) -> a.deadline - b.deadline);
		
		System.out.println("Job sequence with maximum profit ::");
		for(Job j : res) {
			System.out.print(j.id + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter number of jobs");
		int n = sc.nextInt();
		
		Job[] arr = new Job[n];
		for(int i=0; i<n; i++) {
			System.out.println("Enter deadline and profit of the job "+(i+1));
			int d = sc.nextInt();
			int p = sc.nextInt();
			arr[i] = new Job(i+1, d, p);
		}
		
		printJobSchedule(arr, n);
		
	}

}
