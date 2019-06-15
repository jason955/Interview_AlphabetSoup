package com.jbarrett.alphabet;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for the AlphabetSoup application 
 * Pass this main method a single argument
 * to represent the file name the user wishes to solve
 */
public class AlphabetSoup {

	/**
	 * Read in input from cmd line and parse
	 * Then solve filled WordSearch object by checking
	 * horizontally, vertically, and diagonally
	 * @param args
	 */
	public static void main( String[] args ) {

		if (args.length < 1) {
			System.err.println("Please provide an input file!");
			System.exit(0);
		}
		
		AlphabetSoup app = new AlphabetSoup();
		WordSearch ws = new WordSearch();
		
		try {
			ws = app.handleUpload(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
			e.printStackTrace();
		}
		
		//String finalOutput = app.solveWordSearch(ws);
		
	}
	
	/**
	 * Take name of txt file to be parsed for word game
	 * @param fileName of txt to be parsed
	 * @return Robust WordSearch object
	 * @throws FileNotFoundException
	 */
	public WordSearch handleUpload(String fileName) throws FileNotFoundException {

		File file = new File(fileName); 
	    Scanner sc = new Scanner(file); 
	  
	    int rowCounter = 0;
	    WordSearch ws = new WordSearch();
	    Grid grid = new Grid();
	    
	    while (sc.hasNextLine()) {
	    	
	    	String line = sc.nextLine();
	    	int row = grid.getRow();
	    	int column = grid.getColumn();
	    	int counterLimit = row + 1;
	    	
	    	if (rowCounter == 0) {	    		
	    		String delim = "x";
	    		String[] rc = line.split(delim);
	    		grid.setRow(Integer.parseInt(rc[0]));
	    		grid.setColumn(Integer.parseInt(rc[1]));	    		
	    	}
	    	else if (rowCounter < counterLimit) {
	    		String delim = " ";
	    		String[] gridLine = line.split(delim);
	    		ArrayList<String[]> tempGrid = grid.getGrid(); //get current grid
	    		tempGrid.add(gridLine);
	    		grid.setGrid(tempGrid);
	    	}
	    	else {
	    		String val = line;
	    		ArrayList<String> tempVal = new ArrayList<String>();
				for(char c: val.toCharArray()){
					String v = String.valueOf(c);
					if (!v.equals(" ")) { //rogue spaces in input file
						tempVal.add(String.valueOf(c));
					}
				}
				Key tempKey = new Key();
				tempKey.setKey(val);
				tempKey.setKeyArr(tempVal);
				ws.addToKeyArr(tempKey);
	    	}
	    	
	    	rowCounter++;
	    	
	    }
	    
	    ws.setGrid(grid);
		return ws;
	}
	
	
}
