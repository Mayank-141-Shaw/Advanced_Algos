import java.util.Scanner;

public class ZeroOneKnapsack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter number of items");
		int n = sc.nextInt();
		
		System.out.println("Enter knapsack capacity");
		int k = sc.nextInt();
		
		int[] val = new int[n];
		int[] wt = new int[n];
		
		for(int i=0; i<n; i++) {
			System.out.println("Enter value and weight for item "+(i+1));
			val[i] = sc.nextInt();
			wt[i] = sc.nextInt();
		}
		
		// by dynamic programming
		// auto fill dp by 0
		int[][] dp = new int[n+1][k+1];
		
		for(int i=1; i<=n; i++) {
			for(int j=1; j<=k; j++) {
				if( j-wt[i-1] >= 0 ) {
					dp[i][j] = Math.max(dp[i][j], val[i-1] + dp[i-1][j-wt[i-1]]);
				}
				else {
					dp[i][j] = dp[i-1][j];
				}
			}
		}
		
		System.out.println("Maximum profit = "+dp[n][k]);
		
	}

}
