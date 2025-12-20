public class DoubleLL<K, V, N extends Node<K, V, N>> {
    protected N ROOT;

    public DoubleLL(Tree<K, V, N> tree) {
        this.ROOT = create(tree.ROOT).H;
    }

    private HT<N> create(N node) { // Found it easier to create a new Class because I can't make arrays of generic
                                   // types
        if (node == null)
            return null;
        HT<N> left = create(node.left);
        HT<N> right = create(node.right);
        N HEAD;
        N TAIL;

        if (left == null) {
            node.left = null;
            HEAD = node;
        } else {
            left.T.right = node;
            node.left = left.T;
            HEAD = left.H;
        }

        if (right == null) {
            node.right = null;
            TAIL = node;
        } else {
            right.H.left = node;
            node.right = right.H;
            TAIL = right.T;
        }

        return new HT<N>(HEAD, TAIL);
    }

    public void display() {
        display(ROOT);
    }

    private void display(N node) {
        if (node == null)
            return;
        if (node.left != null && node.left.right != node)
            System.out.println("Something is wrong with LinkedList");
        System.out.println("Key: " + node.key + " Value: " + node.value);
        display(node.right);
    }
}
