package net.realqinwei.hzcrm.crm.dao.impl;

import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.dao.intf.UserDAO;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO {
	
	private void flush() {
		getHibernateTemplate().flush();
	}

	@Override
	public void saveUser(User user) {
		getHibernateTemplate().save(user);
		flush();
	}

	@Override
	public void removeUser(User user) {
		getHibernateTemplate().delete(user);
		flush();
	}

	@Override
	public User findUserByID(Integer id) {
		return null == id ? null : (User) getHibernateTemplate().get(User.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAll() {
		return (List<User>) getHibernateTemplate().find("FROM User WHERE userType = 1 ORDER BY userCreateTime");
	}

	@Override
	public void updateUser(User user) {
		getHibernateTemplate().update(user);
		flush();
	}

    @Override
    public List<User> getUsersHaveNodes() {
        String sql = "FROM User WHERE userType = 1 AND id IN (SELECT nodeUserID FROM Node) ORDER BY userCreateTime";
        return (List<User>) getHibernateTemplate().find(sql);
    }
}
