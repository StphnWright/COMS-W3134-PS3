public class ExpressionTreeTest {

	public static void main(String[] args) {
		ExpressionTree tree = new ExpressionTree("34 2 - 5 *");
		System.out.println(tree.eval());
		System.out.println(tree.postfix());
		System.out.println(tree.prefix());
		System.out.println(tree.infix());
	}
}
