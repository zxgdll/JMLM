package net.earthcoder.jmlm.domain;

import java.util.*;
import static java.lang.System.out;

public final class BinaryTree {

    private BinaryNode rootNode;
    private long operatingExpensesSum;
    private long counselingFeeSum;
    private long initialFeeSum;
    private List<BinaryNode> nodes;

    public static BinaryTree getNewTree() {
        return new BinaryTree();
    }

    private BinaryTree() {
        nodes = new ArrayList<BinaryNode>();
    }

    public void addNode(Human point, Integer referPointID, Integer fatherPointID) {
        BinaryNode newNode;
        if (null == rootNode) {
            rootNode = newNode = new RootBinaryNode(point);
        } else if (null == fatherPointID) {
            throw new RuntimeException("No Father ID, can not add node.");
        } else {
            BinaryNode fatherNode = findNodeByID(fatherPointID);
            if (null == fatherNode) {
                throw new RuntimeException("Father not find.");
            } else {
                newNode = new RegularBinaryNode(point, findNodeByID(referPointID), fatherNode);
            }
            fatherNode.autoMountNode(newNode);
            newNode.setRelationshipFlag();
        }
        nodes.add(newNode);
        flash(newNode);
    }

    private BinaryNode findNodeByID(Integer nodeID) {
        for (BinaryNode binaryNode: nodes) {
            if (binaryNode.getContent().nodeID().equals(nodeID)) {
                return binaryNode;
            }
        }
        return null;
    }

    private void flash(BinaryNode newNode) {
        newNode.addInitialFee();
        updateResults(newNode);
        flashNodes(newNode);
    }

    private void updateResults(BinaryNode newNode) {
        BinaryNode node;
        for (Relationship relation: newNode.getRelationshipSet()) {
            node = findNodeByID(relation.getId());
            if ("LEFT".equals(relation.getFlag())) {
                node.leftResultAdd(node.getInitialFee());
            } else if ("RIGHT".equals(relation.getFlag())) {
                node.rightResultAdd(node.getInitialFee());
            }
        }
    }

    public Map<Date, List<BillItem>> getDailyBillList() {
        Map<Date, List<BillItem>> map = new TreeMap<Date, List<BillItem>>();
        Map<Date, List<BillItem>> nodeMap;
        for (BinaryNode node : nodes) {
            nodeMap = node.getBillList();
            for (Date date: nodeMap.keySet()) {
                if (map.containsKey(date)) {
                    map.get(date).addAll(nodeMap.get(date));
                } else {
                    map.put(date, nodeMap.get(date));
                }
            }
        }
        return map;
    }

    protected void flashNodes(BinaryNode newNode) {
        if (null != rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(rootNode);
            BinaryNode currentNode;
            BinaryNode flashAncestor;
            while (!queue.isEmpty()) {
                currentNode = queue.poll();
                if (null == newNode.getFather()) {
                    continue;
                }
                flashAncestor = getFlashAncestor(currentNode, newNode);
                if (null != flashAncestor) {
                    flashAncestor.addOperatingExpenses(newNode.getCreateDate());

                    BinaryNode theNode = (null == flashAncestor.getRefer() ? flashAncestor : flashAncestor.getRefer());
                    theNode.addCounselingFee(newNode.getCreateDate());

                    for (Relationship r1 : currentNode.getRelationshipSet()) {
                        if (r1.getId().equals(flashAncestor.getContent().nodeID())) {
                            r1.setFlashed(true);
                            break;
                        }
                    }
                    for (Relationship r2 : newNode.getRelationshipSet()) {
                        if (r2.getId().equals(flashAncestor.getContent().nodeID())) {
                            r2.setFlashed(true);
                            break;
                        }
                    }
                }
                if (!currentNode.leftIsEmpty()) {
                    queue.offer(currentNode.getLeft());
                }
                if (!currentNode.rightIsEmpty()) {
                    queue.offer(currentNode.getRight());
                }
            }
        }
    }

    private BinaryNode getFlashAncestor(BinaryNode node, BinaryNode newNode) {
        if (node.getLevel() == newNode.getLevel() && !node.equals(newNode)) {
            for (Relationship newNodeAncestor : newNode.getRelationshipSet()) {
                for (Relationship nodeAncestor : node.getRelationshipSet()) {
                    if (null != node.getFather()) {
                        Integer nodeFatherID = nodeAncestor.getId();
                        Integer newNodeFatherID = newNodeAncestor.getId();
                        Boolean nodefatherFlashed = node.flashedForAncestor(nodeFatherID);
                        Boolean newNodeFatherFlashed = newNode.flashedForAncestor(newNodeFatherID);
                        if (nodeFatherID.equals(newNodeFatherID) && !nodefatherFlashed && !newNodeFatherFlashed
                                && !nodeAncestor.getFlag().equals(newNodeAncestor.getFlag())) {
                            return findNodeByID(nodeFatherID);
                        }
                    }
                }
            }
        }
        return null;
    }

    public void printBill() {
        out.printf("%10d%10d%10d%6.2f%%%10d", initialFeeSum, counselingFeeSum, operatingExpensesSum,
                (double) (operatingExpensesSum + counselingFeeSum) / initialFeeSum * 100,
                (null == rootNode ? 0 : rootNode.getOperatingExpenses()) + (null == rootNode ? 0 : rootNode.getCounselingFee()));
    }

    public void printNode(BinaryNode node) {
        out.printf("%-4s %-4s %-3d %-4s %-4s%8d%8d%8d%8d%8d%8d %-16s\n",
                node.getContent(),
                node.getFather(), node.getLevel(),
                node.getLeft(), node.getRight(),
                node.getLeftResults(), node.getRightResults(),
                node.getLeftCurrent(), node.getRightCurrent(),
                node.getOperatingExpenses(), node.getCounselingFee(),
                node.getRelationshipSet());
    }

    public void printNode() {
        out.printf("%-4s %-4s %-3s %-4s %-4s%8s%8s%8s%8s%8s%8s %-16s\n",
                "ID", "FTHR", "LVL", "LEFT", "RGHT", "L_R", "R_R", "L_C", "R_C", "O_FEE", "C_FEE",
                "RELATION");
        if (null != rootNode) {
            Queue<BinaryNode> queue = new LinkedList<BinaryNode>();
            queue.offer(rootNode);
            BinaryNode node;
            while (!queue.isEmpty()) {
                node = queue.poll();
                printNode(node);
                operatingExpensesSum += node.getOperatingExpenses();
                counselingFeeSum += node.getCounselingFee();
                initialFeeSum += node.getInitialFee();
                if (!node.leftIsEmpty()) {
                    queue.offer(node.getLeft());
                }
                if (!node.rightIsEmpty()) {
                    queue.offer(node.getRight());
                }
            }
        }
    }

    public List<BinaryNode> getNodes() {
        return nodes;
    }

    public BinaryNode getRootNode() {
        return rootNode;
    }
}
