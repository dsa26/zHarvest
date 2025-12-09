public class WTree extends Tree<String, Integer, WNode> {
    public Integer get(String key) {
        WNode node = ROOT;
        while (node != null) { // Wanted to try both iterative and recursive -- Also feel like iterative is
                               // cleaner for this
            if (node.key == key) {
                return node.value;
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
            node.right = add(key, node.right);
            return node;
        } else {
            node.left = add(key, node.left);
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

        return first.length() < second.length(); // I want smaller keys to be "before"
    }

    public WNode max(WNode node) {
        if (node.left == null && node.right == null) {
            return node;
        }
    }
}
