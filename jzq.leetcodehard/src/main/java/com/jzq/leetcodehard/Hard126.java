package com.jzq.leetcodehard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Hard126 {

	public static void main(String[] args) {
//		test("hit", "cog", new ArrayList<String>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));
//		test("hit", "cog", new ArrayList<String>(Arrays.asList("hot", "dot", "dog", "lot", "log")));
//		test("hot", "dog", new ArrayList<String>(Arrays.asList("hot", "dog", "dot")));
//		test("hot", "dog", new ArrayList<String>(Arrays.asList("hot", "dog")));
//		List<String> list = new ArrayList<String>(Arrays.asList("si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"));
//		test("qa", "sq", list);
		test("a", "c", new ArrayList<String>(Arrays.asList("a", "b", "c")));
	}

	public static void test(String beginWord, String endWord, List<String> wordList) {
		System.out.println(new Hard126().new Solution().findLadders(beginWord, endWord, wordList));
	}

	class Solution {
		class Node {
			String word;
			List<Node> parent;

			Node(String word) {
				this.word = word;
				parent = new ArrayList<Node>();
			}
		}

		// 双向搜索
		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
			List<List<String>> result = new ArrayList<List<String>>();
			if (wordList == null || !wordList.contains(endWord))
				return result;

			Map<String, List<String>> tree = new HashMap<String, List<String>>();
			Set<String> curLevel1 = new HashSet<String>(), curLevel2 = new HashSet<String>();
			Set<String> nextLevel1 = new HashSet<String>(), nextLevel2 = new HashSet<String>();
			curLevel1.add(beginWord);
			curLevel2.add(endWord);

			Set<String> words1 = new HashSet<String>(wordList), words2 = new HashSet<String>(wordList);
			Set<String> found1 = new HashSet<String>(), found2 = new HashSet<String>();
			words1.remove(endWord);
			words2.remove(endWord);

			Set<String> union = null;

			while (true) {
				Set<String> curLevel, nextLevel, words, found;
				boolean reverse;
				if (curLevel1.size() < curLevel2.size()) {
					curLevel = curLevel1;
					nextLevel = nextLevel1;
					words = words1;
					found = found1;
					reverse = false;
				} else {
					curLevel = curLevel2;
					nextLevel = nextLevel2;
					words = words2;
					found = found2;
					reverse = true;
				}

				for (String parent : curLevel) {
					for (String word : words) {
						int differentCount = 0;
						for (int i = 0; i < parent.length(); i++) {
							if (parent.charAt(i) != word.charAt(i))
								differentCount++;
						}
						if (differentCount != 1)
							continue;

						found.add(word);
						nextLevel.add(word);

						if (reverse) {
							List<String> list = tree.get(parent);
							if (list != null)
								list.add(word);
							else {
								list = new ArrayList<String>();
								list.add(word);
								tree.put(parent, list);
							}
						} else {
							List<String> list = tree.get(word);
							if (list != null)
								list.add(parent);
							else {
								list = new ArrayList<String>();
								list.add(parent);
								tree.put(word, list);
							}
						}
					}
				}

				if (found.isEmpty())
					return result;

				words.removeAll(found);
				found.clear();
				curLevel.clear();
				curLevel.addAll(nextLevel);
				nextLevel.clear();

				union = union(curLevel1, curLevel2);
				if (!union.isEmpty())
					break;
			}
			find(beginWord, endWord, new ArrayDeque<String>(), result, tree);
			return result;
		}

		private void find(String beginWord, String endWord, Deque<String> deque, List<List<String>> result,
				Map<String, List<String>> tree) {
			deque.addFirst(endWord);
			if (beginWord == endWord) {
				result.add(new ArrayList<String>(deque));
				deque.pollFirst();
				return;
			}
			
			
			
			List<String> list = tree.get(endWord);
			if (list != null) {
				for (String n: list) {
					find(beginWord, n, deque, result, tree);
				}
				deque.pollFirst();
			}

		}

		private Set<String> union(Set<String> set1, Set<String> set2) {
			Set<String> res = new HashSet<String>();
			if (set1.size() < set2.size()) {
				for (String s : set1)
					if (set2.contains(s))
						res.add(s);
			} else {
				for (String s : set2)
					if (set1.contains(s))
						res.add(s);
			}
			return res;
		}

		// 按层搜索，反向指针指向父节点
		// 1707ms, 5.07%
		// 42.6MB, 80.33%
		public List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
			List<List<String>> result = new ArrayList<List<String>>();
			if (wordList == null || !wordList.contains(endWord))
				return result;

			Node root = new Node(beginWord);
			Node dst = null;
			HashMap<String, Node> nodes = new HashMap<String, Node>();
			Set<Node> curLevel = new HashSet<Node>(), nextLevel = new HashSet<Node>();
			curLevel.add(root);
			Set<String> words = new HashSet<String>(wordList);
			Set<String> found = new HashSet<String>();

			while (dst == null) {
				for (Node node : curLevel) {
					for (String word : words) {
						Node cur = nodes.get(word);
						if (cur == null) {
							cur = new Node(word);
							nodes.put(word, cur);
						}

						int differentCount = 0;
						for (int i = 0; i < node.word.length(); i++) {
							if (node.word.charAt(i) != cur.word.charAt(i))
								differentCount++;
						}
						if (differentCount != 1)
							continue;

						found.add(cur.word);
						cur.parent.add(node);
						nextLevel.add(cur);

						if (dst == null && cur.word.equals(endWord))
							dst = cur;
					}
				}
				if (found.isEmpty())
					break;

				words.removeAll(found);
				found.clear();

				curLevel = nextLevel;
				nextLevel = new HashSet<Node>();
			}

			if (dst == null)
				return result;

			find(root, dst, new ArrayDeque<String>(), result);
			return result;

		}

		private void find(Node root, Node dst, Deque<String> deque, List<List<String>> result) {
			deque.addFirst(dst.word);
			if (root == dst) {
				result.add(new ArrayList<String>(deque));
				deque.pollFirst();
				return;
			}

			for (Node n : dst.parent) {
				find(root, n, deque, result);
			}
			deque.pollFirst();

		}

	}

}
