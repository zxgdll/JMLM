package net.realqinwei.hzcrm.crm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import net.realqinwei.hzcrm.crm.domain.exception.*;

public final class TreeLeafState<T> extends State<T> {

	private static final long serialVersionUID = -7147953332651528095L;
	
	private static final int[] EMPTY_LOAD_TABLE = {0, 0, 0};
	
	public TreeLeafState(TreeComponent<T> treeComponent) {
		super(treeComponent);
	}
	
	@Override
	public void addComponent(T refer, T value) throws AddErrorException {
		if (null == refer) {
			throw new AddErrorException("A refer who can not be found in tree CAN'T refer other new value.");
		}
		TreeComponent<T> referTree = super.getTree().find(refer);
		if (null == referTree | super.getTree().contains(value)) {
			return;
		}
		referTree.mountIt(referTree, value);
	}

	@Override
	public List<T> branchs(TreeComponent<T> tree) {
		List<T> leafs = new ArrayList<T>();
		leafs.add(tree.getValue());
		return leafs;
	}

	@Override
	public List<T> leafs(TreeComponent<T> tree) {
		List<T> leafs = new ArrayList<T>();
		leafs.add(tree.getValue());
		return leafs;
	}

	@Override
	public TreeComponent<T> find(T value) {
		return super.getTree().getValue().equals(value) ? super.getTree() : null;
	}

	@Override
	public int[] getChildsLoadTable() {
		return EMPTY_LOAD_TABLE;
	}

	@Override
	public void mountIt(TreeComponent<T> refer, T value) {
		TreeComponent<T> tree = super.getTree();
		int flag = tree.findTheOne(tree.getState().getChildsLoadTable());
		tree.setChild();
		if (flag == tree.getChilds().size()) {
			tree.mount(tree, refer, value);
			tree.setState(tree.branchState());
		}
	}

	@Override
	public void update(Observable observable) throws UpdateErrorException {
		super.getTree().setState(super.getTree().branchState());
		TreeComponent<T> newTree = (TreeComponent<T> ) observable;
		newTree.billList.add(new Bill<T>(newTree.billCount++, super.getTree().getValue(), 0, super.getTree().getBonus(newTree)));
	}
}
// ~