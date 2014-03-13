package net.realqinwei.hzcrm.crm.service.intf;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.been.Node;

import java.util.List;

public interface NodeService {

	public boolean nodeIDExist(Integer userID);
	public boolean isPasswordRight(Integer userID, String password);
	
	public void save(Node node);
	public void update(Node node);
	
	public List<Node> getNodes();
    public List<Node> findByOwner(BigUser user);
}
