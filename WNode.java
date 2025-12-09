public class WNode extends Node<String, Integer, WNode> {
    public WNode(String word, int count, WNode left, WNode right) {
        super(word, count, left, right);
    }
}