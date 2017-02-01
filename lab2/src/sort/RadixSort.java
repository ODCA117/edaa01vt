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
			int countWhile = 0;
			
			while (!numbers.isEmpty()){
				int element = numbers.poll();
				int j = (int)(element/Math.pow(10, i-1)) % 10;
				
				queues[j].offer(element);
				
				System.out.println(countWhile);
				countWhile++;
			}
			
			for(int j = 0; j < 10; j++){
				numbers.append(queues[j]);
			}
			System.out.println(i);
		}
		
		System.out.println(numbers.size());
		
		for (int i = 0; i < a.length; i++){
			a[i] = numbers.poll();
		}
		
	}
	
//	public static void main(String args[]){
//		
//		int[] a = { 1 };
//		RadixSort.radixSort(a, 1);
//		
//	}
}
