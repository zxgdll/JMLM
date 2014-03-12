package net.realqinwei.hzcrm.crm.domain;

import net.realqinwei.hzcrm.crm.been.User;
import junit.framework.Assert;
import junit.framework.TestCase;


public class StateTest extends TestCase {
	
	private State<User> branchState;
	private State<User> budState;
	private State<User> emptyState;
	private State<User> leafState;
	private State<User> successState;
	
	private State<User> successState2;
	private State<User> successState3;
	
	public void setUp() {
		TreeComponent<User> tree = new TreeComponent<User>();
		TreeComponent<User> otherTree = new TreeComponent<User>();
		TreeComponent<User> sameTree = otherTree;
		this.branchState = new TreeBranchState<User>(tree);
		this.budState = new TreeBudState<User>(tree);
		this.emptyState = new TreeEmptyState<User>(tree);
		this.leafState = new TreeLeafState<User>(tree);
		this.successState = new TreeSuccessState<User>(tree);
		this.successState2 = new TreeSuccessState<User>(otherTree);
		this.successState3 = new TreeSuccessState<User>(sameTree);
	}
	
	public void testEqualsSameTree() {
		Assert.assertEquals(true, this.successState2.equals(this.successState3));
	}
	
	public void testEqualsDifferentTree() {
		Assert.assertEquals(false, this.successState.equals(this.successState2));
	}

	public void testEqualsDifferentState() {
		Assert.assertEquals(false, this.budState.equals(this.branchState));
	}
	
	public void testEqualsNull() {
		Assert.assertEquals(false, this.budState.equals(null));
	}
	
	public void testEqualsOtherObject() {
		Assert.assertEquals(false, this.budState.equals(new String("")));
	}
	
	public void testEqualsBranchState() {
		Assert.assertEquals(true, this.branchState.equals(this.branchState));
	}
	
	public void testEqualsBudState() {
		Assert.assertEquals(true, this.budState.equals(this.budState));
	}
	
	public void testEqualsEmptyState() {
		Assert.assertEquals(true, this.emptyState.equals(this.emptyState));
	}
	
	public void testEqualsLeafState() {
		Assert.assertEquals(true, this.leafState.equals(this.leafState));
	}
	
	public void testEqualsSuccessState() {
		Assert.assertEquals(true, this.successState.equals(this.successState));
	}
}
