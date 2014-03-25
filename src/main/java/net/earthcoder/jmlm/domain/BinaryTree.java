package net.earthcoder.jmlm.domain;

import java.util.*;

public final class BinaryTree {

    private BinaryNode rootNode;
    private BinaryNode fastNextNode;
    private long operatingExpensesSum;
    private long counselingFeeSum;
    private Fee initialFee;

    public BinaryTree() {
        initialFee = new InitialFee();
    }

    public void addNode(Human people, Integer fatherNodeID, String flag) {
        addNode(people, Calendar.getInstance().getTime(), fatherNodeID, flag);
    }

    public void addNode(Human people, Integer referNodeID, Integer fatherNodeID, String flag) {
        addNode(people, Calendar.getInstance().getTime(), referNodeID, fatherNodeID, flag);
    }

    public void addNode(Human people, Date createDate, Integer referNodeID, Integer fatherNodeID, String flag) {
        BinaryNode newNode = null;
        if (null == rootNode) {
            newNode = new RootBinaryNode(people, createDate);
            rootNode = newNode;
        } else if (null != fatherNodeID) {
            BinaryNode fatherNode = findNodeByID(fatherNodeID);
            if (null == fatherNode) {
                throw new RuntimeException("Father not find.");
            } else {
                newNode = new RegularBinaryNode(people, createDate, findNodeByID(referNodeID), fatherNode);
            }
            fatherNode.mountNode(newNode, flag);
            newNode.setRelationshipFlag();
        }
        flash(newNode, createDate);
    }

    public void addNode(Human people, Date createDate, Integer fatherNodeID, String flag) {
        addNode(people, createDate, fatherNodeID, fatherNodeID, flag);
    }

    public void addNode(Human people) {
        addNode(people, Calendar.getInstance().getTime());
    }

    public void addNode(Human people, Date createDate) {
        BinaryNode newNode;
        if (null == rootNode) {
            newNode = new RootBinaryNode(people, createDate);
            rootNode = newNode;
            fastNextNode = newNode;
        } else {
            if (!(fastNextNode.leftIsEmpty() || fastNextNode.rightIsEmpty())) {
                levelOrderTraverse();
            }
            newNode = new RegularBinaryNode(people, createDate, fastNextNode, fastNextNode);
            fastNextNode.autoMountNode(newNode);
            newNode.setRelationshipFlag();
        }
        flash(newNode, createDate);
    }

