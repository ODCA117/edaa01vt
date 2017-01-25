package set;

public class RemoveDuplicates {

	public static void main(String[] args) {
		int[] xs = {7,5,3,5,2,2,7};
		int[] ys = uniqueElements(xs);
		for(int i: ys){
			System.out.println(i);
		}
	}
	
	public static int[] uniqueElements(int[] ints){
		MaxSet<Integer> ue = new MaxSet();
		for(int i : ints){
			ue.add(i);
		}
		int[] newInts = new int[ue.size()];
		for(int i = ue.size()-1; i >= 0; i--){
			newInts[i] = ue.getMax();
			ue.remove(ue.getMax());
		}
		return newInts;
	}

}
