package net.earthcoder.jmlm.domain;

import java.util.*;

public final class BinaryTree implements Tree {

    private BinaryNode rootNode;
    private BinaryNode fastNextNode;
    private long operatingExpensesSum;
    private long counselingFeeSum;
    private Fee initialFee = new InitialFee();

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

    private void flashNodes(BinaryNode newNode, Date date) {
        if (null != this.rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(this.rootNode);
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

    private void flash(BinaryNode newNode, Date date) {
        initialFee.add(date, newNode.getContent());
        flashNodes(newNode, date);
    }

    protected void printBill() {
        StringBuilder str = new StringBuilder();
        str.append(this.initialFee.sum()).append(",");
        str.append(this.operatingExpensesSum).append(",");
        str.append(this.counselingFeeSum).append(",");
        str.append(
                String.format("%.2f%%", (double) (this.operatingExpensesSum + this.counselingFeeSum) / this.initialFee.sum()
                        * 100)).append(",");
        str.append(this.toString()).append(",");
        str.append(this.rootNode.getOperatingExpenses() + this.rootNode.getCounselingFee());
        System.out.println(str.toString());
    }

    public void addNode(Human people, Date crateDate, Integer fatherNodeID, String flag) {
        BinaryNode newNode = null;
        if (null == rootNode) {
            newNode = new RootBinaryNode(people, crateDate);
            rootNode = newNode;
        } else if (null != fatherNodeID) {
            BinaryNode fatherNode = this.findNodeByID(fatherNodeID);
            if (null == fatherNode) {
                throw new RuntimeException("Father not find.");
            } else {
                newNode = new RegularBinaryNode(people, crateDate, fatherNode);
            }
            if ("L".equals(flag)) {
                fatherNode.leftLoad(newNode);
            } else if ("R".equals(flag)) {
                fatherNode.rightLoad(newNode);
            } else {
                throw new RuntimeException("Wrong flag.");
            }

            if (newNode == newNode.getFather().getLeft()) {
                newNode.setRelationshipFlag(newNode.getFather().getContent().getID(), "LEFT");
            }
            if (newNode == newNode.getFather().getRight()) {
                newNode.setRelationshipFlag(newNode.getFather().getContent().getID(), "RIGHT");
            }
        }
        flash(newNode, crateDate);
    }

    public void addNode(Human people, Date crateDate) {
        BinaryNode newNode;
        if (null == rootNode) {
            newNode = new RootBinaryNode(people, crateDate);
            rootNode = newNode;
            fastNextNode = rootNode;
        } else {
            if (!(fastNextNode.leftIsEmpty() || fastNextNode.rightIsEmpty())) {
                levelOrderTraverse();
            }
            newNode = new RegularBinaryNode(people, crateDate, fastNextNode);
            fastNextNode.autoMountNode(newNode);
            if (newNode == newNode.getFather().getLeft()) {
                newNode.setRelationshipFlag(newNode.getFather().getContent().getID(), "LEFT");
            }
            if (newNode == newNode.getFather().getRight()) {
                newNode.setRelationshipFlag(newNode.getFather().getContent().getID(), "RIGHT");
            }
        }
        flash(newNode, crateDate);
    }

    protected void printNode2(BinaryNode node) {
        StringBuilder str = new StringBuilder();
        str.append(node);
        str.append("\t").append(node.getContent().name()).append("\t");
        if (null != node) {
            str.append(node.getCounselingFee()).append("\t");
            str.append(node.getOperatingExpenses());
        }
        System.out.println(str.toString());
    }

    protected void printNode(BinaryNode node) {
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

    protected void printNode() {
        if (null != this.rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(this.rootNode);
            BinaryNode node;
            while (!queue.isEmpty()) {
                node = queue.poll();
                printNode2(node);

                this.operatingExpensesSum += node.getOperatingExpenses();
                this.counselingFeeSum += node.getCounselingFee();

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

    public void print() {
        this.printNode();
        this.printBill();
    }
}
