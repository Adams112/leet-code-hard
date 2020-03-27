package com.jzq.leetcodehard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Hard145 {
	class Solution {
		public List<Integer> postorderTraversal(TreeNode root) {
			if (root == null)
				return new ArrayList<>();

			Deque<Integer> nums = new ArrayDeque<>();
			Deque<TreeNode> nodes = new ArrayDeque<>();
			nodes.push(root);
			while (!nodes.isEmpty()) {
				TreeNode node = nodes.pop();
				nums.push(node.val);

				if (node.left != null)
					nodes.push(node.left);
				if (node.right != null)
					nodes.push(node.right);
			}

			List<Integer> list = new ArrayList<>();
			while (!nums.isEmpty())
				list.add(nums.pop());
			return list;
		}

		class TreeNode {
			int val;
			TreeNode left;
			TreeNode right;

			TreeNode(int x) {
				val = x;
			}
		}
	}
}
