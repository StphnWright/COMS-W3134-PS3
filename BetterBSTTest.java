public class BetterBSTTest {
    public static void main(String[] args) {
     
        String[] letters = {"M", "D", "R", "B", "F", "E"};
        BetterBST<String> tree = new BetterBST<>();
        
        for (String letter : letters) {
            tree.insert(letter);
        }
        
        System.out.println("Height: " + tree.height());
        System.out.println(tree.levelOrderTraversal());
            
        for (var node : tree.levelOrderTraversal()) {
            System.out.print(node.data + " ");
        }
        System.out.println();
        
        for (String letter : letters) {
           System.out.println("Smallest Greater Than " + letter + ": " + tree.smallestGreaterThan(letter)); 
        }
        
        System.out.println("Mirror: ");
        
        for (var node : tree.mirror().levelOrderTraversal()) {
            System.out.print(node.data + " ");
        }
        System.out.println();
        
        System.out.println("Imbalanced: " + tree.imbalance());
    }  
}
