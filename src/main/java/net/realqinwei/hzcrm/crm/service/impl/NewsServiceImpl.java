package net.realqinwei.hzcrm.crm.service.impl;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.dao.intf.NewsDAO;

import net.realqinwei.hzcrm.crm.service.intf.NewsService;

public final class NewsServiceImpl implements NewsService {
	
	private NewsDAO newsDAO;

	@Override
	public News headline() {
		return this.newsDAO.headline();
	}
	
	public NewsDAO getNewsDAO() {
		return newsDAO;
	}

	public void setNewsDAO(NewsDAO newsDAO) {
		this.newsDAO = newsDAO;
	}

	@Override
	public List<News> allNews() {
		return this.newsDAO.findAll();
	}

	@Override
	public void save(News news) {
		if (null == news.getId()) {
			this.newsDAO.saveNews(news);
		} else {
			this.newsDAO.updateNews(news);
		}
	}

	@Override
	public News findById(Integer id) {
		return this.newsDAO.findByID(id);
	}

	@Override
	public List<News> headlines() {
		return this.newsDAO.headlines();
	}

	@Override
	public void delete(News news) {
		this.newsDAO.deleteNews(news);
	}

	@Override
	public News about() {
		return this.newsDAO.about();
	}

	@Override
	public News contact() {
		return this.newsDAO.contact();
	}

	@Override
	public List<News> products() {
		return this.newsDAO.products();
	}

	@Override
	public List<News> courses() {
		return this.newsDAO.courses();
	}
}
