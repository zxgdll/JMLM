package net.realqinwei.hzcrm.crm.service.intf;

import net.realqinwei.hzcrm.crm.been.Node;

import java.util.List;

public interface NodeService {
	
	public void save(Node node);
	public List<Node> getNodes();
}
