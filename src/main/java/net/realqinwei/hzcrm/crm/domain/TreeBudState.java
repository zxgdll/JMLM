package net.realqinwei.hzcrm.crm.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import net.realqinwei.hzcrm.crm.domain.exception.*;

public final class TreeBudState<T> extends State<T> {
	
	public TreeBudState(TreeComponent<T> treeComponent) {
		super(treeComponent);
	}

	@Override
	public void addComponent(T refer, T value) throws AddErrorException {
		super.getTree().leafState().addComponent(refer, value);
	}

	@Override
	public List<T> branchs(TreeComponent<T> tree) {

		List<T> list = new ArrayList<T>();
		list.add(tree.getValue());
		return list;
	}

	@Override
	public List<T> leafs(TreeComponent<T> tree) {

		return new ArrayList<T>();
	}

	@Override
	public TreeComponent<T> find(T value) {
		return super.getTree().getValue().equals(value) ? super.getTree() : null;
	}

	@Override
	public int[] getChildsLoadTable() {
		return super.getTree().leafState().getChildsLoadTable();
	}

	@Override
	public void mountIt(TreeComponent<T> refer, T value) {
		super.getTree().setChild();
		super.getTree().mount(super.getTree(), refer, value);
	}

	@Override
	public void update(Observable observable) throws UpdateErrorException {
		super.getTree().setState(super.getTree().branchState());
		TreeComponent<T> newTree = (TreeComponent<T> ) observable;
		newTree.billList.add(new Bill<T>(newTree.billCount++, super.getTree().getValue(), 0, super.getTree().getBonus(newTree)));
	}
}
// ~~