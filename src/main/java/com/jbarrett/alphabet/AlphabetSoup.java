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
		
		System.out.println(app.solveWordSearch(ws));
		
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
	    		String delim = "x"; //find row/column vals
	    		String[] rc = line.split(delim);
	    		grid.setRow(Integer.parseInt(rc[0]));
	    		grid.setColumn(Integer.parseInt(rc[1]));	    		
	    	}
	    	else if (rowCounter < counterLimit) {
	    		String delim = " "; //find the word search values
	    		String[] gridLine = line.split(delim);
	    		ArrayList<String[]> tempGrid = grid.getGrid(); //get current grid
	    		tempGrid.add(gridLine);
	    		grid.setGrid(tempGrid);
	    	}
	    	else {
	    		String val = line; //find the keys
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
	
	/**
	 * Solve the word search and prep it for output
	 * @param ws is the WordSearch object to solve
	 * @return the results of the the word search formatted correctly
	 */
	public String solveWordSearch(WordSearch ws) {
		
		ArrayList<Key> keyList = ws.getKeys();
		Grid grid = ws.getGrid();
		ArrayList<String> outputs = new ArrayList<String>();
		String h = new String();
		String v = new String();
		String d = new String();

		for(Key k : keyList) {
			h = solveHorizontal(k, grid);
			if (h != null) {
				outputs.add(h);
			}
			else {
				v = solveVertical(k, grid);
				if (v != null) {
					outputs.add(v);
				}
				else {
					d = solveDiagonal(k, grid);
					if (d != null) {
						outputs.add(d);
					}
				}
					
			}
		}
		String ret = "";
		for(String o : outputs) {
			ret += o + " ";
		}
		
		return ret;
		
	}

	/**
	 * Helper method for solving the search horizontally.
	 * This will run through each row attempting to find the key
	 * both forwards and backwards
	 * @param k the key we are searching for 
	 * @param g the grid we are searching for the key in
	 * @return the correctly formatted output when found
	 */
	public String solveHorizontal(Key k, Grid g) {
		
		int rowCounter = 0;
		String kVal = k.getKey();
		ArrayList<String> keyArr = k.getKeyArr();
		ArrayList<String[]> grid = g.getGrid();

		for (String[] row : grid) {
			
			Integer[] forward = solveForwards(row, keyArr);
			if (forward.length > 0) {
				String beg = rowCounter + ":" + forward[0]; 
				String end = rowCounter + ":" + forward[1]; 
				String ret = kVal + " " + beg + " " + end;
				return ret;
			}
			else {
				Integer[] backwards = solveBackwards(row,keyArr);
				if (backwards.length > 0) {
					String beg = rowCounter + ":" + backwards[0]; 
					String end = rowCounter + ":" + backwards[1]; 
					String ret = kVal + " " + beg + " " + end;
					return ret;
				}
			}
			
			rowCounter++;
		}
		
		return null;
	}
	
	/**
	 * Helper method for solving the search Vertically.
	 * This will run through each row attempting reformatting it from row x column 
	 * to column x row allowing for the resuse of solveForwards and Backwards
	 * @param k the key we are searching for 
	 * @param g the grid we are searching for the key in
	 * @return the correctly formatted output when found
	 */
	public String solveVertical(Key k, Grid g) {
		
		int rowNum = g.getRow();
		int columnNum = g.getColumn();
		ArrayList<String[]> grid = g.getGrid();
		ArrayList<String> keyArr = k.getKeyArr();
		ArrayList<String[]> newRows = new ArrayList<String[]>();
		String kVal = k.getKey();

		for (int i = 0; i < columnNum; i++) { 
			String[] tempRow = new String[columnNum];
			
			for (int j = 0; j < rowNum; j++) {
				tempRow[j] = grid.get(j)[i];
			}
			newRows.add(tempRow);
		}
		
		int rowCounter = 0;
		for (String[] row : newRows) {
			
			Integer[] forward = solveForwards(row, keyArr);
			if (forward.length > 0) {
				String beg = forward[0] + ":" + rowCounter; //because matrix has been flipped output is formatted differently
				String end = forward[1] + ":" + rowCounter; 
				String ret = kVal + " " + beg + " " + end;
				return ret;
			}
			else {
				Integer[] backwards = solveBackwards(row,keyArr);
				if (backwards.length > 0) {
					String beg = forward[0] + ":" + rowCounter; 
					String end = forward[1] + ":" + rowCounter; 
					String ret = kVal + " " + beg + " " + end;
					return ret;
				}
			}
			rowCounter++;
		}
		
		return null;	
	}
	
	/**
	 * Helper method for solving the search diagonally.
	 * This will search each row letter by letter looking for either 
	 * the first or last character in the key. Upon finding it 
	 * begin "lookingDiagonally" by calling said method
	 * @param k the key we are searching for 
	 * @param g the grid we are searching for the key in
	 * @return the correctly formatted output when found
	 */
	public String solveDiagonal(Key k, Grid g) {
		
		int rowNum = g.getRow();
		int columnNum = g.getColumn();
		
		ArrayList<String[]> grid = g.getGrid();
		ArrayList<ArrayList<String>> newRows = new ArrayList<ArrayList<String>>();
		String kVal = k.getKey();
		ArrayList<String> keyArr = k.getKeyArr();
		Integer[] retArr = new Integer[2];
		
		for (int i = 0; i < rowNum; i++) { //each row
			
			ArrayList<String> tempRow = new ArrayList<String>();
			for (int j = 0; j < columnNum; j++) { //letter by letter
				
				String letter = grid.get(i)[j];
				
				if (letter.equals(keyArr.get(0))) {
					retArr = lookDiagonally(keyArr, g, i , j);
				}
				
			}
			newRows.add(tempRow);
		}	
		
		if (retArr != null) {
			String beg = retArr[0] + ":" + retArr[1]; 
			String end = retArr[2] + ":" + retArr[3]; 
			String ret = kVal + " " + beg + " " + end;
			return ret; 
		}
		else {
			return "Not Found";
		}
	}
	
	/**
	 * This is called when "solveDiagonal" finds the common character between 
	 * the grid and key. After finding this we step over the remaining rows 
	 * incrementing the column value by one to search in a diagonal manner.
	 * @param keyArr
	 * @param g
	 * @param row
	 * @param col
	 * @return
	 */
	public Integer[] lookDiagonally(ArrayList<String> keyArr, Grid g, int row, int col) {
		
		ArrayList<String[]> grid = g.getGrid();
		int keyHelper = 0;
		int endRow = 0;
		int endCol = 0;
		int startCol = col;
		
		for (int i = row; i < g.getRow(); i++) {
			
			String val = grid.get(i)[col];
			if (val.equals(keyArr.get(keyHelper))) {
				
				keyHelper++;
				if (keyHelper == keyArr.size()) {
					endRow = i;
					endCol = col;
				}
				
				col++;
				
			}
			else {
				return null;
			}
		}
		
		return new Integer[]{row, startCol, endRow, endCol};
	}
	
	/**
	 * Check each value of the keyArr with the row this fn has 
	 * been passed and report the vals of where it was found within row
	 * @param row the row of letters we're currently searching
	 * @param keyArr the key in array form
	 * @return
	 */
	public Integer[] solveForwards(String[] row, ArrayList<String> keyArr) {
		
		int keyCounter = 0;
		int startCoord = 0;
		int endCoord = 0;
		int keyLen = keyArr.size();
		boolean foundStart = false;
		boolean foundEnd = false;

		for (int i = 0; i < row.length; i ++) {
			
			String rowVal = row[i];
			String val = keyArr.get(keyCounter); //increment as we succeed
			
			if (val.equals(rowVal)) {
				
				keyCounter++;
				
				if (!foundStart) {
					startCoord = i;
					foundStart = true;
				}
				
				if (keyCounter == keyLen) {
					foundEnd = true;
					endCoord = i;
					break;
				}
			}
			else {
				
				if (foundStart) {
					i--;
				}
				
				keyCounter = 0;
				foundStart = false;
			}
		}
		
		if (!foundEnd) {
			return new Integer[0];
		}
		
		return new Integer[] {startCoord, endCoord};		
	}
	
	/**
	 * Check each value of the keyArr with the row this fn has 
	 * been passed and report the vals of where it was found within row	 
	 * @param row we are searching
	 * @param keyArr the key in array form
	 * @return the start and end 
	 */
	public Integer[] solveBackwards(String[] row, ArrayList<String> keyArr) {
		
		int keyCounter = 0;
		int startCoord = 0;
		int endCoord = 0;
		int keyLen = keyArr.size();
		boolean foundStart = false;
		boolean foundEnd = false;

		for (int i = row.length - 1; i >= 0; i--) {
			
			String rowVal = row[i];
			String val = keyArr.get(keyCounter);
			
			if (val.equals(rowVal)) {
				
				keyCounter++;
				
				if (!foundStart) {
					startCoord = i;
					foundStart = true;
				}
				
				if (keyCounter == keyLen) {
					foundEnd = true;
					endCoord = i;
					break;
				}
				
			}
			else {
				
				if (foundStart) {
					i++;
				}
				
				keyCounter = 0;
				foundStart = false;
			}
		}
		
		if (!foundEnd) {
			return new Integer[0];
		}
		
		return new Integer[] {startCoord, endCoord};
	}
}
