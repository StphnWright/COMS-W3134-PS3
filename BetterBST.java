import java.util.LinkedList;
import java.util.IdentityHashMap;

public class BetterBST<T extends Comparable<? super T>> extends BinarySearchTree<T> {

	@Override
	int height() {
		return height(root) - 1;
	}

	private int height(BinaryNode<T> node) {
		return node == null ? 0 : 1 + Math.max(height(node.left), height(node.right));
	}

	@Override
	T imbalance() {
		return imbalance(root);
	}

	private T imbalance(BinaryNode<T> node) {
		if (node == null) {
			return null;
		}
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		int diff = Math.abs(leftHeight - rightHeight);

		if (diff > 1) {
			return node.data;
		}

		T leftImbalance = imbalance(node.left);
		if (leftImbalance != null) {
			return leftImbalance;
		}
		T rightImbalance = imbalance(node.right);
		if (rightImbalance != null) {
			return rightImbalance;
		}
		return null;
	}

	@Override
	T smallestGreaterThan(T t) {
		return smallestGreaterThan(t, root, null);
	}

	private T smallestGreaterThan(T t, BinaryNode<T> node, BinaryNode<T> result) {
		if (node == null) {
			return result == null ? null : result.data;
		}

		if (node.data.compareTo(t) <= 0) {
			return smallestGreaterThan(t, node.right, result);
		}

		if (result == null || node.data.compareTo(result.data) < 0) {
			result = node;
		}
		return smallestGreaterThan(t, node.left, result);
	}

	@Override
	BinarySearchTree<T> mirror() {
		BinarySearchTree<T> tree = new BetterBST<T>();
		tree.root = copy(root);
		mirror(tree.root);

		return tree;
	}

	private BinaryNode<T> copy(BinaryNode<T> node) {
		if (node == null) {
			return null;
		}
		BinaryNode<T> root = new BinaryNode<T>(node.data);
		root.left = copy(node.left);
		root.right = copy(node.right);

		return root;
	}

	private void mirror(BinaryNode<T> node) {
		if (node == null) {
			return;
		}
		BinaryNode<T> temp = node.left;
		node.left = node.right;
		node.right = temp;
		mirror(node.left);
		mirror(node.right);
	}

	@Override
	public LinkedList<BinarySearchTree.BinaryNode<T>> levelOrderTraversal() {
		var list = new LinkedList<BinarySearchTree.BinaryNode<T>>();
		if (root != null) {
			var queue = new LinkedList<BinarySearchTree.BinaryNode<T>>();
			var discoveredMap = new IdentityHashMap<BinarySearchTree.BinaryNode<T>, Boolean>();

			discoveredMap.put(root, true);
			queue.add(root);

			while (!queue.isEmpty()) {
				var node = queue.remove();
				list.add(node);

				if (node.left != null && !discoveredMap.containsKey(node.left)) {
					discoveredMap.put(node.left, true);
					queue.add(node.left);
				}

				if (node.right != null && !discoveredMap.containsKey(node.right)) {
					discoveredMap.put(node.right, true);
					queue.add(node.right);
				}
			}
		}
		return list;
	}
}
