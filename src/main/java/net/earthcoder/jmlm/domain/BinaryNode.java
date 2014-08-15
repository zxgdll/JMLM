package net.earthcoder.jmlm.domain;

import java.util.*;

public abstract class BinaryNode {

    private static final long DEFAULT_VALUE = 2200;
    private static final int RELATION_INIT = 20;

    private Human content;
    private NodeStruct nodeStruct;
    private FeeController feeController;
    private Set<Relationship> relationshipSet;
    private NodeSales nodeSales;
    private long historyValue;
    private long currentValue;
    protected int level;

    public BinaryNode(Human content, Long value) {
        this.content = content;
        nodeStruct = new NodeStruct();
        feeController = new FeeController();
        relationshipSet = new HashSet<Relationship>(RELATION_INIT);
        historyValue = currentValue = (null == value ? DEFAULT_VALUE : value);
        nodeSales = new NodeSales();
    }

    public boolean contains (Human content) {
        return this.content.equals(content);
    }

    public boolean isFull() {
        return nodeStruct.isFull();
    }

    public BinaryNode[] getChilds() {
        return nodeStruct.getChilds();
    }

    public long getLeftResults() {
        return nodeSales.getLeftHistorySales();
    }

    public long getRightResults() {
        return nodeSales.getRightHistorySales();
    }

    public long getLeftCurrent() {
        return nodeSales.getLeftCurrentSales();
    }

    public long getRightCurrent() {
        return nodeSales.getRightCurrentSales();
    }

    protected Map<Date, List<BillItem>> getBillList() {
        return feeController.getBillList();
    }

    protected void leftResultAdd(long feeValue) {
        nodeSales.leftResultAdd(feeValue);
    }

    protected void rightResultAdd(long feeValue) {
        nodeSales.rightResultAdd(feeValue);
    }

    protected void autoMountNode(BinaryNode newNode) {
        nodeStruct.autoMountNode(newNode);
    }

    protected Boolean flashedForAncestor(BinaryNode ancestor) {
        for (Relationship relation : relationshipSet) {
            if (relation.getBinaryNode().equals(ancestor)) {
                return relation.getFlashed();
            }
        }
        return null;
    }

    protected void setRelationshipFlag() {
        if (this == getFather().getLeft()) {
            setRelationshipFlag(getFather(), "LEFT");
        } else if (this == getFather().getRight()) {
            setRelationshipFlag(getFather(), "RIGHT");
        }
    }

    private void setRelationshipFlag(BinaryNode node, String flag) {
        for (Relationship relation : relationshipSet) {
            if (relation.getBinaryNode().equals(node)) {
                relation.setFlag(flag);
                break;
            }
        }
    }

    protected boolean leftIsEmpty() {
        return nodeStruct.leftIsEmpty();
    }

    protected boolean rightIsEmpty() {
        return nodeStruct.rightIsEmpty();
    }

    protected void addInitialFee() {
        feeController.addInitialFee(content.initDateTime(), content);
    }

    protected void addCounselingFee(Date date) {
        feeController.addCounselingFee(date, content);
    }

    protected void addOperatingExpenses(Date date) {
        feeController.addOperatingExpenses(date, content);
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
        return nodeStruct.getLeft();
    }

    protected BinaryNode getRight() {
        return nodeStruct.getRight();
    }

    protected Date getCreateDate() {
        return content.initDateTime();
    }

    public long getHistoryValue() {
        return historyValue;
    }

    public long getCurrentValue() {
        return currentValue;
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
        if (level != otherNode.level || historyValue != otherNode.getHistoryValue()
                || currentValue != otherNode.getCurrentValue()) {
            return false;
        }
        return content.equals(otherNode.getContent());
    }

    @Override
    public String toString() {
        return this.getContent().toString();
    }
}
