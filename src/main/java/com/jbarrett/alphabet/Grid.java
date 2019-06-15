package com.jbarrett.alphabet;

import java.util.ArrayList;

public class Grid {
	
	private ArrayList<String[]> grid;
	private int row;
	private int column;
	
	public Grid() {
		this.grid = new ArrayList<String[]>();
		this.row = 0;
		this.column = 0;
	}
	
	public Grid(ArrayList<String[]> grid, int row, int column) {
		this.grid = grid;
		this.row = row;
		this.column = column;
	}
	
	public Grid(Grid g) {
		this.grid = g.grid;
		this.row = g.row;
		this.column = g.column;
	}
	
	public ArrayList<String[]> getGrid() {
		return grid;
	}

	public void setGrid(ArrayList<String[]> grid) {
		this.grid = grid;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

}
