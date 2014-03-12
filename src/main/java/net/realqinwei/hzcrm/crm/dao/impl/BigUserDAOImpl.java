package net.realqinwei.hzcrm.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.dao.intf.UserDAO;

public class BigUserDAOImpl extends HibernateDaoSupport implements UserDAO {
	
	private void flush() {
		this.getHibernateTemplate().flush();
	}

	@Override
	public void saveUser(User user) {
		this.getHibernateTemplate().save(user);
		this.flush();
	}

	@Override
	public void removeUser(User user) {
		this.getHibernateTemplate().delete(user);
		this.flush();
	}

	@Override
	public User findUserByID(Integer id) {
		return null == id ? null : (User) this.getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return (List<User>) this.getHibernateTemplate().find("FROM User WHERE userType = 1 ORDER BY userCreateTime");
	}

	@Override
	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
		this.flush();
	}
}
