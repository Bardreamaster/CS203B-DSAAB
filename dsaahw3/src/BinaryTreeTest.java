
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaryTreeTest {
	@Test
	public void test01() {
		BinaryTree binaryTree = new BinaryTree();
		TreeNode node0 = new TreeNode(3);
		binaryTree.root = node0;
		TreeNode node1 = new TreeNode(9);
		TreeNode node2 = new TreeNode(20);
		binaryTree.AddLeft(node0, node1);
		binaryTree.AddRight(node0, node2);
		TreeNode node3 = new TreeNode(15);
		TreeNode node4 = new TreeNode(7);
		binaryTree.AddLeft(node1, node3);
		binaryTree.AddRight(node1, node4);


		System.out.println("TraversalInOrder:"+ binaryTree.TraversalInOrder());
		assertEquals("15 9 7 3 20 ",binaryTree.TraversalInOrder());
		System.out.println("TraversalPreOrder:"+ binaryTree.TraversalPreOrder());
		assertEquals("3 9 15 7 20 ",binaryTree.TraversalPreOrder());
		System.out.println("TraversalPostOrder:"+ binaryTree.TraversalPostOrder());
		assertEquals("15 7 9 20 3 ",binaryTree.TraversalPostOrder());
		System.out.println("TraversalLevelOrder:"+ binaryTree.TraversalLevelOrder());
		assertEquals("[3 ][9 20 ][15 7 ]",binaryTree.TraversalLevelOrder());


		System.out.println(binaryTree.PreIn2Post(binaryTree.TraversalPreOrder(),binaryTree.TraversalInOrder()));
		assertEquals(binaryTree.TraversalPostOrder(),binaryTree.PreIn2Post(binaryTree.TraversalPreOrder(),binaryTree.TraversalInOrder()));


		System.out.println(binaryTree.InPost2Pre(binaryTree.TraversalInOrder(),binaryTree.TraversalPostOrder()));
		assertEquals(binaryTree.InPost2Pre(binaryTree.TraversalInOrder(),binaryTree.TraversalPostOrder()),binaryTree.TraversalPreOrder());



	}
	@Test
	public void test02() {

		BinaryTree binaryTree = new BinaryTree();
		TreeNode node0 = new TreeNode(12);
		binaryTree.root = node0;
		TreeNode node1 = new TreeNode(9);
		TreeNode node2 = new TreeNode(23);
		binaryTree.AddLeft(node0, node1);
		binaryTree.AddRight(node0, node2);
		TreeNode node3 = new TreeNode(44);
		TreeNode node4 = new TreeNode(1236);
		binaryTree.AddLeft(node1, node3);
		binaryTree.AddRight(node1, node4);
		TreeNode node5 = new TreeNode(1296);
		TreeNode node6 = new TreeNode(112);
		binaryTree.AddRight(node4, node5);
		binaryTree.AddRight(node4, node6);
		TreeNode node7 = new TreeNode(982);
		TreeNode node8 = new TreeNode(114514);
		binaryTree.AddRight(node3, node7);
		binaryTree.AddRight(node3, node8);
		TreeNode node9 = new TreeNode(173173);
		TreeNode node10 = new TreeNode(499706);
		binaryTree.AddRight(node2, node9);
		binaryTree.AddRight(node2, node10);


		System.out.println("TraversalInOrder:"+ binaryTree.TraversalInOrder());
		assertEquals("44 982 9 1236 1296 12 23 173173 ",binaryTree.TraversalInOrder());
		System.out.println("TraversalPreOrder:"+ binaryTree.TraversalPreOrder());
		assertEquals("12 9 44 982 1236 1296 23 173173 ",binaryTree.TraversalPreOrder());
		System.out.println("TraversalPostOrder:"+ binaryTree.TraversalPostOrder());
		assertEquals("982 44 1296 1236 9 173173 23 12 ",binaryTree.TraversalPostOrder());
		System.out.println("TraversalLevelOrder:"+ binaryTree.TraversalLevelOrder());
		assertEquals("[12 ][9 23 ][44 1236 173173 ][982 1296 ]",binaryTree.TraversalLevelOrder());




		System.out.println(binaryTree.PreIn2Post(binaryTree.TraversalPreOrder(),binaryTree.TraversalInOrder()));
		assertEquals("114514 44 112 1236 9 499706 23 12 ",binaryTree.PreIn2Post("12 9 44 114514 1236 112 23 499706 ","44 114514 9 1236 112 12 23 499706 "));


		System.out.println(binaryTree.InPost2Pre(binaryTree.TraversalInOrder(),binaryTree.TraversalPostOrder()));
		assertEquals("12 9 44 114514 1236 112 23 499706 ",binaryTree.InPost2Pre("44 114514 9 1236 112 12 23 499706 ","114514 44 112 1236 9 499706 23 12 "));

		System.out.println(binaryTree.InPost2Pre(binaryTree.TraversalInOrder(),binaryTree.TraversalPostOrder()));
		assertEquals("12 9 44 982 1236 1296 23 173173 ",binaryTree.InPost2Pre("44 982 9 1236 1296 12 23 173173 ","982 44 1296 1236 9 173173 23 12 "));

		System.out.println(binaryTree.PreIn2Post(binaryTree.TraversalPreOrder(),binaryTree.TraversalInOrder()));
		assertEquals("982 44 1296 1236 9 173173 23 12 ",binaryTree.PreIn2Post("12 9 44 982 1236 1296 23 173173 ","44 982 9 1236 1296 12 23 173173 "));


	}



}
