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

    protected void leftLoad(BinaryNode newNode) {
        if (!this.leftIsEmpty()) {
            throw new RuntimeException("Load node in a not empty node.");
        } else {
            this.left = newNode;
        }
    }

    protected void rightLoad(BinaryNode newNode) {
        if (!this.rightIsEmpty()) {
            throw new RuntimeException("Load node in a not empty node.");
        } else {
            this.right =  newNode;
        }
    }

    protected Boolean flashedForAncestor(Integer ancestor) {
        for (Relationship r : this.relationshipSet) {
            if (r.getId().equals(ancestor)) {
                return r.getFlashed();
            }
        }
        return null;
    }

    protected void setRelationshipFlag(Integer id, String flag) {
        for (Relationship r : this.relationshipSet) {
            if (r.getId().equals(id)) {
                r.setFlag(flag);
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
