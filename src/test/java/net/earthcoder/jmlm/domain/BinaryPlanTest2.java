package net.earthcoder.jmlm.domain;

public class BinaryPlanTest2 {
	
    public static void main(String[] args) {
        BinaryTree plan = BinaryTree.getNewTree();

        plan.addNode(new People(1), null, null);
        plan.addNode(new People(2), 1, 1);
        plan.addNode(new People(3), 1, 1);

        plan.addNode(new People(4), 2, 2);
        plan.addNode(new People(5), 2, 2);

        plan.printNode();
        plan.printBill();
    }
}
