package com.jbarrett.alphabet;

import java.util.ArrayList;

public class Key {
	private String key;
	private ArrayList<String> keyArr;
	private ArrayList<String[]> output;
	
	public Key() {
		this.key = new String();
		this.keyArr = new ArrayList<String>();
		this.output = new ArrayList<String[]>();
	}
	
	public Key(String key, ArrayList<String> keyArr,  ArrayList<String[]> output) {
		this.key = key;
		this.keyArr = keyArr;
		this.output = output;
	}
	
	public Key(Key k) {
		this.key = k.key;
		this.keyArr = k.keyArr;
		this.output = k.output;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ArrayList<String> getKeyArr() {
		return keyArr;
	}

	public void setKeyArr(ArrayList<String> keyArr) {
		this.keyArr = keyArr;
	}

	public ArrayList<String[]> getOutput() {
		return output;
	}

	public void setOutput(ArrayList<String[]> output) {
		this.output = output;
	}
	
	public String toString() {
		
		String val = this.getKey();
		String ret = "";
		ArrayList<String[]> output = this.getOutput();
		
		for(String[] coords : output) {
			
			for (int i = 0; i < 2; i++) {
				String beg = coords[0]; //
				String end = coords[1];
				ret += val + " " + beg + " " + end + "\n";
			}
			
		}
		
		
		return ret;
		
	}
}
