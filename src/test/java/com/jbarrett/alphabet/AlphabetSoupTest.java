package com.jbarrett.alphabet;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Before;
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
}
