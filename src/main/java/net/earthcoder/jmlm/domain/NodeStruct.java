package net.earthcoder.jmlm.domain;

/**
 * Created by Wei on 2014/8/3.
 */
public final class NodeStruct {

    private static final String ERROR = "Load node in a not empty node.";

    private BinaryNode left;
    private BinaryNode right;
    private BinaryNode[] childs;

    public NodeStruct() {
        childs = new BinaryNode[2];
    }

    protected void autoMountNode(BinaryNode newNode) {
        if (leftIsEmpty()) {
            setLeft(newNode);
        } else if (rightIsEmpty()) {
            setRight(newNode);
        }
    }

    protected boolean leftIsEmpty() {
        return null == left;
    }

    protected boolean rightIsEmpty() {
        return null == right;
    }

    protected boolean isFull() {
        return null != childs[0] && null != childs[1];
    }

    protected BinaryNode getLeft() {
        return left;
    }

    protected BinaryNode getRight() {
        return right;
    }

    protected BinaryNode[] getChilds() {
        return childs;
    }

    private void setLeft(BinaryNode left) {
        if (leftIsEmpty()) {
            this.left = left;
            childs[0] = left;
        } else {
            throw new RuntimeException(ERROR);
        }

    }

    private void setRight(BinaryNode right) {
        if (rightIsEmpty()) {
            this.right = right;
            childs[1] = right;
        } else {
            throw new RuntimeException(ERROR);
        }
    }
}
