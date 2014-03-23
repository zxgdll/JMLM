package net.realqinwei.hzcrm.crm.domain;

import java.io.Serializable;

import java.util.*;

import net.earthcoder.jmlm.domain.Human;
import net.earthcoder.jmlm.domain.Tree;
import net.realqinwei.hzcrm.crm.domain.exception.AddErrorException;
import net.realqinwei.hzcrm.crm.domain.exception.UpdateErrorException;

/**
 * 
 * @author qinwei
 *
 * @param <T>
 */
public final class TreeComponent<T> extends Observable implements Serializable, Observer, Tree {
	
	private static final int MAX_OBSERVE_LEVEL = 5;

	// 计算节点处于第几层时，以根节点作为第1层
	private static final int ROOT_LEVEL = 1;

	// 在计算A节点相对于B节点处于第几层时，B节点作为相对根节点，即第1层
	private static final int RELATIVE_ROOT_LEVEL = 1;

	// 每个节点自身的重量。
	private static final int WEIGHT_OF_NODE = 1;

	// /////////////////////////////////////////////////////////////////////////
	//
	// Fields
	//
	protected int billCount = 1;
	protected int bonus;
	private T value;
	private TreeComponent<T> refer;
	private TreeComponent<T> parent;
	private List<TreeComponent<T>> childs;
	private String message;
	protected List<Bill<T>> billList = new ArrayList<Bill<T>>();
	
	public List<Bill<T>> getBillList() {
		return this.billList;
	}
	
	private State<T> state;
	private final State<T> branchState = new TreeBranchState<T>(this);
	private final State<T> budState = new TreeBudState<T>(this);
	private final State<T> emptyState = new TreeEmptyState<T>(this);;
	private final State<T> leafState = new TreeLeafState<T>(this);
	private final State<T> successState = new TreeSuccessState<T>(this);
	
	public final State<T> branchState() {
		return branchState;
	}
	
	public final State<T> budState() {
		return budState;
	}
	
	public final State<T> emptyState() {
		return emptyState;
	}
	
	public final State<T> leafState() {
		return leafState;
	}
	
	public final State<T> successState() {
		return successState;
	}
	
	// ////////////////////////////////////////////////////////////////////////
	//
	// Constructors
	//
	public TreeComponent() {
		this.setState(emptyState);
	}
	
	private TreeComponent(TreeComponent<T> refer, T value, TreeComponent<T> parent) {
		this.setState(leafState);
		this.setValue(value);
		this.refer = refer;
		this.setParent(parent);
	}

	// ////////////////////////////////////////////////////////////////////////
	//
	// Public methods
	//
	public final Map<String, T> getBranchs() {

		Map<String, T> hashMap = new HashMap<String, T>();
		List<T> list = this.branchs();
		for (T value : list) {
			hashMap.put(this.find(value).rowID(), value);
		}
		return hashMap;
	}

	public final Map<String, T> getLeafs(T value) {

		Map<String, T> leafs = new TreeMap<String, T>();
		for (T tmp : this.find(value).leafs()) {
			leafs.put(this.find(tmp).rowID(), tmp);
		}
		return leafs;
	}

	protected final void setValue(T value) {

		this.value = value;
	}

	public final void setChild() {
		this.childs = new ArrayList<TreeComponent<T>>();
	}
	
	private final TreeComponent<T> getNewTree(TreeComponent<T> refer, T value, TreeComponent<T> parent) {
		return new TreeComponent<T>(refer, value, parent);
	}

	public final List<TreeComponent<T>> all() {

		List<TreeComponent<T>> list = new ArrayList<TreeComponent<T>>();
		list.add(this);
		if (this.getState().equals(this.leafState)) {
			;
		} else {
			for (TreeComponent<T> child : this.childs) {
				list.addAll(child.all());
			}
		}
		return list;
	}

	public final List<T> branchs() {

		return this.getState().branchs(this);
	}

	public final String rowID() {

		return (String.valueOf(this.level()).length() == 1 ? "0"
				+ String.valueOf(this.level()) : String.valueOf(this.level()))
				+ "-"
				+ "/"
				+ levelMaxIndex(this.level());
	}

	/**
	 * @return 与本节点在同一层的所有节点，也包括自己。这个函数目前返回整个树中与本节点在同一层的所有节点，以后可能需要返回某个子树中的兄弟
	 *         时，就需要修改这个函数，注意！
	 * 
	 */
	/*
	private final SortedSet<T> brothers(TreeComponent<T> start) {

		SortedSet<T> brothers = new TreeSet<T>();

		// 对于根节点来说，它的兄弟集合里只有它自己
		// 如果不是根节点，也要首先把自己添加到集合中
		brothers.add(this.getValue());

		if (!this.equals(this.root())) {
			if (start.getState().equals(this.leafState)) {
				;
			} else {
				for (TreeComponent<T> child : start.getChilds()) {
					if (this.level() == child.level()) {

						brothers.add(child.getValue());
					} else {
						brothers.addAll(this.brothers(child));
					}
				}
			}
		}

		return brothers;
	}
	*/

