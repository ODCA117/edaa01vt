package sort;
import static org.junit.Assert.assertTrue;

import queue.FifoQueue;


public class RadixSort {
	public static void radixSort(int[] a, int maxNbrOfDigits) {
	
		FifoQueue<Integer> numbers = new FifoQueue<Integer>();
		FifoQueue<Integer>[] queues = 
			(FifoQueue<Integer>[]) new FifoQueue[10];
		
		for (int i: a) {
			numbers.add(i);
		}

		for (int i = 0; i < 10; i++) {
			queues[i] = new FifoQueue<Integer>();
		}
		// sort ...	
		
		for (int i = 1; i <= maxNbrOfDigits; i++){
			
			while (!numbers.isEmpty()){
				int element = numbers.poll();
				int p = i - 1;
				
				
				int j = (int) (element / Math.pow(10.0, p)) % 10;
				
				queues[j].offer(element);
								
			}
			
			for(int j = 0; j < 10; j++){
				numbers.append(queues[j]);
			}
		}
		
		//System.out.println(numbers.size());
		
		for (int i = 0; i < a.length; i++){
			a[i] = numbers.poll();
		}
		
	}
	
	public static void main(String args[]){
		
		int[] a = { 10, 2, 5, 7, 4, 9, 8, 6, 1, 3 };
			
		RadixSort.radixSort(a, 2);
		for(int i: a){
			System.out.println(i);
		}
		
		
		
//		for(int i: a){
//			System.out.println(i);
//		}
		
		
	}
}
