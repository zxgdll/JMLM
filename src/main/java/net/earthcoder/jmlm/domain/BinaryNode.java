package net.earthcoder.jmlm.domain;

import java.util.*;

/**
 * Created by Wei on 2014/3/23.
 */
public abstract class BinaryNode {

    private static final long DEFAULT_VALUE = 2200;
    private static final int RELATION_INIT = 20;

    private Human content;
    private FeeController feeController;
    private Set<Relationship> relationshipSet;
    private long[] results = {0, 0};
    private long[] current = {0, 0};
    private long historyValue;
    private long currentValue;
    private BinaryPlanArea a_area;
    private BinaryPlanArea b_area;
    protected int level;

    private NodeStruct nodeStruct;

    public BinaryNode(Human content) {
        this.content = content;
        nodeStruct = new NodeStruct();
        feeController = new FeeController();
        relationshipSet = new HashSet<Relationship>(RELATION_INIT);

        a_area = new BinaryPlanArea();
        b_area = new BinaryPlanArea();

        historyValue = currentValue = BinaryNode.DEFAULT_VALUE;
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

    protected Map<Date, List<BillItem>> getBillList() {
        return feeController.getBillList();
    }

    protected void leftResultAdd(long feeValue) {
        results[0] += feeValue;
        current[0] += feeValue;
    }

    protected void rightResultAdd(long feeValue) {
        results[1] += feeValue;
        current[1] += feeValue;
    }

    protected void autoMountNode(BinaryNode newNode) {
        nodeStruct.autoMountNode(newNode);
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
        return nodeStruct.getLeft();
    }

    protected BinaryNode getRight() {
        return nodeStruct.getRight();
    }

    protected Date getCreateDate() {
        return content.initDateTime();
    }

    protected abstract BinaryNode getFather();
    protected abstract BinaryNode getRefer();

    public long getHistoryValue() {
        return historyValue;
    }

    public long getCurrentValue() {
        return currentValue;
    }

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
        return content.equals(otherNode.getContent());
    }

    @Override
    public String toString() {
        return this.getContent().toString();
    }
}
