package net.earthcoder.jmlm.domain;

/**
 * Created by Wei on 2014/3/23.
 */
public final class RootBinaryNode extends BinaryNode {

    private static final int BINARYNODE_ROOT_LEVEL = 1;

    public RootBinaryNode(Human content, Long value) {
        super(content, value);
        level = RootBinaryNode.BINARYNODE_ROOT_LEVEL;
    }

    @Override
    protected BinaryNode getFather() {
        return null;
    }

    @Override
    protected BinaryNode getRefer() {
        return null;
    }
}
