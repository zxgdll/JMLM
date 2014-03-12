package net.realqinwei.hzcrm.crm.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.service.intf.NewsService;

import org.apache.log4j.Logger;

public class NewsListAction extends ActionSupport implements Preparable {

	private static final long serialVersionUID = -3525440274451286671L;

	private static final Logger LOG = Logger.getLogger(NewsListAction.class);
	
	private NewsService newsService;
	private List<News> news;
	
	@Override
	public void prepare() throws Exception {
		
	}
	
	@Override
	public String execute() throws Exception {
		this.news = this.newsService.allNews();
		LOG.debug(this.news.size());
		return SUCCESS;
	}
	
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	public List<News> getNews() {
		return news;
	}

	public void setNews(List<News> news) {
		this.news = news;
	}
}
