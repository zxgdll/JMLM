package net.realqinwei.hzcrm.crm.service.impl;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.dao.intf.NodeDAO;
import net.realqinwei.hzcrm.crm.domain.NodeRepository;

import java.util.List;

public final class NodeRepositoryImpl implements NodeRepository {

    public NodeDAO getNodeDAO() {
        return nodeDAO;
    }

    public void setNodeDAO(NodeDAO nodeDAO) {
        this.nodeDAO = nodeDAO;
    }

    private NodeDAO nodeDAO;

	@Override
	public List<Node> findAll() {
		return this.getNodeDAO().findAll();
	}

	@Override
	public Node findById(int id) {
		return this.getNodeDAO().findNodeByID(id);
	}
}
