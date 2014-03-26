package net.earthcoder.jmlm.domain;

import java.util.*;

/**
 * Created by Wei on 2014/3/23.
 */
public abstract class BinaryNode {

    private Human content;
    private FeeController feeController;
    private BinaryNode left;
    private BinaryNode right;
    private Set<Relationship> relationshipSet;
    private Date createDate;
    private long[] results = {0, 0};
    private long[] current = {0, 0};
    private BinaryNode[] childs;
    public BinaryNode[] getChilds() {
        return childs;
    }

    public long getLeftResults() {
        return results[0];
    }

    public long getRightResults() {
        return results[1];
    }

    public long getLeftCurrent() {
        return current[0];
    }

    public long getRightCurrent() {
        return current[1];
    }

    private void leftCurrentMinus(long feeValue) {
        current[0] -= feeValue;
    }

    private void rightCurrentMinus(long feeValue) {
        current[1] -= feeValue;
    }

    protected void leftResultAdd(long feeValue) {
        results[0] += feeValue;
        current[0] += feeValue;
    }

    protected void rightResultAdd(long feeValue) {
        results[1] += feeValue;
        current[1] += feeValue;
    }

    protected int level;

    public BinaryNode(Human content, Date createDate) {
        this.content = content;
        this.createDate = createDate;
        feeController = new FeeController();
        relationshipSet = new HashSet<Relationship>();
        childs = new BinaryNode[2];
    }

    protected void autoMountNode(BinaryNode newNode) {
        if (leftIsEmpty()) {
            leftLoad(newNode);
            childs[0] = newNode;
        } else if (rightIsEmpty()) {
            rightLoad(newNode);
            childs[1] = newNode;
        }
    }

    protected void mountNode(BinaryNode newNode, String flag) {
        if ("LEFT".equals(flag)) {
            leftLoad(newNode);
            childs[0] = newNode;
        } else if ("RIGHT".equals(flag)) {
            rightLoad(newNode);
            childs[1] = newNode;
        } else {
            throw new RuntimeException("Wrong flag.");
        }
    }

    private void leftLoad(BinaryNode newNode) {
        if (!this.leftIsEmpty()) {
            throw new RuntimeException("Load node in a not empty node.");
        } else {
            left = newNode;
        }
    }

    private void rightLoad(BinaryNode newNode) {
        if (!this.rightIsEmpty()) {
            throw new RuntimeException("Load node in a not empty node.");
        } else {
            right =  newNode;
        }
    }

    protected Boolean flashedForAncestor(Integer ancestor) {
        for (Relationship relation : relationshipSet) {
            if (relation.getId().equals(ancestor)) {
                return relation.getFlashed();
            }
        }
        return null;
    }

    protected void setRelationshipFlag() {
        if (this == getFather().getLeft()) {
            setRelationshipFlag(getFather().getContent().nodeID(), "LEFT");
        } else if (this == getFather().getRight()) {
            setRelationshipFlag(getFather().getContent().nodeID(), "RIGHT");
        }
    }

    private void setRelationshipFlag(Integer id, String flag) {
        for (Relationship relation : relationshipSet) {
            if (relation.getId().equals(id)) {
                relation.setFlag(flag);
                break;
            }
        }
    }

    protected boolean leftIsEmpty() {
        return null == this.left;
    }

    protected boolean rightIsEmpty() {
        return null == this.right;
    }

    protected void addInitialFee(Date date) {
        feeController.addInitialFee(date, content);
    }

    protected void addCounselingFee(Date date) {
        feeController.addCounselingFee(date, content);
    }

    protected void addOperatingExpenses(Date date) {
        feeController.addOperatingExpenses(date, content);
        leftCurrentMinus(feeController.getInitialFee().defaultValue());
        rightCurrentMinus(feeController.getInitialFee().defaultValue());
    }

    public long getCounselingFee() {
        return feeController.getCounselingFee().sum();
    }

    public long getOperatingExpenses() {
        return feeController.getOperatingExpenses().sum();
    }

    protected long getInitialFee() {
        return feeController.getInitialFee().sum();
    }

    protected Set<Relationship> getRelationshipSet() {
        return relationshipSet;
    }

    public Human getContent() {
        return content;
    }

    protected int getLevel() {
        return this.level;
    }

    protected BinaryNode getLeft() {
        return left;
    }

    protected BinaryNode getRight() {
        return right;
    }

    protected Date getCreateDate() {
        return createDate;
    }

    protected abstract BinaryNode getFather();
    protected abstract BinaryNode getRefer();

    @Override
    public int hashCode() {
        return this.getContent().hashCode();
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (null == otherObject) {
            return false;
        }
        if (this.getClass() != otherObject.getClass()) {
            return false;
        }
        @SuppressWarnings("unchecked")
        RegularBinaryNode otherNode = (RegularBinaryNode) otherObject;
        return this.getContent().equals(otherNode.getContent());
    }

    @Override
    public String toString() {
        return this.getContent().toString();
    }
}
