package net.realqinwei.hzcrm.crm.dao.intf;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;

import java.util.List;

public interface NodeDAO {
	
	public void saveNode(Node node);
	public List<Node> findAll();
    public List<Node> findByOwner(BigUser user);

}
