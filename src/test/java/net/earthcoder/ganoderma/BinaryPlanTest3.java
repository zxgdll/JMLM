package net.earthcoder.ganoderma;


import java.util.*;

import net.earthcoder.ganoderma.man.People;
import net.earthcoder.ganoderma.struct.BinaryTree;
import net.earthcoder.ganoderma.struct.Tree;

public class BinaryPlanTest3 {
    
    public static final People[] PEOPLES;
    
    static {
        PEOPLES = new People[Test.PEOPLE_NAMES.length];
        for (int i = 0; i < PEOPLES.length; i++) {
            PEOPLES[i] = new People(i, Test.PEOPLE_NAMES[i]);
        }
    }
	
    public static void main(String[] args) {
    	Tree<People> plan = new BinaryTree<People>();
        Calendar calendar = Calendar.getInstance();


        
        
        plan.print();
    }
}
