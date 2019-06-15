package com.jbarrett.alphabet;

import java.util.ArrayList;

public class WordSearch {
	private Grid grid;
	private ArrayList<Key> keys;
	
	public WordSearch() {
		this.grid = new Grid();
		this.keys = new ArrayList<Key>();
	}
	
	public WordSearch(Grid grid, ArrayList<Key> keys) {
		this.grid = grid;
		this.keys = keys;
	}
	
	public WordSearch(WordSearch ws) {
		this.grid = ws.grid;
		this.keys = ws.keys;
	}

	public Grid getGrid() {
		return grid;
	}

	public void setGrid(Grid grid) {
		this.grid = grid;
	}

	public ArrayList<Key> getKeys() {
		return keys;
	}

	public void setKeys(ArrayList<Key> keys) {
		this.keys = keys;
	}
	
	public void addToKeyArr(Key k) {
		this.getKeys().add(k);
	}
}
