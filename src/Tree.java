public class Tree<E> {
    class Node<E> {
        E data;
        Node<E> left;
        Node<E> right;

        public Node(E obj) {
            data = obj;
            left = right = null;
        }
    }

    static enum TraversalOrder {
        IN_ORDER,
        PRE_ORDER,
        POST_ORDER;
    }

    private Node<E> root;
    int currentSize;

    public Tree() {
        root = null;
        currentSize = 0;
    }


    private Node<E> add(E obj, Node<E> node) {
        if (((Comparable<E>) obj).compareTo(node.data) > 0) {
            // go to right
            if (node.right == null) {
                node.right = new Node<>(obj);
                return node.right;
            } else {
                return add(obj, node.right);
            }
        } else {
            if (node.left == null) {
                node.left = new Node<>(obj);
                return node.left;
            }
            return add(obj, node.left);
        }

    }

    public Node<E> add(E obj) {
        currentSize++;
        if (root == null) {
            root = new Node<>(obj);
            return root;
        }
        return add(obj, root);
    }

    public boolean contains(E obj) {
        return contains(obj, root);
    }

    private boolean contains(E obj, Node<E> node) {
        if (node == null) return false;

        int comparison = ((Comparable<E>) obj).compareTo(node.data);

        if (comparison == 0) return true;
        else if (comparison < 0) return contains(obj, node.left);
        else return contains(obj, node.right);
    }

    public void remove(E obj) {

        root = remove(obj, root);
    }

    private Node<E> remove(E obj, Node<E> node) {
        if (node == null) return node;

        int comparison = ((Comparable<E>) obj).compareTo(node.data);

        if (comparison < 0) node.left = remove(obj, node.left);
        else if (comparison > 0) node.right = remove(obj, node.right);
        else { // comparison == 0
            // Case1&2: node has 0 or 1 children
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;

            // Case3: node has 2 children
            // replace the data in node with the smallest value from node.right's tree
            node.data = min(node.right);

            // delete the node that has the smallest value in the node.right's tree
            node.right = remove(node.data, node.right);
        }
        return node;
    }

    public E min() {
        return min(root);
    }

    private E min(Node<E> node) {
        if (node.left == null) return node.data;
        else return min(node.left);
    }

    public E max() {
        return max(root);
    }

    private E max(Node<E> node) {
        if (node.right == null) return node.data;
        else return min(node.right);
    }

    private Node<E> leftRotate(Node<E> node) {
        Node<E> tmp = node.right;
        node.right = tmp.left;
        tmp.left = node;
        return tmp;
    }

    private Node<E> rightRotate(Node<E> node) {
        Node<E> tmp = node.left;
        node.left = tmp.right;
        tmp.right = node;
        return tmp;
    }

    public void traverse(TraversalOrder order) {
        if (root == null) {
            System.out.println("[]");
            return;
        }

        System.out.print("[");
        switch (order) {
            case IN_ORDER -> traverseInOrder(root);
            case PRE_ORDER -> traversePreOrder(root);
            case POST_ORDER -> traversePostOrder(root);
        }
        System.out.println("\b\b]");
    }

    private void traverseInOrder(Node<E> node) {
        if (node == null) return;

        if (node.left != null) traverseInOrder(node.left);
        System.out.print(node.data.toString() + ", ");
        if (node.right != null) traverseInOrder(node.right);
    }

    private void traversePreOrder(Node<E> node) {
        if (node == null) return;

        System.out.print(node.data.toString() + ", ");
        if (node.left != null) traversePreOrder(node.left);
        if (node.right != null) traversePreOrder(node.right);
    }

    private void traversePostOrder(Node<E> node) {
        if (node == null) return;

        if (node.left != null) traversePostOrder(node.left);
        if (node.right != null) traversePostOrder(node.right);
        System.out.print(node.data.toString() + ", ");
    }

}
