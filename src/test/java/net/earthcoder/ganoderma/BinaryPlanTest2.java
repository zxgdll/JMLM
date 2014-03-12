package net.earthcoder.ganoderma;


import java.util.*;

import net.earthcoder.ganoderma.man.People;
import net.earthcoder.ganoderma.struct.BinaryTree;
import net.earthcoder.ganoderma.struct.Tree;

public class BinaryPlanTest2 {
	
    public static void main(String[] args) {
    	Tree<People> plan = new BinaryTree<People>();
        Calendar calendar = Calendar.getInstance();

        plan.addNode(new People(1, "于景威（1）"), calendar.getTime(), null, null);
        plan.addNode(new People(2, "于景威（2）"), calendar.getTime(), 1, "L");
        plan.addNode(new People(3, "于景威（3）"), calendar.getTime(), 2, "L");
        plan.addNode(new People(4, "于景威（4）"), calendar.getTime(), 3, "L");
        plan.addNode(new People(5, "于景威（5）"), calendar.getTime(), 4, "L");
        plan.addNode(new People(6, "于景威（6）"), calendar.getTime(), 5, "L");
        plan.addNode(new People(7, "于景威（7）"), calendar.getTime(), 6, "L");
        plan.addNode(new People(8, "于景威（8）"), calendar.getTime(), 7, "L");
        plan.addNode(new People(9, "于景威（9）"), calendar.getTime(), 8, "L");
        plan.addNode(new People(10, "于景威（10）"), calendar.getTime(), 9, "L");
        plan.addNode(new People(11, "于景威（11）"), calendar.getTime(), 10, "L");
        plan.addNode(new People(12, "于景威（12）"), calendar.getTime(), 11, "L");
        plan.addNode(new People(13, "于景威（13）"), calendar.getTime(), 12, "L");
        plan.addNode(new People(14, "于景威（14）"), calendar.getTime(), 13, "L");
        plan.addNode(new People(15, "于景威（15）"), calendar.getTime(), 14, "L");
        plan.addNode(new People(16, "于景威（16）"), calendar.getTime(), 15, "L");
        plan.addNode(new People(17, "于景威（17）"), calendar.getTime(), 16, "L");
        plan.addNode(new People(18, "于景威（14）"), calendar.getTime(), 17, "L");
        plan.addNode(new People(19, "于景威（15）"), calendar.getTime(), 18, "L");
        plan.addNode(new People(20, "于景威（16）"), calendar.getTime(), 19, "L");
        plan.addNode(new People(21, "于景威（17）"), calendar.getTime(), 20, "L");
        
        plan.addNode(new People(22, "王凤枝（1）(18)"), calendar.getTime(), 21, "L");
        plan.addNode(new People(23, "李云英（1）(6)"), calendar.getTime(), 21, "R");
        
        
        
        plan.print();
    }
}
