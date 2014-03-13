package net.realqinwei.hzcrm.crm.domain;

import net.realqinwei.hzcrm.crm.been.Node;

import java.util.List;

public interface UserRepository {

	public List<Node> findAll();
	public Node findById(int id);
}