	/**
	 * @return 本节点的层数（根节点的层数为 1）
	 */
	public final int level() {

		return this.equals(this.root()) ? ROOT_LEVEL : ROOT_LEVEL + this.getParent().level();
	}

	/**
	 * 
	 * @param relativeRoot
	 * @return 相对于某一节点，当前节点处于的层数（即当前节点处于某节点所属于的子树的第几层）
	 */
	public final int relativeLevel(TreeComponent<T> relativeRoot) {
		return this.equals(relativeRoot) ? RELATIVE_ROOT_LEVEL : 
			RELATIVE_ROOT_LEVEL + this.getParent().relativeLevel(relativeRoot);
	}

	/**
	 * @return 节点在绝对层（相对于根节点所在的层）的序列，从 1 开始计数。
	 */
	/*
	private final int levelIndex() {

		List<T> brothers = new ArrayList<T>();
		brothers.addAll(this.brothers(this.root()));
		return brothers.indexOf(this.value) + INDEX_START_FROM;
	}
	*/

	/**
	 * @return
	 */
	/*
	public final int relativeLevelIndex(TreeComponent<T> relativeRoot) {

		List<T> brothers = new ArrayList<T>();
		brothers.addAll(this.brothers(relativeRoot));

		// 因为 List 中的索引从 0 开始，所以需要 +1 达到序号从 1 开始数的效果。
		return brothers.indexOf(this.value) + INDEX_START_FROM;
	}
	*/

	public int refered() {
		return countReferedStartWith(root(), this);
	}

	public TreeComponent<T> getRefer() {
		return this.refer;
	}

	private int countReferedStartWith(TreeComponent<T> start, TreeComponent<T> myself) {

		int refered = (null == start.getRefer() ? 0 : (start.getRefer().equals(myself) ? 1 : 0));
		if (start.getState().equals(this.leafState)) {
			;
		} else {
			for (TreeComponent<T> child : start.getChilds()) {
				refered += myself.countReferedStartWith(child, myself);
			}
		}
		return refered;
	}
	
	public final int size() {
		return this.childs(this);
	}
	
	protected final int childs(TreeComponent<T> self) {
		System.out.println(self.getValue() + " " + this.getValue() + " " + this.relativeLevel(self));
		if (null == this.childs) {
			return 0;
		}
		if (this.relativeLevel(self) < MAX_OBSERVE_LEVEL) {
			int c = this.childs.size();
			for (TreeComponent<T> child: this.childs) {
				c += child.childs(self);
			}
			return c;
		} else {
			return 0;
		}
	}
	
	protected final int load() {
		if (null == this.childs) {
			return WEIGHT_OF_NODE;
		}
		int load = WEIGHT_OF_NODE;
		for (TreeComponent<T> child : this.childs) {
			load += child.load();
		}
		return load;
	}
	
	public void addTreeObservers() {
		this.addTreeObserverStartWith(this);
		if (this.countObservers() > MAX_OBSERVE_LEVEL - 1) {
			System.out.println("==========ERROR==========");
		}
	}
	
	private void addTreeObserverStartWith(TreeComponent<T> startTree) {
		if (null == startTree) {
			;
		} else if (startTree.equals(this.root())) {
			;
		} else {
			TreeComponent<T> beAddedTree = startTree.getParent();
			if (this.relativeLevel(beAddedTree) <= MAX_OBSERVE_LEVEL) {
				if (beAddedTree.getState().equals(this.successState)) {
					;
				} else {
					this.addObserver(beAddedTree);
				}
				this.addTreeObserverStartWith(beAddedTree);
			}
		}
	}
	
	//
	// Override methods
	//
	@Override
	@SuppressWarnings("unchecked")
	public final boolean equals(Object anotherObject) {
		if (this == anotherObject) {
			return true;
		}
		if (null == anotherObject) {
			return false;
		} 
		if (this.getClass() != anotherObject.getClass()) {
			return false;
		}
		TreeComponent<T> anotherTree = (TreeComponent<T>) anotherObject;
		return this.value.equals(anotherTree.value);
	}

	@Override
	public int hashCode() {
		return this.value.hashCode();
	}
	
	private String formatStr(String string, int length) {
		StringBuilder s = new StringBuilder(string);
		for (int i = 0; i < length - string.length(); i++) {
			s.append(" ");
		}
		return s.toString();
	}

	@Override
	public String toString() {
		
		StringBuilder str = new StringBuilder();
		str.append(this.formatStr(this.value.toString(), 16)).append("\t");
		str.append(this.bonus).append("\t");
		/*
		str.append(this.load()).append("\t");
		str.append(this.childs(this)).append("\t");
		str.append(this.bonus).append("\t");
		
		if (!this.getState().equals(this.leafState)) {
			str.append("[");
			List<TreeComponent<T>> childs = this.getChilds();
			for (int i = 0; i < childs.size(); i++) {
				str.append(childs.get(i).getValue());
				str.append(i == childs.size() - 1 ? "" : "|");
			}
			str.append("]");
		}
		str.append("\t");
		str.append("LE" + this.level()).append("\t");
	
		str.append(this.rowID()).append(" ");
		
		str.append(this.getState()).append(" ");
		*/
		return str.toString();
	}

