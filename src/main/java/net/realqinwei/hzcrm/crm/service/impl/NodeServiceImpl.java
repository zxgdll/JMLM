package net.realqinwei.hzcrm.crm.service.impl;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.dao.intf.NodeDAO;
import net.realqinwei.hzcrm.crm.service.intf.NodeService;
import net.realqinwei.hzcrm.crm.util.MD5;

import java.util.ArrayList;
import java.util.List;

public final class NodeServiceImpl implements NodeService {
	
	private NodeDAO nodeDAO;
	
	public void setNodeDAO(NodeDAO nodeDAO) {
		this.nodeDAO = nodeDAO;
	}
	
	public NodeDAO getNodeDAO() {
		return nodeDAO;
	}

	@Override
	public void save(Node node) {
        this.getNodeDAO().saveNode(node);
	}

	@Override
	public void update(Node node) {
        this.getNodeDAO().updateNode(node);
	}

    @Override
    public List<Node> findByOwner(User user) {
        return this.getNodeDAO().fintByOwner(user);
    }
}
