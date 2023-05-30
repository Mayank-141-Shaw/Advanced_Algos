import java.util.Arrays;
import java.util.Scanner;

class Pair{
	double wt, profit;
	public Pair(double wt, double profit){
		this.wt = wt;
		this.profit = profit;
	}
}

public class FractionalKnapsack {
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of items");
		int n = sc.nextInt();
		Pair[] arr = new Pair[n];
		
		for(int i=0; i<n; i++) {
			System.out.println("Enter weight and profit for item "+(i+1));
			double wt = sc.nextDouble();
			double pro = sc.nextDouble();
			Pair p = new Pair(wt, pro);
			arr[i] = p;
		}
		
		System.out.println("Enter knapsack capacity");
		int k = sc.nextInt();
		
		// sort in decreasing order of profit/wt
		Arrays.sort(arr, (a,b) -> {
			double cpa = a.profit / a.wt;
			double cpb = b.profit / b.wt;
			
			if(cpa > cpb) return -1;
			return 1;
		});
		
		for(int i=0; i<n; i++) {
			System.out.println(arr[i].wt + " " + arr[i].profit);
		}
		
		// fractional
		int i=0;
		double total_profit = 0;
		while( k>0 && i<n ) {
			if(arr[i].wt <= k) {
				total_profit += arr[i].profit;
				k -= arr[i].wt;
			}else {
				total_profit += (arr[i].profit / arr[i].wt) * k;
				k = 0;
			}
			i++;
		}
		
		System.out.println("Maximum profit for knapsack is = "+total_profit);
	}

}
