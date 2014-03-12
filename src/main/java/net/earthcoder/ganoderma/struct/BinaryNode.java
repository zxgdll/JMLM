package net.earthcoder.ganoderma.struct;

import java.util.*;

import net.earthcoder.ganoderma.Relationship;
import net.earthcoder.ganoderma.man.*;

public class BinaryNode<E extends Human> extends Node<E> {

    private BinaryNode<E> left;
    private BinaryNode<E> right;
    private BinaryNode<E> father;
    private int level;
    private Set<Relationship> relationshipSet;
    private Date createDate;
    
    public BinaryNode(E content, BinaryNode<E> father, Date createDate) {
        this.createDate = createDate;
        this.content = content;
        this.father = father;
        this.level = (null == this.father ? 1 : this.father.level + 1);
        this.relationshipSet = new HashSet<Relationship>();
        if (null != this.father) {
            for (Relationship r : this.father.relationshipSet) {
                this.relationshipSet.add(new Relationship(r.getId(), r.getFlag()));
            }
            this.relationshipSet.add(new Relationship(this.father.getContent().getID(), null));
        }
    }
    
    public void addCounselingFee(Date date) {
        super.feeController.addCounselingFee(date, super.content);
    }

    public void addOperatingExpenses(Date date) {
        super.feeController.addOperatingExpenses(date, super.content);
    }

    public long getCounselingFee() {
        return feeController.getCounselingFee().sum();
    }

    public long getOperatingExpenses() {
        return feeController.getOperatingExpenses().sum();
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    protected void autoLoad(BinaryNode<E> newNode) {
        if (this.leftIsEmpty()) {
            this.leftLoad(newNode);
        } else if (this.rightIsEmpty()) {
            this.rightLoad(newNode);
        }
    }
    
    protected void leftLoad(BinaryNode<E> newNode) {
        if (!this.leftIsEmpty()) {
            throw new RuntimeException("Load node in a not empty node.");
        } else {
            this.left = newNode;
        }
    }
    
    protected void rightLoad(BinaryNode<E> newNode) {
        if (!this.rightIsEmpty()) {
            throw new RuntimeException("Load node in a not empty node.");
        } else {
            this.right =  newNode;
        }
    }

    public boolean leftIsEmpty() {
        return null == this.left;
    }

    public boolean rightIsEmpty() {
        return null == this.right;
    }

    @Override
    public int hashCode() {
        return this.content.hashCode();
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
        BinaryNode<E> otherNode = (BinaryNode<E>) otherObject;
        return this.content.equals(otherNode.content);
    }

    @Override
    public String toString() {
        return this.content.toString();
    }

    protected Boolean flashedForAncestor(Integer ancestor) {
        for (Relationship r : this.relationshipSet) {
            if (r.getId().equals(ancestor)) {
                return r.getFlashed();
            }
        }
        return null;
    }

    protected Set<Relationship> getRelationshipSet() {
        return this.relationshipSet;
    }



    protected void setRelationshipFlag(Integer id, String flag) {
        for (Relationship r : this.relationshipSet) {
            if (r.getId().equals(id)) {
                r.setFlag(flag);
                break;
            }
        }
    }

    public int getLevel() {
        return this.level;
    }

    protected BinaryNode<E> getFather() {
        return this.father;
    }

    public BinaryNode<E> getLeft() {
        return left;
    }

    protected void setLeft(BinaryNode<E> left) {
        this.left = left;
    }

    public BinaryNode<E> getRight() {
        return right;
    }

    protected void setRight(BinaryNode<E> right) {
        this.right = right;
    }
}
