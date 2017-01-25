package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import set.ArraySet;
import set.MaxSet;
import set.RemoveDuplicates;

public class TestUniqueElements {

	
	int[] ints;
	private RemoveDuplicates rd = new RemoveDuplicates();

	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void tearDown() throws Exception {
		ints = null;
	}
	public String printVec(int[] ints){
		StringBuilder sb = new StringBuilder("");
		for(int i: ints){
			sb.append(i + ",");
		}
		
		return sb.toString();
	}
	
	@Test
	public final void emptyVec() {
		ints = new int[0];
		ints = rd.uniqueElements(ints);
		
		assertEquals("can't send empty vector ", "", printVec(ints));
	}
	
	@Test
	public final void sameElements() {
		ints = new int[3];
		for(int i = 0; i < ints.length; i++){
			ints[i] = 4;
		}
		ints = rd.uniqueElements(ints);
		assertEquals("Nope", "4,", printVec(ints));
	}
	
	@Test
	public final void differentElements() {
		ints = new int[3];
		for(int i = 0; i < ints.length; i++){
			ints[i] = i;
		}
		ints = rd.uniqueElements(ints);
		assertEquals("Nope", "0,1,2,", printVec(ints));
	}
	
	@Test
	public final void largeArrray(){
		ints = new int[10000];
		for(int i = 0; i < ints.length; i++){
			ints[i] = i%3;
		}
		ints = rd.uniqueElements(ints);
		assertEquals("Nope", "0,1,2,", printVec(ints));
	}
	
	@Test
	public final void oneElement(){
		ints = new int[1];
		ints[0] = 0;
		ints = rd.uniqueElements(ints);
		assertEquals("Nope", "0,", printVec(ints));
	}

}
