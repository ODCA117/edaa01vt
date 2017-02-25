package filesplace;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Lab {

	public static void main(String[] args) {
		Scanner sc = null;
		try {
			sc = new Scanner(new File(args[0]));
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
		LinkedList<Integer> nbr = new LinkedList<Integer>();
		
		while(sc.hasNextLine())
			nbr.add(Integer.parseInt(sc.nextLine()));
		
		File f = new File(args[1]);
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(f);
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		pw.println("Test,Time");
		
		for(int i = 0; i < Integer.parseInt(args[2]); i++){
			LinkedList<Integer> cpNbr = (LinkedList<Integer>) nbr.clone();
			
			Long time = System.nanoTime();
			Collections.sort(cpNbr);
			time = System.nanoTime() - time;
			
			pw.println((i + 1) + "," + time);
			
			
		}
		pw.close();

	}
	
	

}
