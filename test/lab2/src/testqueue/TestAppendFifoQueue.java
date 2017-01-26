package testqueue;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import queue.FifoQueue;

public class TestAppendFifoQueue {
	
	FifoQueue<Integer> q1;
	FifoQueue<Integer> q2;
	
	@Before
	public void setUp() throws Exception {
		q1 = new FifoQueue<Integer>();
		q2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		q1 = null;
		q2 = null;
	}

	@Test
	public final void testtwoEmptyQueues() {
		q1.append(q2);
		assertTrue("Value", q1.poll() == null);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public final void testSameQueue(){
		q1.append(q1);
	}
	
	@Test
	public final void testfirstEmptyQueue() {
		q2.offer(1);
		q2.offer(2);
		q2.offer(3);
		
		q1.append(q2);
		for(int i = 0; i < q1.size(); i++){
			assertTrue("Value", q1.poll() == i + 1);
		}
		
	}
	
	@Test
	public final void testSecondEmptyQueue() {
		q1.offer(1);
		q1.offer(2);
		q1.offer(3);
		
		q1.append(q2);
		for(int i = 0; i < q1.size(); i++){
			assertTrue("Value", q1.poll() == i + 1);
		}
		
	}
	
	@Test
	public final void testnonEmptyQueue() {
		q1.offer(1);
		q1.offer(2);
		q2.offer(3);
		
		q1.append(q2);
		for(int i = 0; i < q1.size(); i++){
			assertTrue("Value", q1.poll() == i + 1);
		}
		
	}

}
