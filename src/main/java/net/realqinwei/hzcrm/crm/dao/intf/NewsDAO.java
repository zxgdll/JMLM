package net.realqinwei.hzcrm.crm.dao.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.News;

public interface NewsDAO {
	
	public News headline();
	public List<News> headlines();
	public List<News> findAll();
	public void saveNews(News news);
	public News findByID(Integer id);
	public void updateNews(News news);
	public void deleteNews(News news);
	
	public News about();
	
	public News contact();
	
	public List<News> products();
	public List<News> courses();

}
