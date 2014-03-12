package net.realqinwei.hzcrm.crm.domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;

import net.realqinwei.hzcrm.crm.domain.exception.AddErrorException;
import net.realqinwei.hzcrm.crm.domain.exception.UpdateErrorException;

public final class TreeBranchState<T> extends State<T> {

	private static final long serialVersionUID = 5578768434845662089L;
	
	public TreeBranchState(TreeComponent<T> treeComponent) {
		super(treeComponent);
	}

	@Override
	public final void addComponent(T refer, T value) throws AddErrorException {

		TreeComponent<T> referTree = super.getTree().find(refer);
		if (null == referTree) {
			throw new AddErrorException("refer can not be found");
		} else if (super.getTree().contains(value)) {
			throw new AddErrorException("CAN'T add value who has been in the tree");
		} else {
			referTree.mountIt(referTree, value);
		}
	}

	@Override
	public TreeComponent<T> find(T value) {
		if (super.getTree().getValue().equals(value)) {
			return super.getTree();
		} else {
			TreeComponent<T> tmp = null;
			for (Iterator<TreeComponent<T>> iterator = super.getTree().getChilds().iterator(); iterator.hasNext() && null == tmp;) {
				tmp = iterator.next().find(value);
			}
			return tmp;
		}
	}

	@Override
	public List<T> branchs(TreeComponent<T> tree) {

		List<T> list = new ArrayList<T>();
		list.add(tree.getValue());
		for (TreeComponent<T> child : tree.getChilds()) {
			list.addAll(child.branchs());
		}
		return list;
	}

	@Override
	public List<T> leafs(TreeComponent<T> tree) {

		List<T> leafs = new ArrayList<T>();
		for (TreeComponent<T> child : tree.getChilds()) {
			leafs.addAll(child.leafs());
		}
		return leafs;
	}

	/**
	 * @return 子节点负重列表，即当前节点的三个孩子，他们各自的负重
	 */
	@Override
	public int[] getChildsLoadTable() {
		TreeComponent<T> tree = super.getTree();
		int[] childsLoadTable = {0, 0, 0};
		for (int i = 0; i < tree.getChilds().size(); i++) {
			childsLoadTable[i] = tree.getChilds().get(i).load();
		}
		return childsLoadTable;
	}

	@Override
	public void mountIt(TreeComponent<T> refer, T value) {
		TreeComponent<T> tree = super.getTree();
		
		// 此处可以说是最核心的代码
		// flag = 0 时，说明要插左节点；要判断左节点是否为空，就要判断 size == 0 ？
		// flag = 1 时，说名要插中节点；要判断中节点是否为空，就要判断 size == 1 ？
		// flag = 2 时，说明要插右节点；要判断右节点是否为空，就要判断 size == 2 ？
		// 综上，只要判断 flag == size 即可
		// 也可以这样写，因为业务要求的顺序正好也是从左往右，与 add 的顺序一致
		// loader.childs.set(flag, newTree);
		
		// 此处的逻辑依赖一个隐含的条件：每个节点下的子女插入顺序是从左往右（即，第一个插入的子女肯定是左孩子，
		// 第二个插入的子女肯定是中间孩子，第三个插入的子女肯定是右孩子）

		int flag = tree.findTheOne(tree.getState().getChildsLoadTable());
		if (flag == tree.getChilds().size()) {
			tree.mount(tree, refer, value);
		} else {
			tree.getChilds().get(flag).mountIt(refer, value);
		}
	}

	@Override
	public void update(Observable observable) throws UpdateErrorException {
		TreeComponent<T> tree = super.getTree();
		if (tree.childs(tree) == 121) {
			tree.setState(tree.successState());
		}
		TreeComponent<T> newTree = (TreeComponent<T> ) observable;
		newTree.billList.add(new Bill<T>(newTree.billCount++, tree.getValue(), 0, tree.getBonus(newTree)));
	}
}
// ~