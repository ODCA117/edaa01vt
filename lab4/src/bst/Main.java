package bst;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		bst.add(1);
		bst.add(2);
		bst.add(3);
		bst.add(4);
		bst.add(5);
		bst.add(0);
		bst.add(-2);
		bst.add(6);
		bst.add(7);
		bst.add(0);
		
		bst.rebuild();
		
		BSTVisualizer bstv = new BSTVisualizer("tree", 500, 500);
		bstv.drawTree(bst);
		bst.printTree();
		
	}

}
