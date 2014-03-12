package net.realqinwei.hzcrm.crm.dao.intf;

import net.realqinwei.hzcrm.crm.been.Node;

import java.util.List;

public interface NodeDAO {
	
	public void saveNode(Node node);
	public List<Node> findAll();

}
