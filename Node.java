public class Node<K, V, N extends Node<K, V, N>> { // Cool syntax and solution from ChatGPT:
                                                   // https://chatgpt.com/share/69386b95-ba80-8004-af21-cd02b33ecd1d
    public K key;
    public V value;
    public N left;
    public N right;

    public Node(K key, V value, N left, N right) {
        this.key = key;
        this.value = value;
        this.left = left;
        this.right = right;
    }
}
