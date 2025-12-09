public abstract class Tree<K, V, N extends Node<K, V, N>> {
    protected N ROOT;

    public Tree() {
        this.ROOT = null;
    }

    public void display() {
        display(ROOT);
    }

    private void display(N node) { // Hopefully alphabetically sorted traversal
        if (node == null) {
            return;
        }
        display(node.left);
        System.out.println("Key: " + node.key + ", Value: " + node.value);
        display(node.right);
    }

    public void reverseDisplay() {
        reverseDisplay(ROOT);
    }

    private void reverseDisplay(N node) { // Hopefully alphabetically sorted traversal
        if (node == null) {
            return;
        }
        reverseDisplay(node.right);
        System.out.println("Key: " + node.key + ", Value: " + node.value);
        reverseDisplay(node.left);
    }

    public abstract V get(K key);

    public abstract void increment(K key);
}