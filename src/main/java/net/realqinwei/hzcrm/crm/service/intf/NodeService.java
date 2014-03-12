package net.realqinwei.hzcrm.crm.service.intf;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;

import java.util.List;

public interface NodeService {
	
	public void save(Node node);
	public List<Node> getNodes();
    public List<Node> findByOwner(BigUser user);
}
