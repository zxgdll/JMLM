package net.realqinwei.hzcrm.crm.service.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.News;

public interface NewsService {

	public News headline();
	public List<News> allNews();
	public void save(News news);
	public News findById(Integer id);
	public List<News> headlines();
	public void delete(News news);
	
	public News about();
	public News contact();
	public List<News> products();
	public List<News> courses();
}
