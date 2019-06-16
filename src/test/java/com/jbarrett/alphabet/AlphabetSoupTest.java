package com.jbarrett.alphabet;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test the AlphabetSoup class by passing in varying 
 * input files and testing it's parsing output and its
 * solving ability
 * @author Jason
 *
 */
public class AlphabetSoupTest {
	
	private AlphabetSoup alphabetTest;
	
	@Before
	public void setUp() {
		alphabetTest = new AlphabetSoup();
	}
	
	/**
	 * test for solving the maze using the first input given
	 * @throws FileNotFoundException
	 */
	@Test
	public void testSolveSearch() throws FileNotFoundException {
		WordSearch ws = alphabetTest.handleUpload("input.txt");
		String ret = alphabetTest.solveWordSearch(ws);
		Assert.assertEquals("ABC 0:0 0:2 AEI 0:0 2:2 ", ret);
	}
	
	/**
	 * test for solving the maze using the second input given
	 * @throws FileNotFoundException
	 */
	@Test
	public void testSolveSearch2() throws FileNotFoundException {
		WordSearch ws = alphabetTest.handleUpload("input2.txt");
		String ret = alphabetTest.solveWordSearch(ws);
		Assert.assertEquals("HELLO 0:0 4:4 GOOD 4:0 4:3 BYE 1:3 1:1 FRY 1:4 3:4 ", ret);
	}
	
	/**
	 * Test the handleUpload function on input.txt
	 * @throws FileNotFoundException
	 */
	@Test
	public void testHandleUpload() throws FileNotFoundException {

		WordSearch ws = alphabetTest.handleUpload("input.txt");
		
		int row = ws.getGrid().getRow();
		String[] firstRow = ws.getGrid().getGrid().get(0);
		String firstKey = ws.getKeys().get(0).getKey();
		
		Assert.assertEquals(3, row);
		Assert.assertArrayEquals(firstRow, new String[] {"A", "B", "C"});
		Assert.assertEquals(firstKey, "ABC");
	}
	
	/**
	 * Test the handleUpload function on a larger input2.txt file
	 * @throws FileNotFoundException
	 */
	@Test
	public void testHandleUpload2() throws FileNotFoundException {

		WordSearch ws = alphabetTest.handleUpload("input2.txt");
		
		int row = ws.getGrid().getRow();
		String[] firstRow = ws.getGrid().getGrid().get(0);
		String firstKey = ws.getKeys().get(0).getKey();
		
		Assert.assertEquals(5, row);
		Assert.assertArrayEquals(firstRow, new String[] {"H", "A", "S", "D", "F"});
		Assert.assertEquals(firstKey, "HELLO");


	}
	
	/**
	 * test horizontally using ABC from input.txt
	 * @throws FileNotFoundException
	 */
	@Test
	public void testHorizontal() throws FileNotFoundException {
		WordSearch ws = alphabetTest.handleUpload("input.txt");
		String ret = alphabetTest.solveHorizontal(ws.getKeys().get(0), ws.getGrid());
		Assert.assertEquals("ABC 0:0 0:2", ret);

	}
	
	/**
	 * test vertically using FRY on input2.txt
	 * @throws FileNotFoundException
	 */
	@Test
	public void testVertical() throws FileNotFoundException {
		WordSearch ws = alphabetTest.handleUpload("input2.txt");
		String ret = alphabetTest.solveVertical(ws.getKeys().get(3), ws.getGrid());
		Assert.assertEquals("FRY 1:4 3:4", ret);

	}
	
	/**
	 * test diagonally 
	 * @throws FileNotFoundException
	 */
	@Test
	public void testDiagonal() throws FileNotFoundException {
		WordSearch ws = alphabetTest.handleUpload("input2.txt");
		String ret = alphabetTest.solveDiagonal(ws.getKeys().get(0), ws.getGrid());
		Assert.assertEquals("HELLO 0:0 4:4", ret);

	}
	
	/**
	 * Test the handleUpload function on a larger input2.txt file
	 * @throws FileNotFoundException
	 */
	@Test
	public void testForward() throws FileNotFoundException {

		String[] firstRow = new String[] {"H", "A", "S", "D", "F"};
		ArrayList<String> keyArr = new ArrayList<String>();
		keyArr.add("A");
		keyArr.add("S");
		keyArr.add("D");
		Integer[] ret = alphabetTest.solveForwards(firstRow, keyArr);
		int start = ret[0];
		int end = ret[1];
		
		Assert.assertEquals(1, start);
		Assert.assertEquals(3, end);

	}
	
	/**
	 * Test the handleUpload function on a larger input2.txt file
	 * @throws FileNotFoundException
	 */
	@Test
	public void testBackward() throws FileNotFoundException {

		String[] firstRow = new String[] {"H", "A", "S", "D", "F"};
		ArrayList<String> keyArr = new ArrayList<String>();
		keyArr.add("D");
		keyArr.add("S");
		keyArr.add("A");
		Integer[] ret = alphabetTest.solveBackwards(firstRow, keyArr);
		int start = ret[0];
		int end = ret[1];
		
		Assert.assertEquals(3, start);
		Assert.assertEquals(1, end);

	}
	
	/**
	 * Test the handleUpload function on a larger input2.txt file
	 * @throws FileNotFoundException
	 */
	@Test
	public void testForward2() throws FileNotFoundException {

		String[] firstRow = new String[] {"A", "A", "S", "D", "F"};
		ArrayList<String> keyArr = new ArrayList<String>();
		keyArr.add("A");
		keyArr.add("S");
		keyArr.add("D");
		Integer[] ret = alphabetTest.solveForwards(firstRow, keyArr);
		int start = ret[0];
		int end = ret[1];
		
		Assert.assertEquals(1, start);
		Assert.assertEquals(3, end);

	}
	
	/**
	 * Test the handleUpload function on a larger input2.txt file
	 * @throws FileNotFoundException
	 */
	@Test
	public void testBackward2() throws FileNotFoundException {

		String[] firstRow = new String[] {"H", "A", "S", "D", "D"};
		ArrayList<String> keyArr = new ArrayList<String>();
		keyArr.add("D");
		keyArr.add("S");
		keyArr.add("A");
		Integer[] ret = alphabetTest.solveBackwards(firstRow, keyArr);
		int start = ret[0];
		int end = ret[1];
		
		Assert.assertEquals(3, start);
		Assert.assertEquals(1, end);

	}
}
