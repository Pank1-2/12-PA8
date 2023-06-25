import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class ElementFinder {
	
	private static Comparator<Integer> initMaxComparator() {
		return new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return i1 - i2;
			}
		};
	}
	
	private static Comparator<Integer> initMinComparator() {
		return new Comparator<Integer>() {
			public int compare(Integer i1, Integer i2) {
				return i2 - i1;
			}
		};
	}

	public static int Kth_finder(String filename, int K, String operation) {
		
		// Create a comparator depending upon the type of operation
		// Heap<Integer, Integer> heap = new Heap<Integer, Integer>(comparator);
		/** TODO **/
		Heap<Integer, String> pq;
		final boolean findLargest;
		
		if (operation.equals("largest")) {
			pq = new Heap<>(initMinComparator());
			findLargest = true;
		}
		
		else {
			pq = new Heap<>(initMaxComparator());
			findLargest = false;
		}
		
		try {
			File files = new File(filename);
			Scanner scan = new Scanner(files);
			while (scan.hasNextLine()) {
				String data[] = scan.nextLine().split(" ");
				for (String s : data) {
					if (findLargest) checkOnMaxPQ(pq, s, K);
					else {
						checkOnMinPQ(pq, s, K);
					}
					
				}
				
			}
		} 
		
		catch (FileNotFoundException err) {
			err.printStackTrace();
			return -1;
		}
		
		if (pq.isEmpty()) {
			return -1;
		}
		
		return pq.peek().getKey();
	}
	
	/* Add any helper methods you find useful */
	
	// helper methods helps check if the max queue has less than k elements
	// method insert into priority queue
	private static void checkOnMaxPQ(Heap<Integer, String> pq, String s, int k) {
		int num = Integer.parseInt(s);
		
		if (pq.size() < k) {
			pq.add(num, "");
		} 
		else if (num > pq.peek().getKey()) {
			pq.poll();
			pq.add(num, "");
		}
		
	}
	
	// helper methods helps check if the min queue has less than k elements
	// method insert into priority queue
	private static void checkOnMinPQ(Heap<Integer, String> pq, String s, int k) {
		int num = Integer.parseInt(s);
		
		if (pq.size() < k) {
			pq.add(num, "");
		} 
		
		else if (num < pq.peek().getKey()) {
			pq.poll();
			pq.add(num, "");
		}
	}
		
}