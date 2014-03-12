package net.realqinwei.hzcrm.crm.dao.impl;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.dao.intf.BigUserDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class BigUserDAOImpl extends HibernateDaoSupport implements BigUserDAO {
	
	private void flush() {
		this.getHibernateTemplate().flush();
	}

	@Override
	public void saveUser(BigUser user) {
		this.getHibernateTemplate().save(user);
		this.flush();
	}

	@Override
	public void removeUser(BigUser user) {
		this.getHibernateTemplate().delete(user);
		this.flush();
	}

	@Override
	public BigUser findUserByID(Integer id) {
		return null == id ? null : (BigUser) this.getHibernateTemplate().get(BigUser.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BigUser> findAll() {
		return (List<BigUser>) this.getHibernateTemplate().find("FROM BigUser WHERE userType = 1 ORDER BY userCreateTime");
	}

	@Override
	public void updateUser(BigUser user) {
		this.getHibernateTemplate().update(user);
		this.flush();
	}
}
