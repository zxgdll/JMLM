package net.realqinwei.hzcrm.crm.dao.impl;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.dao.intf.NodeDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class NodeDAOImpl extends HibernateDaoSupport implements NodeDAO {
	
	private void flush() {
		this.getHibernateTemplate().flush();
	}

	@Override
	public void saveNode(Node node) {
		this.getHibernateTemplate().save(node);
		this.flush();
	}

	@Override
	public void removeNode(Node user) {
		this.getHibernateTemplate().delete(user);
		this.flush();
	}

	@Override
	public Node findNodeByID(Integer id) {
		return null == id ? null : (Node) this.getHibernateTemplate().get(Node.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Node> findAll() {
		return (List<Node>) this.getHibernateTemplate().find("FROM Node WHERE userType = 1 ORDER BY userCreateTime");
	}

	@Override
	public void updateNode(Node node) {
		this.getHibernateTemplate().update(node);
		this.flush();
	}

    @Override
    public List<Node> fintByOwner(BigUser user) {
        return (List<Node>) this.getHibernateTemplate().find("FROM Node WHERE nodeUserID = " + user.getId());
    }
}