	// Setters & Getters
	
	public String getMessage() {

		return null == this.message ? "" : this.message;
	}

	public void setMessage(String message) {

		this.message = message;
	}
	
	public T getValue() {

		return this.value;
	}

	public final List<TreeComponent<T>> getChilds() {
		return this.childs;
	}
	
	@Override
	final public void update(Observable observable, Object object) {
		try {
			this.state.update(observable);
		} catch (UpdateErrorException updateErrorException) {
			updateErrorException.printStackTrace();
		}
	}

	/**
	 * @param  refer 介绍 value 加入
	 * 
	 */
	public final void addComponent(T refer, T value) throws AddErrorException {
		this.getState().addComponent(refer, value);
	}

	public final TreeComponent<T> find(T value) {
		return this.getState().find(value);
	}
	
	public final void mountIt(TreeComponent<T> referTree, T value) {
		this.bonus += HuazhiFee.REFER_BONUS;
		this.getState().mountIt(referTree, value);
	}
	
	/**
	 * 
	 * @param mountTree 负责挂接的节电对象
	 * @param referTree 负责介绍的节点对象
	 * @param value
	 */
	public final void mount(TreeComponent<T> mountTree, TreeComponent<T> referTree, T value) {

		// 1 把新的节电new出来
		TreeComponent<T> newTree = this.getNewTree(referTree, value, mountTree);
		
		// 2 把新节电挂载到父亲节点下面
		mountTree.getChilds().add(newTree);
		
		// 3 新节点添加其祖辈到通知列表
		newTree.addTreeObservers();

		// 4 新节点的账单列表添加加入费用
		newTree.billList.add(new Bill<T>(newTree.billCount++, newTree.getValue(), HuazhiFee.JOIN_FEE, 0));
		newTree.setChanged();
		newTree.notifyObservers();
		
		// 5 新节点的账单列表添加介绍人介绍奖金
		newTree.billList.add(new Bill<T>(newTree.billCount++, referTree.getValue(), 0, HuazhiFee.REFER_BONUS));
	}
	
	public final int findTheOne(int[] childsLoadTable) {

		int start = 0;
		int position = 0;
		int min = childsLoadTable[start];

		for (int i = start + 1; i < childsLoadTable.length; i++) {
			if (min > childsLoadTable[i]) {
				min = childsLoadTable[i];
				position = i;
			}
		}

		return position;
	}
	
	public final TreeComponent<T> root() {

		return null == this.getRefer() ? this : this.getRefer().root();
	}
	
	public final boolean contains(T value) {
		return null == this.find(value) ? false : true;
	}
	
	public final List<T> leafs() {

		return this.getState().leafs(this);
	}
	
	public final void view() {

		System.out.println(this);
		if (this.getState().equals(this.leafState)) {
			;
		} else {
			for (TreeComponent<T> child : this.getChilds()) {
				child.view();
			}
		}
	}
	
	/**
	 * @return 三叉树某一层的最大节点数
	 */
	public final static int levelMaxIndex(int level) {

		return (int) Math.pow(3, level - 1);
	}
	
	@SuppressWarnings("unchecked")
	public final List<Bill<T>> createAndBill(T value) {

		return BillListFactory.getInstance().getBillList(find(value));
	}
	
	// Setters & Getters

	protected final void setState(State<T> state) {
		this.state = state;
	}

	public final State<T> getState() {

		return this.state;
	}
	
	public final void setParent(TreeComponent<T> parent) {
		
		this.parent = parent;
	}
	
	public final TreeComponent<T> getParent() {

		return this.parent;
	}
	
	private static final long serialVersionUID = 5534291149660719478L;
	
	protected final int getBonus() {
		int childs = this.childs(this);
		if (1 <= childs && childs <= 3) {
			return HuazhiFee.FIRST_BONUS;
		} else if (4 <= childs && childs <= 12) {
			return HuazhiFee.SECOND_BONUS;
		} else if (13 <= childs && childs <= 39) {
			return HuazhiFee.THIRD_BONUS;
		} else if (40 <= childs && childs <= 120) {
			return HuazhiFee.FOURTH_BONUS;
		} else {
			return 0;
		}
	}
	
	protected final int getBonus(TreeComponent<T> newTree) {
		switch (newTree.relativeLevel(this)) {
		case 1:return 0;
		case 2:return HuazhiFee.FIRST_BONUS;
		case 3:return HuazhiFee.SECOND_BONUS;
		case 4:return HuazhiFee.THIRD_BONUS;
		case 5:return HuazhiFee.FOURTH_BONUS;
		default: return 0;
		}
	}

    @Override
    public void addNode(Human people, Date crateDate, Integer fatherNodeID, String flag) {

    }

    @Override
    public void print() {

    }
}
