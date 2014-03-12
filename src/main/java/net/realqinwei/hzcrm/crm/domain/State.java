package net.realqinwei.hzcrm.crm.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

import net.realqinwei.hzcrm.crm.domain.exception.AddErrorException;
import net.realqinwei.hzcrm.crm.domain.exception.UpdateErrorException;

public abstract class State<T> implements Serializable {

	private static final long serialVersionUID = 1658308081295276658L;
	
	private TreeComponent<T> treeComponent;
	
	public State(TreeComponent<T> treeComponent) {
		this.treeComponent = treeComponent;
	}
	
	protected final TreeComponent<T> getTree() {
		return this.treeComponent;
	}
	
	abstract public void update(Observable observable) throws UpdateErrorException;
	abstract public void addComponent(T refer, T value) throws AddErrorException;
	abstract public void mountIt(TreeComponent<T> refer, T value);
	abstract public List<T> branchs(TreeComponent<T> tree);
	abstract public List<T> leafs(TreeComponent<T> tree);
	abstract public TreeComponent<T> find(T value);
	abstract public int[] getChildsLoadTable();
	
	@SuppressWarnings("unchecked")
	@Override
	final public boolean equals(Object otherObject) {
		if (this == otherObject) {
			return true;
		}
		if (null == otherObject) {
			return false;
		}
		if (!(otherObject instanceof State)) {
			return false;
		}
		if (this.getClass() != otherObject.getClass()) {
			return false;
		}
		State<T> otherState = (State<T>) otherObject;
		return this.treeComponent == otherState.treeComponent ? true : false;
	}
	
	@Override
	final public int hashCode() {
		return this.treeComponent.hashCode();
	}
	
	@Override
	final public String toString() {
		return this.getClass().getSimpleName();
	}
}
// ~