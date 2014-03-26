package net.earthcoder.jmlm.domain;

public class BinaryPlanTest2 {
	
    public static void main(String[] args) {
        BinaryTree plan = new BinaryTree();

        plan.addNode(new People(1, "（1）"), null, null);
        plan.addNode(new People(2, "（2）"), 1, 1, "LEFT");
        plan.addNode(new People(3, "（3）"), 2, 2, "LEFT");
        plan.addNode(new People(4, "（4）"), 3, 3, "LEFT");
        plan.addNode(new People(5, "（5）"), 4, 4, "LEFT");
        plan.addNode(new People(6, "（6）"), 5, 5, "LEFT");

        plan.printNode();
        plan.printBill();
    }
}
