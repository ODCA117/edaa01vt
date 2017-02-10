package bst;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
    int size;
    
	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		root = null;
		size = 0;
	}
	
	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x){
		int tempSize = size;
		
		root = add(root, x);
		
		if(tempSize == size)
			return false;
		else 
			return true;
		
	}
	
	private BinaryNode<E> add(BinaryNode<E> root, E x){
		if(root == null){
			root = new BinaryNode<E>(x);
			size++;
			return root;
		}
		
		else if(x.compareTo(root.element) == 0){
			return root;
		}
		else if(x.compareTo(root.element) < 0){
			root.left = add(root.left, x);
			return root;
		}
		else{
			root.right = add(root.right, x);
			return root;
		}
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	
	private int height(BinaryNode<E> root){
		if(root == null){
			return 0;
		}else{
			int left = 1 + height(root.left);
			int right = 1 + height(root.right);
			
			if(left >= right){
				return left;
			}else{
				return right;				
			}
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}
	
	private void printTree(BinaryNode<E> root){
		if (root != null) {
			printTree(root.left);
			System.out.print(root.element + ", ");
			printTree(root.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		
		E[] a = (E[]) new Comparable[size];
		int index = 0;
		toArray(root, a, index);
		root = null;
		size = 0;
		root = buildTree(a, 0, a.length - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a
	 * starting at a[index].
	 * Returns the index of the last inserted element + 1 (the first empty
	 * position in a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if(n.left != null){
			index = toArray(n.left, a, index);
		}
		a[index] = n.element;
		index ++;
		
		if(n.right != null){
			index = toArray(n.right, a, index);
		}
		
		return index;
	}
	
	/*
	 * Builds a complete tree from the elements a[first]..a[last].
	 * Elements in the array a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		int mid = (first + last)/2;
		
		add(a[mid]);
		if(last - first > 0){
			buildTree(a, first, mid-1);
			buildTree(a, mid+1, last);
			
		}
		return root;
		
		
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
