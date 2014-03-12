package net.realqinwei.hzcrm.crm.domain;

import java.util.List;
import java.util.Observable;

import net.realqinwei.hzcrm.crm.domain.exception.*;

public final class TreeEmptyState<T> extends State<T> {

	private static final long serialVersionUID = -1517003858465074832L;
	
	public TreeEmptyState(TreeComponent<T> treeComponent) {
		super(treeComponent);
	}

	/**
	 * 空节点添加内容时，直接放入值
	 */
	@Override
	public void addComponent(T refer, T value) {
		TreeComponent<T> tree = super.getTree();
		tree.setValue(value);
		tree.setState(tree.budState());
		tree.billList.add(new Bill<T>(tree.billCount++, tree.getValue(), HuazhiFee.JOIN_FEE, 0));
	}

	@Override
	public List<T> branchs(TreeComponent<T> tree) {
		State<T> state = tree.leafState();
		return state.branchs(tree);
	}

	@Override
	public List<T> leafs(TreeComponent<T> tree) {
		return null;
	}

	@Override
	public TreeComponent<T> find(T value) {
		return null;
	}

	@Override
	public int[] getChildsLoadTable() {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return super.getTree().leafState().getChildsLoadTable();
	}

	@Override
	public void mountIt(TreeComponent<T> refer, T value) {
		
	}

	@Override
	public void update(Observable observable) throws UpdateErrorException {

		throw new UpdateErrorException("空节点被通知了！");
	}
}
// ~