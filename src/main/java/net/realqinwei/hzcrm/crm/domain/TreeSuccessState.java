package net.realqinwei.hzcrm.crm.domain;

import java.util.List;
import java.util.Observable;

import net.realqinwei.hzcrm.crm.domain.exception.*;

public final class TreeSuccessState<T> extends State<T> {

	private static final long serialVersionUID = 1184431890798903930L;
	
	public TreeSuccessState(TreeComponent<T> treeComponent) {
		super(treeComponent);
	}
	
	@Override
	public void addComponent(T refer, T value) throws AddErrorException {
		super.getTree().branchState().addComponent(refer, value);
	}

	@Override
	public List<T> branchs(TreeComponent<T> tree) {
		return super.getTree().branchState().branchs(tree);
	}

	@Override
	public List<T> leafs(TreeComponent<T> tree) {
		return super.getTree().branchState().leafs(tree);
	}

	@Override
	public TreeComponent<T> find(T value) {
		return super.getTree().branchState().find(value);
	}
	
	@Override
	public int[] getChildsLoadTable() {
		return super.getTree().branchState().getChildsLoadTable();
	}

	@Override
	public void mountIt(TreeComponent<T> refer, T value) {
		TreeComponent<T> tree = super.getTree();
		tree.branchState().mountIt(tree, value);
	}

	@Override
	public void update(Observable observable) throws UpdateErrorException {
	
		throw new UpdateErrorException("successful should not be notified anything");
	}
}
//~