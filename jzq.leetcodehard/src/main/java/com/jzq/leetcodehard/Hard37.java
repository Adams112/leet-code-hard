package com.jzq.leetcodehard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Hard37 {

	public static void main(String[] args) {
			char[][] ch = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
			new Hard37().new Solution().solveSudoku(ch);
	}
	
	class Solution {
		List<Integer> list = Arrays.asList(~1, ~2, ~4, ~8, ~16, ~32, ~64, ~128, ~256);
	    public void solveSudoku(char[][] board) {
	        int[][] b = new int[9][9];
	        int min = Integer.MIN_VALUE, all = min | 511;
	        for (int i = 0; i < 9; i++) {
	        	for (int j = 0; j < 9; j++) {
	        		if (board[i][j] == '.')
	        			b[i][j] = all;
	        		else
	        			b[i][j] = board[i][j] - '1';
	        	}
	        }
	        
	        if (soleve0(b)) {
	        	for (int i = 0; i < 9; i++) {
	        		for (int j = 0; j < 9; j++) {
	        			board[i][j] = (char) (b[i][j] + '1');
	        		}
	        	}
	        }
	    }
	    
	    private boolean soleve0(int[][] b) {
	    	
	    	for (;;) {
	    		boolean needTraceBack = true;
	    		int cnt = 0;
		    	for (int i = 0; i < 9; i++) {
		    		for (int j = 0; j < 9; j++) {
		    			if (b[i][j] >= 0) cnt++;
		    			else {
		    				int r = (i / 3) * 3, c = (j / 3) * 3;
		    				for (int p = 0; p < 3; p++) {
		    					for (int q = 0; q < 3; q++) {
		    						if (b[r + p][c + q] > 0) {
		    							b[i][j] &= list.get(b[r + p][c + q]);
		    						}
		    					}
		    				}
		    				for (int p = 0; p < 9; p++) {
		    					if (b[i][p] >= 0) {
		    						b[i][j] &= list.get(b[i][p]);
		    					}
		    					if (b[p][j] >= 0) {
		    						b[i][j] &= list.get(b[p][j]);
		    					}
		    				}
		    				if (b[i][j] == Integer.MIN_VALUE) return false;
		    				
		    				int index = list.indexOf(~(b[i][j] & Integer.MAX_VALUE));
		    				if (index >= 0) {
		    					cnt++;
		    					b[i][j] = index;
		    					needTraceBack = false;
		    				}
		    			}
		    		}
		    	}
		    	
		    	if (cnt == 81) return true;
		    	if (needTraceBack) break;
	    	}
	    	
	    	int[][] tmp = new int[9][9];
	    	for (int i = 0; i < 9; i++) {
	    		for (int j = 0; j < 9; j++) {
	    			tmp[i][j] = b[i][j];
	    		}
	    	}
	    	
	    	int ti = 0, tj = 0, possibleCnt = 10;
	    	for (int p = 0; p < 9; p++) {
	    		for (int q = 0; q < 9; q++) {
	    			if (b[p][q] < 0) {
	    				int pcnt = 0, bit = 1;
	    				for (int i = 0; i < 9; i++) {
	    					if ((b[p][q] & bit) > 0) {
	    						pcnt++;
	    					}
	    					bit <<= 1;
	    				}
	    				if (pcnt < possibleCnt) {
	    					ti = p;
	    					tj = q;
	    					possibleCnt = pcnt;
	    				}
	    			}
	    		}
	    	}
	    	
	    	int possibleValue = b[ti][tj] & Integer.MAX_VALUE;
	    	for (int i = 0; i < 9; i++) {
	    		if ((possibleValue & (1 << i)) != 0) {
	    			b[ti][tj] = i;
	    			if (soleve0(b)) return true;
	    			
	    			for (int p = 0; p < 9; p++) {
	    				for (int q = 0; q < 9; q++) {
	    					b[p][q] = tmp[p][q];
	    				}
	    			}
	    		}
	    	}
	    	
	    	return false;
	    }
	}
}