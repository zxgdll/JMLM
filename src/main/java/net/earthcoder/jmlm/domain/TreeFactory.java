package net.earthcoder.jmlm.domain;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.domain.NodeRepository;
import net.realqinwei.hzcrm.crm.domain.TreeComponent;
import net.realqinwei.hzcrm.crm.domain.exception.AddErrorException;

import java.util.*;

public final class TreeFactory {

    public NodeRepository getNodeRepository() {
        return nodeRepository;
    }

    public void setNodeRepository(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    private NodeRepository nodeRepository;

	public SortedSet<Node> getBill() {

		List<Node> allUsers = this.getNodeRepository().findAll();

		SortedSet<Node> sortedAllUsers = new TreeSet<Node>();
		sortedAllUsers.addAll(allUsers);
		return sortedAllUsers;
	}

    public SortedSet<Node> getBinaryBill() {
        return nodeRepository.findSortedSet();
    }

    public BinaryTree getBinaryTree() {
        Queue<Node> allUsersQueue = new LinkedList<Node>();
        for (Node user : nodeRepository.findSortedSet()) {
            allUsersQueue.offer(user);
        }
        BinaryTree tree = new BinaryTree();
        Node node;
        while (!allUsersQueue.isEmpty() && null != tree) {
            node = allUsersQueue.poll();
            tree.addNode(node, node.getUserReferID(), node.getNodeLoaderID());
        }
        return tree;
    }

	public TreeComponent<Node> getTree() throws AddErrorException {

		List<Node> allUsers = this.getNodeRepository().findAll();

		SortedSet<Node> sortedAllUsers = new TreeSet<Node>();
		sortedAllUsers.addAll(allUsers);

		Queue<Node> allUsersQueue = new LinkedList<Node>();
		for (Node user : sortedAllUsers) {
			allUsersQueue.offer(user);
		}

		TreeComponent<Node> tree = new TreeComponent<Node>();
        Node tmpUser = null;
		while (!allUsersQueue.isEmpty() && null != tree) {
			tmpUser = allUsersQueue.poll();

			tree.addComponent(null == tmpUser.getUserReferID() ? null
					: this.getNodeRepository().findById(tmpUser.getUserReferID()),
					tmpUser);

		}
		
		//tree.view();

		return tree;
	}
}
