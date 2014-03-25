package net.earthcoder.jmlm.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    protected int level;

    public BinaryNode(Human content, Date createDate) {
        this.content = content;
        this.createDate = createDate;
        feeController = new FeeController();
        relationshipSet = new HashSet<Relationship>();
    }

    protected void autoMountNode(BinaryNode newNode) {
        if (this.leftIsEmpty()) {
            this.leftLoad(newNode);
        } else if (this.rightIsEmpty()) {
            this.rightLoad(newNode);
        }
    }

    protected void mountNode(BinaryNode newNode, String flag) {
        if ("LEFT".equals(flag)) {
            leftLoad(newNode);
        } else if ("RIGHT".equals(flag)) {
            rightLoad(newNode);
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
            setRelationshipFlag(getFather().getContent().getID(), "LEFT");
        }
        if (this == getFather().getRight()) {
            setRelationshipFlag(getFather().getContent().getID(), "RIGHT");
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

    protected void addCounselingFee(Date date) {
        feeController.addCounselingFee(date, this.getContent());
    }

    protected void addOperatingExpenses(Date date) {
        feeController.addOperatingExpenses(date, this.getContent());
    }

    protected long getCounselingFee() {
        return feeController.getCounselingFee().sum();
    }

    protected long getOperatingExpenses() {
        return feeController.getOperatingExpenses().sum();
    }

    protected Set<Relationship> getRelationshipSet() {
        return relationshipSet;
    }

    protected Human getContent() {
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