    private BinaryNode findNodeByID(Integer nodeID) {
        if (null != rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(rootNode);
            BinaryNode node;
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (node.getContent().getID().equals(nodeID)) {
                    return node;
                }
                if (!node.leftIsEmpty()) {
                    queue.offer(node.getLeft());
                }
                if (!node.rightIsEmpty()) {
                    queue.offer(node.getRight());
                }
            }
        }
        return null;
    }

    private void flash(BinaryNode newNode, Date date) {
        initialFee.add(date, newNode.getContent());
        flashNodes(newNode, date);
    }

    private void flashNodes(BinaryNode newNode, Date date) {
        if (null != rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(rootNode);
            BinaryNode node;
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (null == newNode.getFather()) {
                    continue;
                }
                BinaryNode flashAncestor = getFlashAncestor(node, newNode);
                if (null != flashAncestor) {

                    BinaryNode theNode = (null == flashAncestor.getFather() ? flashAncestor : flashAncestor.getFather());
                    theNode.addCounselingFee(date);
                    flashAncestor.addOperatingExpenses(date);

                    for (Relationship r1 : node.getRelationshipSet()) {
                        if (r1.getId().equals(flashAncestor.getContent().getID())) {
                            r1.setFlashed(true);
                            break;
                        }
                    }
                    for (Relationship r2 : newNode.getRelationshipSet()) {
                        if (r2.getId().equals(flashAncestor.getContent().getID())) {
                            r2.setFlashed(true);
                            break;
                        }
                    }
                }

                if (!node.leftIsEmpty()) {
                    queue.offer(node.getLeft());
                }
                if (!node.rightIsEmpty()) {
                    queue.offer(node.getRight());
                }
            }
        }
    }

    private BinaryNode getFlashAncestor(BinaryNode node, BinaryNode newNode) {
        if (node.getLevel() == newNode.getLevel() && !node.equals(newNode)) {
            for (Relationship newNodeAncestor : newNode.getRelationshipSet()) {
                for (Relationship nodeAncestor : node.getRelationshipSet()) {
                    if (null == node.getFather()) {
                        continue;
                    } else {
                        Integer nodefatherID = nodeAncestor.getId();
                        Integer newNodeFatherID = newNodeAncestor.getId();
                        Boolean nodefatherFlashed = node.flashedForAncestor(nodefatherID);
                        Boolean newNodeFatherFlashed = newNode.flashedForAncestor(newNodeFatherID);
                        if (nodefatherID.equals(newNodeFatherID) && !nodefatherFlashed && !newNodeFatherFlashed
                                && !nodeAncestor.getFlag().equals(newNodeAncestor.getFlag())) {
                            return findNodeByID(nodefatherID);
                        }
                    }
                }
            }
        }
        return null;
    }

    public void printBill() {
        StringBuilder str = new StringBuilder();
        str.append(initialFee.sum()).append(",");
        str.append(operatingExpensesSum).append(",");
        str.append(counselingFeeSum).append(",");
        str.append(
                String.format("%.2f%%", (double) (operatingExpensesSum + counselingFeeSum) / initialFee.sum()
                        * 100)).append(",");
        str.append(this.toString()).append(",");
        str.append(rootNode.getOperatingExpenses() + rootNode.getCounselingFee());
        System.out.println(str.toString());
    }

    public void printNode2(BinaryNode node) {
        StringBuilder str = new StringBuilder();
        str.append(node);
        str.append("\t").append(node.getContent().name()).append("\t");
        if (null != node) {
            str.append(node.getCounselingFee()).append("\t");
            str.append(node.getOperatingExpenses());
        }
        System.out.println(str.toString());
    }

    public void printNode(BinaryNode node) {
        StringBuilder str = new StringBuilder();
        str.append(node);
        str.append(" ").append(node.getContent().name());
        if (null != node) {
            str.append(",");
            str.append("Fa:").append(node.getFather()).append(",");
            str.append("Le:").append(node.getLevel()).append(",");
            str.append("L:").append(node.getLeft()).append(",");
            str.append("R:").append(node.getRight()).append(",");
            str.append(node.getCounselingFee()).append(",");
            str.append(node.getOperatingExpenses()).append(",");
            str.append("[");
            for (Relationship r : node.getRelationshipSet()) {
                str.append(r.getId()).append(", ");
            }
            str.append("]").append(",");
            str.append(node.getCreateDate());
        }
        System.out.println(str.toString());
    }

    public void printNode() {
        if (null != rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(rootNode);
            BinaryNode node;
            while (!queue.isEmpty()) {
                node = queue.poll();
                printNode2(node);
                operatingExpensesSum += node.getOperatingExpenses();
                counselingFeeSum += node.getCounselingFee();
                if (!node.leftIsEmpty()) {
                    queue.offer(node.getLeft());
                }
                if (!node.rightIsEmpty()) {
                    queue.offer(node.getRight());
                }
            }
        }
    }

    private void levelOrderTraverse() {
        if (null != rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(rootNode);
            BinaryNode node;
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (node.leftIsEmpty()) {
                    fastNextNode = node;
                    break;
                } else {
                    if (node.rightIsEmpty()) {
                        fastNextNode = node;
                        break;
                    }
                }
                if (!node.leftIsEmpty()) {
                    queue.offer(node.getLeft());
                }
                if (!node.rightIsEmpty()) {
                    queue.offer(node.getRight());
                }
            }
        }
    }
}
