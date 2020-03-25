package com.jzq.leetcodehard;

import java.util.Arrays;

public class Hard123 {

	public static void main(String[] args) {
		Solution so = new Hard123().new Solution();
		System.out.println(so.maxProfit(new int[] {3,3,5,0,0,3,1,4}));
		System.out.println(so.maxProfit(new int[] {1,2,3,4,5}));
		System.out.println(so.maxProfit(new int[] {7,6,4,3,1}));
	}
	
	class Solution {
	    public int maxProfit(int[] prices) {
	    	if (prices == null || prices.length < 2) return 0;
	    	
	    	int[] p1 = new int[prices.length], p2 = new int[prices.length];
	    	int start = prices[0], end = prices[prices.length - 1];
	    	p1[0] = 0;
	    	p2[prices.length - 1] = 0;
	    	
	    	for (int i = 1; i < prices.length; i++) {
	    		p1[i] = p1[i - 1];
	    		if (prices[i] < start) start = prices[i];
	    		else if (prices[i] - start > p1[i - 1]) p1[i] = prices[i] - start;
	    	}
	    	
	    	for (int j = prices.length - 2; j >= 0; j--) {
	    		p2[j] = p2[j + 1];
	    		if (prices[j] > end) end = prices[j];
	    		else if (end - prices[j] > p2[j + 1]) p2[j] = end - prices[j];
	    	}
	    	
	    	int profit = Integer.max(p1[p1.length - 1], p2[0]);
	    	for (int i = 1; i < prices.length - 1; i++)
	    		profit = Integer.max(profit, p1[i] + p2[i + 1]);
	    	
	    	return profit;
	    }
	}
}
