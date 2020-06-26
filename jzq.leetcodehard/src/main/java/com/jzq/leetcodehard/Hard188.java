package com.jzq.leetcodehard;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Hard188 {

	public static void main(String[] args) {
//		test(2, new int[] { 2, 4, 1 });
//		test(2, new int[] { 3, 2, 6, 5, 0, 3 });
		test(2, new int[] { 2, 1, 2, 0, 1 });
	}

	private static void test(int k, int[] prices) {
		System.out.println(new Hard188().new Solution().maxProfit(k, prices));
	}

	class Solution {
		class Node {
			int min;
			int max;

			Node(int min, int max) {
				this.min = min;
				this.max = max;
			}

			@Override
			public String toString() {
				return "Node [min=" + min + ", max=" + max + "]";
			}

		}

		public int maxProfit(int k, int[] prices) {
			int index = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			HashMap<Integer, Node> map = new HashMap<Integer, Node>();
			int gain = 0;
			for (int i = 0; i < prices.length; i++) {
				if (min == Integer.MAX_VALUE)
					min = prices[i];
				else if (prices[i] <= min && max == Integer.MIN_VALUE)
					min = prices[i];
				else if (max < prices[i])
					max = prices[i];
				else {
					map.put(index++, new Node(min, max));
					gain += max - min;
					min = prices[i];
					max = Integer.MIN_VALUE;
				}
			}
			if (max > min) {
				map.put(index++, new Node(min, max));
				gain += max - min;
			}

			if (map.size() <= k)
				return gain;

			PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
			for (int i = 0; i < index - 1; i++) {
				Node n1 = map.get(i), n2 = map.get(i + 1);
				int g1 = n1.max - n1.min + n2.max - n2.min;
				int g2 = n1.max - n1.min;
				if (n2.max - n2.min > g2)
					g2 = n2.max - n2.min;
				if (n2.max - n1.min > g2)
					g2 = n2.max - n1.min;
				queue.add(g1 - g2);
			}
			int cnt = map.size() - k;
			while (cnt-- > 0)
				gain -= queue.poll();

			return gain;
		}
	}

}
