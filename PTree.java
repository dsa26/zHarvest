public class PTree extends Tree<String, WTree, PNode> {
    public PNode get(String key) {
        PNode node = ROOT;
        while (node != null) {
            if (node.key.equals(key)) {
                return node;
            } else if (before(node.key, key)) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public void increment(String key, String next) {
        ROOT = increment(key, next, ROOT);
    }

    private PNode increment(String key, String next, PNode node) { // Increments if key already exists (DOESN'T
                                                                   // OVERWRITE)
        if (node == null) {
            return new PNode(key, new WTree(), null, null);
        }
        if (node.key.equals(key)) {
            node.value.increment(next);
            return node;
        } else if (before(node.key, key)) {
            node.right = increment(key, next, node.right);
            return node;
        } else {
            node.left = increment(key, next, node.left);
            return node;
        }
    }

    public void predict(String start) {
        predict(start, 25);
    }

    public void predict(String start, int words) {
        if (this.get(start) == null) {
            System.out.println("No prediction possible for word: " + start);
            return;
        }
        String current = start;
        System.out.print("Prediction: " + current);
        while (words-- > 0) {
            String next = this.get(current).value.max().key;
            System.out.print(" " + next);
            current = next;
        }
        System.out.println();
    }
}
