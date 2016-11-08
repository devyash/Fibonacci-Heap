/**
 * Created by Dylan Richardson on 10/29/16.
 *
 * Description: Maintains and manages nodes in a fibonacci heap and pointers to nodes by value.
 *              FibonacciMaxHeap has standard fibonacci heap methods, including insert, removeMax,
 *              remove, and increaseKey. I decoupled increaseKey and insert so the runner can be
 *              conscious of repeat keys.
 */
import java.util.HashMap;

public class FibonacciMaxHeap {

    private HashMap<String, Node> nodeHashMap;
    private Node root;

    public FibonacciMaxHeap()
    {
        nodeHashMap = new HashMap<>();
        root = null;
    }

    /**
     * @param count Amount of times hashtag was recorded
     * @param hashtag String representation of the hashtag
     * @return True IFF the insert was successful
     */
    public boolean insert(int count, String hashtag) {
        // Break immediately if we already have this key. Runner must do an increaseKey instead.
        if (nodeHashMap.containsKey(hashtag))
            return false;

        Node node = new Node(count, hashtag);

        if (root == null) {
            node = root;
        }
        else {
            // Insert the node into the doubly linked list to the right of the root
            root.getRight().setLeft(node);
            node.setRight(root.getRight());
            node.setLeft(root);
            root.setRight(node);
            // Replace root with max value if appropriate
            if (root.getCount() < count)
                root = node;
        }
        nodeHashMap.put(hashtag, node);
        return true;
    }

    public boolean increaseKey(String hashtag, int count) {
        // Break immediately if the key is not already in the tree
        if (!nodeHashMap.containsKey(hashtag))
            return false;
        // TODO
        return true;
    }

    public boolean remove(Node node) {
        if (node == root)
        {

        }
        return false;
    }

    public Node removeMin() {
        if (root == null)
            return null;

        Node ret_node = root;
        nodeHashMap.remove(ret_node.getHashtag());

        // Nothing is left in the top level. Go directly to subtrees
        if (root.getRight() == root)
            if (root.getChild() != null)
                root = root.getChild();
            else
                root = null;
        // Root has siblings and children. Need to bring root's children up to top level.
        else if (root.getChild() != null) {
            Node child = root.getChild();
            root = root.getRight();
            Node orig_right = root.getRight();
            root.getRight().setLeft(child);
            root.setRight(child.getRight());
            child.setRight(orig_right);
            orig_right.setLeft();
        }

        else {
            HashMap<Integer, Node> degreeMap = new HashMap<>();

            degreeMap.put(root.getDegree(), root);

            Node next = root.getRight();

            // Traverse through top level linked list and check the degrees of every node
            // against previously encountered degrees. If match is found, merge into one tree.
            // Remove old hashmap entry, and add the "new" tree's degree.
            while (next != root) {
                // Need to merge with other node
                if (degreeMap.containsKey(next.getDegree())) {
                    // Remove other node from doubly linked list
                    next.getLeft().setRight(next.getRight());
                    next.getRight().setLeft(next.getLeft());

                    Node comp = degreeMap.get(next.getDegree());

                    if (comp.getCount() > next.getCount()) {

                    }

                }
            }
        }

        return ret_node;
    }
}
