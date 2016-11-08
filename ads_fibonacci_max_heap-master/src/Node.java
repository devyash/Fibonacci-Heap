/**
 * Created by Dylan Richardson on 10/29/16.
 *
 * Description: Represents a node in a fibonacci tree
 */
public class Node {

    private Node child;
    private Node left;
    private Node right;
    private Node parent;
    private String hashtag;
    private int count;
    private boolean childCut;
    private int degree;

    public Node(int count, String hashtag) {
        this.count = count;
        this.hashtag = hashtag;
        this.child = null;
        this.left = this;
        this.right = this;
        this.parent = null;
        this.degree = 0;
        this.childCut = false;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
        left.right = this;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
        right.left = this;
    }

    public Node getChild() {
        return child;
    }

    public void setChild(Node child) {
        if (this.child != null)
        {
            this.child.getLeft().setRight(child);
            child.setRight(this.child);
        }
        else
        {
            this.child = child;
        }
        this.child.setParent(this);
        ++degree;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public int getCount() {
        return count;
    }

    public int getDegree() {
        return degree;
    }

    public String getHashtag() {
        return hashtag;
    }
}
