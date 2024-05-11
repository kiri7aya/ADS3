import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.Entry<K, V>> {
    private Node root;
    private int size;
    private class Node {
        private K key;
        private V val;
        private Node left, right;
        public Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }
    public void put(K key, V val) {
        root = put(root, key, val);
    }
    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;
        return x;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }
    public int size() {
        return size;
    }
    public Iterator<Entry<K, V>> iterator() {
        List<Entry<K, V>> list = new ArrayList<>();
        inorder(root, list);
        return list.iterator();
    }
    private void inorder(Node x, List<Entry<K, V>> list) {
        if (x == null) return;
        inorder(x.left, list);
        list.add(new Entry<>(x.key, x.val));
        inorder(x.right, list);
    }
    public static void main(String[] args) {
        BST<Integer, String> tree = new BST<>();
        tree.put(3, "Three");
        tree.put(1, "One");
        tree.put(2, "Two");
        tree.put(5, "Five");
        tree.put(4, "Four");
        for (Entry<Integer, String> entry : tree) {
            System.out.println("key is " + entry.getKey() + " and value is " + entry.getValue());
        }
    }
    public static class Entry<K, V> {
        private K key;
        private V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
}
