public class WTree extends Tree<String, Integer, WNode> {
    public WNode get(String key) {
        WNode node = ROOT;
        while (node != null) { // Wanted to try both iterative and recursive -- Also feel like iterative is
                               // cleaner for this
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

    public void increment(String key) {
        ROOT = increment(key, ROOT);
    }

    private WNode increment(String key, WNode node) { // Increments if key already exists (DOESN'T OVERWRITE)
        if (node == null) {
            return new WNode(key, 1, null, null);
        }
        if (node.key.equals(key)) {
            node.value++;
            return node;
        } else if (before(node.key, key)) {
            node.right = increment(key, node.right);
            return node;
        } else {
            node.left = increment(key, node.left);
            return node;
        }
    }

    public WNode max() {
        return max(ROOT);
    }

    public WNode max(WNode node) {
        if (node.left == null && node.right == null) {
            return node;
        } else if (node.left == null) {
            return largerNode(node, max(node.right));
        } else if (node.right == null) {
            return largerNode(node, max(node.left));
        } else {
            return largerNode(max(node.left), max(node.right));
        }
    }

    private WNode largerNode(WNode node1, WNode node2) {
        if (node1.value > node2.value)
            return node1;
        else
            return node2;
    }
}