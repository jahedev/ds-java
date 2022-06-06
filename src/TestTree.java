public class TestTree {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        tree.add(25);
        tree.add(20);
        tree.add(15);
        tree.add(27);
        tree.add(30);
        tree.add(29);
        tree.add(26);
        tree.add(22);
        tree.add(32);
        tree.add(17);

        tree.traverse(Tree.TraversalOrder.IN_ORDER);


        // case 1
//        tree.remove(15);
//        tree.traverseInOrder();

        // case 2
//        tree.remove(17);
//        tree.traverseInOrder();
//
        // case 3
        tree.remove(25);
        tree.traverse(Tree.TraversalOrder.IN_ORDER);

    }
}
