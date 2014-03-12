package net.earthcoder.ganoderma.struct;

import java.util.*;

import net.earthcoder.ganoderma.Relationship;
import net.earthcoder.ganoderma.fee.*;
import net.earthcoder.ganoderma.man.*;

public final class BinaryTree<E extends Human> extends Tree<E> {

    private BinaryNode<E> rootNode;
    private BinaryNode<E> fastNextNode;
    private long operatingExpensesSum;
    private long counselingFeeSum;
    private Fee<E> initialFee = new InitialFee<E>();

    private BinaryNode<E> findNodeByID(Integer id) {
        if (null != rootNode) {
            Queue<BinaryNode<E>> queue = new LinkedList<BinaryNode<E>>();
            queue.offer(rootNode);
            BinaryNode<E> node = null;
            while (!queue.isEmpty()) {
                node = queue.poll();
                if (node.getContent().getID().equals(id)) {
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

    private BinaryNode<E> getFlashAncestor(BinaryNode<E> node, BinaryNode<E> newNode) {
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
                        if (nodefatherID == newNodeFatherID && !nodefatherFlashed && !newNodeFatherFlashed
                                && !nodeAncestor.getFlag().equals(newNodeAncestor.getFlag())) {
                            return findNodeByID(nodefatherID);
                        }
                    }
                }
            }
        }
        return null;
    }

    private void flashNodes(BinaryNode<E> newNode, Date date) {
        if (null != this.rootNode) {
            Queue<BinaryNode<E>> queue = new LinkedList<BinaryNode<E>>();
            queue.offer(this.rootNode);
            BinaryNode<E> node = null;
            while (!queue.isEmpty()) {
                node = queue.poll();

                if (null == newNode.getFather()) {
                    continue;
                }
                BinaryNode<E> flashAncestor = getFlashAncestor(node, newNode);
                if (null != flashAncestor) {

                    BinaryNode<E> theNode = (null == flashAncestor.getFather() ? flashAncestor : flashAncestor.getFather());
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

    private void flash(BinaryNode<E> newNode, Date date) {
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

    public void addNode(E people, Date crateDate, Integer fatherNodeID, String flag) {
        BinaryNode<E> newNode = null;
        if (null == rootNode) {
            newNode = new BinaryNode<E>(people, null, crateDate);
            setRoot(newNode);
        } else if (null != fatherNodeID) {
            BinaryNode<E> fatherNode = this.findNodeByID(fatherNodeID);
            if (null == fatherNode) {
                throw new RuntimeException("Father not find.");
            } else {
                newNode = new BinaryNode<E>(people, fatherNode, crateDate);
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

    @Override
    public void addNode(E people, Date crateDate) {
        BinaryNode<E> newNode = null;
        if (null == rootNode) {
            newNode = new BinaryNode<E>(people, null, crateDate);
            setRoot(newNode);
            fastNextNode = rootNode;
        } else {
            if (fastNextNode.leftIsEmpty() || fastNextNode.rightIsEmpty()) {
                ;
            } else {
                levelOrderTraverse();
            }
            newNode = new BinaryNode<E>(people, fastNextNode, crateDate);
            fastNextNode.autoLoad(newNode);
            if (newNode == newNode.getFather().getLeft()) {
                newNode.setRelationshipFlag(newNode.getFather().getContent().getID(), "LEFT");
            }
            if (newNode == newNode.getFather().getRight()) {
                newNode.setRelationshipFlag(newNode.getFather().getContent().getID(), "RIGHT");
            }
        }
        flash(newNode, crateDate);
    }
    
    protected void printNode2(BinaryNode<E> node) {
        StringBuilder str = new StringBuilder();
        str.append(node);
        str.append("\t" + node.getContent().name() + "\t");
        if (null != node) {
            str.append(node.getCounselingFee()).append("\t");
            str.append(node.getOperatingExpenses());
        }
        System.out.println(str.toString());
    }

    protected void printNode(BinaryNode<E> node) {
        StringBuilder str = new StringBuilder();
        str.append(node);
        str.append(" " + node.getContent().name());
        if (null != node) {
            
            str.append(",");
            str.append("Fa:" + node.getFather()).append(",");
            str.append("Le:" + node.getLevel()).append(",");
            str.append("L:" + node.getLeft()).append(",");
            str.append("R:" + node.getRight()).append(",");
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
            Queue<BinaryNode<E>> queue = new LinkedList<BinaryNode<E>>();
            queue.offer(this.rootNode);
            BinaryNode<E> node = null;
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
            Queue<BinaryNode<E>> queue = new LinkedList<BinaryNode<E>>();
            queue.offer(rootNode);
            BinaryNode<E> node = null;
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

    private void setRoot(BinaryNode<E> rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public void print() {
        this.printNode();
        this.printBill();
    }
}
