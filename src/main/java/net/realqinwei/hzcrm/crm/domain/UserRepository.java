package net.realqinwei.hzcrm.crm.domain;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.User;

public interface UserRepository {

	public List<User> findAll();
	public User findById(int id);
}
