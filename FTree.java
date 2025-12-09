public class FTree extends Tree<Integer, WTree, FNode> {
    public String get(Integer key) {
        FNode node = ROOT;
        while (node != null) {
            if (node.key == key) {
                return node.value.max();
            } else if (node.key > key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        return null;
    }

    public void add(Integer key, String value) {
        ROOT = add(key, value, ROOT);
    }

    private FNode add(Integer key, Key value, FNode node) { // Increments if key already exists (DOESN'T OVERWRITE)
        if (node == null) {
            return new FNode(key, value, null, null);
        }
        if (node.key == key) {
            node.value++;
            return node;
        } else if (node.key > key) {
            node.right = add(key, value, node.right);
            return node;
        } else {
            node.left = add(key, value, node.left);
            return node;
        }
    }
}
