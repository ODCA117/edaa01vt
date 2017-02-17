package map;

public class Main {

	public static void main(String[] args) {
		SimpleHashMap<Integer,Integer> shm = new SimpleHashMap<>(10);
		
		for(int i = 1; i <= 15; i++){
			shm.put(i, i*3);
			shm.put(-i*3, -i*3);
		}
		System.out.println(shm.show());
		
		

	}

}
