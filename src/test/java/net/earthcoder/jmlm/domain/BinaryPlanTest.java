package net.earthcoder.jmlm.domain;

import java.util.Calendar;

public class BinaryPlanTest {
	
	private static final int DAYS = 40;
	private static final int PEOPLE_PER_DAY = 25;
	
    public static void main(String[] args) {
        BinaryTree plan = new BinaryTree();
        Calendar calendar = Calendar.getInstance();
        
        int peopleID = 0;
        for (int day = 1; day <= DAYS; day++) {
            for (int people = 0; people < PEOPLE_PER_DAY; people++) {
                plan.addNode(new People(peopleID), calendar.getTime());
                peopleID++;
            }
            calendar.add(Calendar.DATE, 1);
        }
        plan.printNode();
        plan.printBill();
    }
}
