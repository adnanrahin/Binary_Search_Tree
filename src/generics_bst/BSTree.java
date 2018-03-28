package generics_bst;

import java.util.LinkedList;
import java.util.Queue;

public class BSTree<T extends Comparable<T>> {

	private static class Tree<T> {
		T data;
		Tree<T> right, left;

		public Tree(T data) {
			this.data = data;
			left = right = null;
		}
	}

	private Tree<T> root;

	public void successor(T key) {
		try {
			Tree<T> scsr = successor(root, key);
			if (scsr != null)
				System.out.println("Successor of " + key + " is " + scsr.data);
		} catch (NullPointerException e) {
			System.out.println(e);
		}
	}

	public void add(T newData) {
		root = add(root, newData);
	}

	public void inorder() {
		inorder_traversal(root);
	}

	public void preorder() {
		preorder_traversal(root);
	}

	public void postorder() {
		postorder_traversal(root);
	}

	public void levelorder() {
		levelorder_traversal(root);
	}

	public int height() {
		return height(root);
	}

	public Tree<T> maximum() {
		return maximum(root);
	}

	public Tree<T> minimum() {
		return minimum(root);
	}

	public void deletenode(T key){
		root = deletenode(root, key);
	}
	
	public boolean search(T key) {
		Tree<T> fact = Tree_Search(root, key);
		if (fact == null) {
			return false;
		}
		return true;
	}

	private Tree<T> add(Tree<T> root, T newdata) {
		if (root == null) {
			root = new Tree<T>(newdata);
			return root;
		}
		int compare = newdata.compareTo(root.data);
		if (compare < 0)
			root.left = add(root.left, newdata);
		else
			root.right = add(root.right, newdata);

		return root;
	}

	private T minValue(Tree<T> root) {
		if(root.left == null) return root.data;
		return minValue(root.left);
	}
	
	private Tree<T> deletenode(Tree<T> root, T key){
		
		if(root == null) return root;
		
		int cmp = root.data.compareTo(key);
		
		if(cmp > 0) {
			root.left = deletenode(root.left, key);
		}
		else if (cmp < 0) {
			root.right = deletenode(root.right, key);
		}
		else {
			if(root.left == null) return root.right;
			else if(root.right == null) return root.left;
			root.data = minValue(root.right);
			root.right = deletenode(root.right, key);
		}		
		return root;
	}
	
	private void inorder_traversal(Tree<T> root) {
		if (root == null)
			return;
		inorder_traversal(root.left);
		System.out.println(root.data);
		inorder_traversal(root.right);
	}

	private void preorder_traversal(Tree<T> root) {
		if (root == null)
			return;
		System.out.println(root.data);
		preorder_traversal(root.left);
		preorder_traversal(root.right);
	}

	private void postorder_traversal(Tree<T> root) {
		if (root == null)
			return;
		postorder_traversal(root.left);
		postorder_traversal(root.right);
		System.out.println(root.data);
	}

	private int height(Tree<T> root) {
		if (root == null) {
			return 0;
		} else {
			int left = height(root.left);
			int right = height(root.right);
			if (left > right)
				return left + 1;
			else
				return right + 1;
		}
	}
	

	
	private void levelorder_traversal(Tree<T> root) {

		if (root == null) {
			return;
		}

		Queue<Tree<T>> q = new LinkedList<>();

		q.add(root);

		while (!q.isEmpty()) {

			while (q.size() > 0) {
				Tree<T> u = q.peek();
				System.out.println(u.data + " ");
				q.remove();
				if (u.left != null) {
					q.add(u.left);
				}
				if (u.right != null) {
					q.add(u.right);
				}
			}
			System.out.println();
		}
	}

	private Tree<T> Tree_Search(Tree<T> root, T key) {
		if (root == null || root.data == key) {
			return root;
		}
		int cmp = key.compareTo(root.data);
		if (cmp < 0)
			return Tree_Search(root.left, key);

		else
			return Tree_Search(root.right, key);
	}

	private Tree<T> minimum(Tree<T> root) {
		if (root.left == null) {
			System.out.println("Minimum Data: " + root.data);
			return root;
		}
		return minimum(root.left);
	}

	private Tree<T> maximum(Tree<T> root) {
		if (root.right == null) {
			System.out.println("Maximum Data: " + root.data);   
			return root;
		}
		return maximum(root.right);
	}

	private Tree<T> successor(Tree<T> root, T key) {
		Tree<T> newroot = Tree_Search(root, key);
		if (newroot == null) {
			return null;
		}
		if(newroot.right!=null) {
			return minimum(newroot.right);
		}else {
			Tree<T> scsr = null;
			Tree<T> anstr = root;
			while(anstr != newroot) {
				int cmp = newroot.data.compareTo(anstr.data);
				if(cmp < 0) {
					scsr = anstr;
					anstr = anstr.left;
				}else
					anstr = anstr.right;
			}
			return scsr;
		}
	}
}