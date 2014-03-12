package net.realqinwei.hzcrm.crm.domain;

import java.util.*;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.exception.AddErrorException;

public final class TreeFactory {

	private UserRepository userRepository;

	public void setUserRepository(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {

		return userRepository;
	}

	public SortedSet<User> getBill() {

		List<User> allUsers = this.getUserRepository().findAll();

		SortedSet<User> sortedAllUsers = new TreeSet<User>();
		sortedAllUsers.addAll(allUsers);
		return sortedAllUsers;
	}

	public TreeComponent<User> getTree() throws AddErrorException {

		List<User> allUsers = this.getUserRepository().findAll();

		SortedSet<User> sortedAllUsers = new TreeSet<User>();
		sortedAllUsers.addAll(allUsers);

		Queue<User> allUsersQueue = new LinkedList<User>();
		for (User user : sortedAllUsers) {
			allUsersQueue.offer(user);
		}

		TreeComponent<User> tree = new TreeComponent<User>();
		User tmpUser = null;
		while (!allUsersQueue.isEmpty() && null != tree) {
			tmpUser = allUsersQueue.poll();

			tree.addComponent(null == tmpUser.getUserReferID() ? null
					: this.userRepository.findById(tmpUser.getUserReferID()),
					tmpUser);

		}
		
		//tree.view();

		return tree;
	}
}
