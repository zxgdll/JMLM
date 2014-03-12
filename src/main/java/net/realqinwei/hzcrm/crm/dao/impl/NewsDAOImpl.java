package net.realqinwei.hzcrm.crm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.dao.intf.NewsDAO;

public final class NewsDAOImpl extends HibernateDaoSupport implements NewsDAO {
	
	private static final String SQL_ALL_NEWS = "FROM News WHERE type = 1 ORDER BY modifyTime DESC";
	private static final String SQL_ABOUT = "FROM News WHERE type = 2";
	private static final String SQL_CONTACT = "FROM News WHERE type = 3";
	private static final String SQL_PRODUCT = "FROM News WHERE type = 4";
	private static final String SQL_SCHOOL = "FROM News WHERE type = 5";

	@Override
	public News headline() {
		return (News) this.findAll().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> findAll() {
		return (List<News>) this.getHibernateTemplate().find(SQL_ALL_NEWS);
	}

	@Override
	public void saveNews(News news) {
		this.getHibernateTemplate().save(news);
		this.getHibernateTemplate().flush();
	}

	@Override
	public News findByID(Integer id) {
		return null == id ? null : (News) this.getHibernateTemplate().get(News.class, id);
	}

	@Override
	public void updateNews(News news) {
		this.getHibernateTemplate().update(news);
		this.getHibernateTemplate().flush();
	}

	@Override
	public List<News> headlines() {
		List<News> allNews = this.findAll();
		List<News> headlines = new ArrayList<News>();
		for (int i = 0; i < allNews.size() && i <= 3; i++) {
			headlines.add(allNews.get(i));
		}
		return headlines;
	}

	@Override
	public void deleteNews(News news) {
		this.getHibernateTemplate().delete(news);
		this.getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public News about() {
		List<News> list = this.getHibernateTemplate().find(SQL_ABOUT);
		return list.size() != 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public News contact() {
		List<News> list = this.getHibernateTemplate().find(SQL_CONTACT);
		return list.size() != 0 ? list.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> products() {
		return ((List<News>) this.getHibernateTemplate().find(SQL_PRODUCT));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<News> courses() {
		return ((List<News>) this.getHibernateTemplate().find(SQL_SCHOOL));
	}
}
