package net.realqinwei.hzcrm.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import net.realqinwei.hzcrm.crm.been.LoginLog;
import net.realqinwei.hzcrm.crm.dao.intf.LogDAO;

public final class LogDAOImpl extends HibernateDaoSupport implements LogDAO {

	@Override
	public void save(LoginLog log) {
		this.getHibernateTemplate().save(log);
		this.getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoginLog> all() {
		return (List<LoginLog>) this.getHibernateTemplate().find("FROM LoginLog");
	}
}
