package net.realqinwei.hzcrm.crm.dao.intf;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;

import java.util.List;

public interface NodeDAO {
	
	public void saveNode(Node node);
	public void removeNode(Node node);
	public Node findNodeByID(Integer id);
	public List<Node> findAll();
	public void updateNode(Node node);
    public List<Node> fintByOwner(User user);

}
