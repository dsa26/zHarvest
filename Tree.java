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

    protected boolean before(String first, String second) {
        for (int i = 0; i < Math.min(first.length(), second.length()); i++) {
            if (first.charAt(i) > second.charAt(i)) {
                return false; // Being greater in ASCII means being before in lexicographic order
            } else if (first.charAt(i) < second.charAt(i)) {
                return true;
            }
        }

        return first.length() < second.length(); // I want smaller keys to be "before"
    }

    // Decided to get rid of abstract methods because I wanted to customize
    // parameters and return types for each tree
}