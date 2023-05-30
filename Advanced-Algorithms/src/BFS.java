import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class BFS {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Map<Integer, Set<Integer>> adj = new HashMap<>();
		
		// input is in form of adjoint matrix
		int n;
		System.out.println("Enter number of nodes");
		n = sc.nextInt();
	
		for(int i=0; i<n; i++) {
			adj.put(i, new HashSet<>());
		}
		
		
		System.out.println("Enter undirected graph");
		// input unordered graph
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				int hasPathFromItoJ = sc.nextInt();
				
				if(hasPathFromItoJ == 1) {
					adj.get(i).add(j);
					adj.get(j).add(i);
				}
			}
		}
		
		// do dfs and print
		ArrayList<Integer> res = new ArrayList<>();
		Set<Integer> visited = new HashSet<>();
		BFS bfs = new BFS();
		
		for(int i=0; i<n; i++) {
			if(!visited.contains(i)) {
				bfs.bfs(i, adj, visited, res);
			}
		}
		
		// print res
		System.out.println("BFS path");
		for(int node:res) {
			System.out.print(node + "->");
		}
		System.out.print("END\n");
		
	}
	
	void bfs(int cur, Map<Integer, Set<Integer>> adj, Set<Integer> vis, ArrayList<Integer> res) {
		Queue<Integer> q = new LinkedList<>();
		
		// start node
		q.add(cur);
		vis.add(cur);
		
		while(!q.isEmpty()) {
			int node = q.remove();
			// add to result while visiting the node
			res.add(node);
			
			for(int neighbor : adj.get(node)) {
				if(!vis.contains(neighbor)) {
					vis.add(neighbor);
					q.add(neighbor);
				}
			}
			
		}
		
	}

}
