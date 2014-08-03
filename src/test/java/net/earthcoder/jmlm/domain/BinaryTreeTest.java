package net.earthcoder.jmlm.domain;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * Created by Wei on 2014/3/31.
 */
public class BinaryTreeTest extends TestCase {

    BinaryTree tree;

    public void setUp() {
        tree = BinaryTree.getNewTree();
        tree.addNode(new People(1), null, null);
    }

    public void testFlashNodesRoot() {
        Assert.assertEquals(true, true);
    }
}
