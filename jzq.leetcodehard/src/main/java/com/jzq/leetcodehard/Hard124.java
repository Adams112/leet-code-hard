package com.jzq.leetcodehard;

public class Hard124 {

	public static void main(String[] args) {

	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	class Solution {
		// 递归
		public int maxPathSum(TreeNode root) {
			int[] max = new int[] {root.val};
			maxGain(root, max);
			return max[0];
		}

		private int maxGain(TreeNode root, int[] max) {
			if (root == null)
				return 0;
			int left = maxGain(root.left, max), right = maxGain(root.right, max);
			int m = root.val;
			if (left > 0) m += left;
			if (right > 0) m += right;
			max[0] = Integer.max(m, max[0]);
			return Integer.max(Integer.max(left, right), 0) + root.val;
		}
	}

}
