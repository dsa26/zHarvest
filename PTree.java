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

    public void predictRandom(String start) {
        predictRandom(start, 25);
    }

    public void predictRandom(String start, int words) {
        if (this.get(start) == null) {
            System.out.println("No prediction possible for word: " + start);
            return;
        }

        String current = start;
        System.out.print("Prediction: " + current);

        while (words-- > 0) {
            PNode pNode = this.get(current);

            if (pNode == null || pNode.value == null) {
                break;
            }

            WNode nextNode = randomNext(pNode.value);

            if (nextNode == null) {
                break;
            }

            String next = nextNode.key;
            System.out.print(" " + next);
            current = next;
        }

        System.out.println();
    }

    private WNode randomNext(WTree tree) {
        int total = totalCount(tree.ROOT);

        if (total == 0) {
            return null;
        }

        int r = (int) (Math.random() * total);

        return randomNext(tree.ROOT, r);
    }

    private int totalCount(WNode node) {
        if (node == null) {
            return 0;
        }

        return node.value + totalCount(node.left) + totalCount(node.right);
    }

    private WNode randomNext(WNode node, int r) {
        if (node == null) {
            return null;
        }

        int leftTotal = totalCount(node.left);

        if (r < leftTotal) {
            return randomNext(node.left, r);
        }

        r -= leftTotal;

        if (r < node.value) {
            return node;
        }

        r -= node.value;

        return randomNext(node.right, r);
    }
}
