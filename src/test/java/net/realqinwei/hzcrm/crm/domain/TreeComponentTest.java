package net.realqinwei.hzcrm.crm.domain;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TreeComponentTest extends TestCase {

	private TreeComponent<User> tree;

	public void setUp() {

		this.tree = new TreeComponent<User>();
	}

	public void testFindTheOneBlank() {

		int[] table = { 0, 0, 0 };
		Assert.assertEquals(0, tree.findTheOne(table));
	}

	public void testFindTheOneAllOne() {

		int[] table = { 1, 1, 1 };
		Assert.assertEquals(0, tree.findTheOne(table));
	}
	
	public void testFindTheOne0() {

		int[] table = { 3, 4, 5 };
		Assert.assertEquals(0, tree.findTheOne(table));
	}
	
	public void testFindTheOne2() {

		int[] table = { 5, 4, 3 };
		Assert.assertEquals(2, tree.findTheOne(table));
	}
	
	public void testFindTheOne1() {

		int[] table = { 5, 3, 4 };
		Assert.assertEquals(1, tree.findTheOne(table));
	}
	
	public void testFindTheOneEquals1() {

		int[] table = { 3, 3, 4 };
		Assert.assertEquals(0, tree.findTheOne(table));
	}
	
	public void testFindTheOneEquals2() {

		int[] table = { 4, 3, 3 };
		Assert.assertEquals(1, tree.findTheOne(table));
	}
	
	public void testFindTheOneEquals3() {

		int[] table = { 2, 3, 3 };
		Assert.assertEquals(0, tree.findTheOne(table));
	}
	
	public void testFindTheOneEquals4() {

		int[] table = { 3, 3, 2 };
		Assert.assertEquals(2, tree.findTheOne(table));
	}
}
