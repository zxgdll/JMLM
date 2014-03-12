package net.realqinwei.hzcrm.crm.dao.impl;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import net.realqinwei.hzcrm.crm.dao.intf.NodeDAO;

public class NodeDAOImpl extends HibernateDaoSupport implements NodeDAO {
	
	private void flush() {
		this.getHibernateTemplate().flush();
	}

    @Override
    public void saveNode(Node node) {
        this.getHibernateTemplate().save(node);
        this.flush();
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public List<Node> findAll() {
        return (List<Node>) this.getHibernateTemplate().find("FROM Node");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Node> findByOwner(BigUser user) {
        return (List<Node>) this.getHibernateTemplate().find("FROM Node Where nodeUserID = " + user.getId());
    }
}
