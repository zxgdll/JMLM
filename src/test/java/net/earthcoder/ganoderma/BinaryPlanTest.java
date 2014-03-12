package net.earthcoder.ganoderma;

import java.util.*;

import net.earthcoder.ganoderma.man.People;
import net.earthcoder.ganoderma.struct.BinaryTree;
import net.earthcoder.ganoderma.struct.Tree;

public class BinaryPlanTest {
	
	private static final int DAYS = 40;
	private static final int PEOPLE_PER_DAY = 25;
	
    public static void main(String[] args) {
    	Tree<People> plan = new BinaryTree<People>();
        Calendar calendar = Calendar.getInstance();
        
        int peopleID = 0;
        for (int day = 1; day <= DAYS; day++) {
            for (int people = 0; people < PEOPLE_PER_DAY; people++) {
                plan.addNode(new People(peopleID), calendar.getTime());
                peopleID++;
            }
            calendar.add(Calendar.DATE, 1);
        }
        plan.print();
    }
}
