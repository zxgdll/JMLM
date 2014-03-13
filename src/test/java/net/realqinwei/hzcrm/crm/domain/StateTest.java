package net.realqinwei.hzcrm.crm.domain;

import junit.framework.Assert;
import junit.framework.TestCase;
import net.realqinwei.hzcrm.crm.been.Node;


public class StateTest extends TestCase {
	
	private State<Node> branchState;
	private State<Node> budState;
	private State<Node> emptyState;
	private State<Node> leafState;
	private State<Node> successState;
	
	private State<Node> successState2;
	private State<Node> successState3;
	
	public void setUp() {
		TreeComponent<Node> tree = new TreeComponent<Node>();
		TreeComponent<Node> otherTree = new TreeComponent<Node>();
		TreeComponent<Node> sameTree = otherTree;
		this.branchState = new TreeBranchState<Node>(tree);
		this.budState = new TreeBudState<Node>(tree);
		this.emptyState = new TreeEmptyState<Node>(tree);
		this.leafState = new TreeLeafState<Node>(tree);
		this.successState = new TreeSuccessState<Node>(tree);
		this.successState2 = new TreeSuccessState<Node>(otherTree);
		this.successState3 = new TreeSuccessState<Node>(sameTree);
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
