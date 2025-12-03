public class Node {
    public String word;
    public int count;
    public Node left;
    public Node right;
    
    public Node(String word, int count, Node left, Node right) {
        this.word = word;
        this.count = count;
        this.left = left;
        this.right = right;
    }
}