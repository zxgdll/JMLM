package net.earthcoder.jmlm.domain;

import java.util.*;

public final class RegularBinaryNode extends BinaryNode {

    private static final int BINARYNODE_LEVEL_STEP = 1;

    private BinaryNode father;
    private BinaryNode refer;

    public RegularBinaryNode(Human content, Date createDate, BinaryNode refer, BinaryNode father) {
        super(content, createDate);
        this.refer = refer;
        this.father = father;
        level = getFather().getLevel() + RegularBinaryNode.BINARYNODE_LEVEL_STEP;
        initRelationshipSet();
    }

    private void initRelationshipSet() {
        for (Relationship relation : father.getRelationshipSet()) {
            getRelationshipSet().add(new Relationship(relation.getId(), relation.getFlag()));
        }
        getRelationshipSet().add(new Relationship(father.getContent().getID(), null));
    }

    @Override
    protected BinaryNode getFather() {
        return father;
    }

    public BinaryNode getRefer() {
        return refer;
    }
}
