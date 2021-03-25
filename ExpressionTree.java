import java.util.LinkedList;
import java.util.List;

public class ExpressionTree implements ExpressionTreeInterface {

	private String expression;
	private LinkedList<ExpressionNode> stack = new LinkedList<>();

	public ExpressionTree(String expression) {
		this.expression = expression;
		String[] tokens = expression.split(" ");
		for (String token : tokens) {
			if ("+-*/".contains(token)) {
				ExpressionNode operand2 = stack.removeLast();
				ExpressionNode operand1 = stack.removeLast();
				ExpressionNode node = new ExpressionNode(token.charAt(0), operand1, operand2);
				stack.addLast(node);
			} else {
				ExpressionNode node = new ExpressionNode(Integer.parseInt(token));
				stack.addLast(node);
			}
		}
	}

	@Override
	public int eval() {
		return evalRecursive(stack.getLast());
	}

	private int evalRecursive(ExpressionNode node) {
		if (node.isLeaf()) {
			return node.value;
		} else {
			switch (node.operator) {
			case '+':
				return evalRecursive(node.left) + evalRecursive(node.right);
			case '-':
				return evalRecursive(node.left) - evalRecursive(node.right);
			case '*':
				return evalRecursive(node.left) * evalRecursive(node.right);
			case '/':
				return evalRecursive(node.left) / evalRecursive(node.right);
			default:
				return 0;
			}
		}
	}

	@Override
	public String postfix() {
		return postfixRecursive(stack.getLast());
	}

	private String postfixRecursive(ExpressionNode node) {
		if (node.isLeaf()) {
			return Integer.toString(node.value);
		} else {
			return postfixRecursive(node.left) + " " + postfixRecursive(node.right) + " " + node.operator;
		}
	}

	@Override
	public String prefix() {
		return prefixRecursive(stack.getLast());
	}

	private String prefixRecursive(ExpressionNode node) {
		if (node.isLeaf()) {
			return Integer.toString(node.value);
		} else {
			return node.operator + " " + prefixRecursive(node.left) + " " + prefixRecursive(node.right);
		}
	}

	@Override
	public String infix() {
		return infixRecursive(stack.getLast());
	}

	private String infixRecursive(ExpressionNode node) {
		if (node.isLeaf()) {
			return Integer.toString(node.value);
		} else {
			return "(" + infixRecursive(node.left) + " " + node.operator + " " + infixRecursive(node.right) + ")";
		}
	}

	private static class ExpressionNode {
		private int value;
		private char operator;
		private ExpressionNode left;
		private ExpressionNode right;

		public ExpressionNode(int value) {
			this.value = value;
		}

		public ExpressionNode(char operator, ExpressionNode left, ExpressionNode right) {
			this.operator = operator;
			this.left = left;
			this.right = right;
		}

		public boolean isLeaf() {
			return left == null && right == null;
		}
	}
}
