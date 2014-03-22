package net.earthcoder.jmlm.domain;

import java.util.Date;

public abstract class Tree<E> {
	
	protected Tree<E> root;
	
    public Tree<E> getRoot() {
		return root;
	}
    
    public abstract void addNode(E people, Date crateDate);
    public abstract void addNode(E people, Date crateDate, Integer fatherNodeID, String flag);
    public abstract void print();
}
