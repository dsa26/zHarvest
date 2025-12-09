public class Tree {
    private Node ROOT;

    public Tree() {
        this.ROOT = null;
    }

    public int get(String word) {
        Node node = ROOT;
        while (node != null) { // Wanted to try both iterative and recursive -- Also feel like iterative is
                               // cleaner for this
            if (node.word == word) {
                return node.count;
            } else if (before(node.word, word)) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return 0;
    }

    public void increment(String word) {
        ROOT = increment(word, ROOT);
    }

    public Node increment(String word, Node node) {
        if (node == null) {
            return new Node(word, 1, null, null);
        }
        if (node.word.equals(word)) {
            node.count++;
            return node;
        } else if (before(node.word, word)) {
            node.right = increment(word, node.right);
            return node;
        } else {
            node.left = increment(word, node.left);
            return node;
        }
    }

    private boolean before(String first, String second) {
        for (int i = 0; i < Math.min(first.length(), second.length()); i++) {
            if (first.charAt(i) > second.charAt(i)) {
                return false; // Being greater in ASCII means being before in lexicographic order
            } else if (first.charAt(i) < second.charAt(i)) {
                return true;
            }
        }

        return first.length() < second.length(); // I want smaller words to be "before"
    }

    public void display() {
        display(ROOT);
    }

    public void display(Node node) { // Hopefully alphabetically sorted traversal
        if (node == null) {
            return;
        }
        display(node.left);
        System.out.println("Word: " + node.word + ", Count: " + node.count);
        display(node.right);
    }

    public void reverseDisplay() {
        reverseDisplay(ROOT);
    }

    public void reverseDisplay(Node node) { // Hopefully alphabetically sorted traversal
        if (node == null) {
            return;
        }
        reverseDisplay(node.right);
        System.out.println("Word: " + node.word + ", Count: " + node.count);
        reverseDisplay(node.left);
    }
}