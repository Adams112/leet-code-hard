package com.jzq.leetcodehard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hard37 {

	public static void main(String[] args) {

	}
	
	class Solution {
	    public void solveSudoku(char[][] board) {
	        Node[][] b = new Node[board.length][board[0].length];
	        List<Character> l = Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9');
	        for (int i = 0; i < board.length; i++) {
	        	for (int j = 0; j < board[0].length; j++) {
	        		if (board[i][j] == '.') {
	        			b[i][j] = new Node(new ArrayList<Character>(l));
	        		} else {
	        			b[i][j] = new Node(board[i][j]);
	        		}
	        	}
	        }
	        
	    }
	    
	    private boolean solveSudoku0(Node[][] b) {
	    	boolean needTraceBack = true;
	    	for (;;) {
	    		needTraceBack = true;
	    		int uniqueCount = 0;
	    		for (int i = 0; i < b.length; i++) {
	    			for (int j = 0; j < b[0].length; j++) {
	    				if (b[i][j].unique) {
	    					uniqueCount++;
	    					continue;
	    				}
	    				
	    				remove9(b, i, j);
	    				removeCol(b, i, j);
	    				removeRow(b, i, j);
	    				
	    				if (b[i][j].list.size() == 1) {
	    					b[i][j] = new Node(b[i][j].list.get(0));
	    					uniqueCount++;
	    					needTraceBack = false;
	    				} else if (b[i][j].list.size() == 0)
	    					return false;
	    			}
	    		}
	    		
	    		if (uniqueCount == 81) return true;
	    		
	    		if (needTraceBack) {
	    			int i = 0, j = 0;
	    			outer: for (; i < b.length; i++) {
		    			for (; j < b[0].length; j++) {
		    				if (!b[i][j].unique) break outer;
		    			}
	    			}
	    			
	    			List<Character> list = b[i][j].list;
	    			
	    			for (Character c: list) {
	    				b[i][j] = new Node(c);
	    			}
	    		}
	    	}
	    }
	    
	    
	    
	    private void remove9(Node[][] b, int i, int j) {
	    	int r = (i / 3) * 3, c = (j / 3) * 3;
	    	List<Character> list = b[i][j].list;
	    	for (int p = 0; p < 3; p++) {
	    		for (int q = 0; q < 3; q++) {
	    			Node n = b[r + p][c + q];
	    			if (n.unique) {
	    				list.remove((Character) n.val);
	    			}
	    		}
	    	}
	    }
	    
	    private void removeRow(Node[][] b, int i, int j) {
	    	List<Character> list = b[i][j].list;
	    	for (int p = 0; p < 9; p++) {
	    		Node n = b[i][p];
	    		if (n.unique)
	    			list.remove((Character) n.val);
	    	}
	    }
	    
	    private void removeCol(Node[][] b, int i, int j) {
	    	List<Character> list = b[i][j].list;
	    	for (int p = 0; p < 9; p++) {
	    		Node n = b[p][j];
	    		if (n.unique)
	    			list.remove((Character) n.val);
	    	}
	    }
	}
	
	static class Node {
		boolean unique;
		char val;
		List<Character> list;
		Node(char val) {unique = true; this.val = val;}
		Node(List<Character> list) {unique = false; this.list = list;}
	}

}
