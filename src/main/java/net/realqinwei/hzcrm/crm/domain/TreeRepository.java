package net.realqinwei.hzcrm.crm.domain;

import java.util.SortedSet;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.domain.exception.AddErrorException;

public final class TreeRepository {

	private TreeFactory treeFactory;
	
	public void setTreeFactory(TreeFactory treeFactory) {
		this.treeFactory = treeFactory;
	}
	
	public TreeFactory getTreeFactory() {
		return treeFactory;
	}
	
	public TreeComponent<Node> getTree() {
		TreeComponent<Node> tree = null;
		try {
			tree = this.getTreeFactory().getTree();
		} catch (AddErrorException e) {
			e.printStackTrace();
		}
		return tree;
	}
	
	public SortedSet<Node> getBill() {
		return this.getTreeFactory().getBill();
	}
}
