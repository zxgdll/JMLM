package net.realqinwei.hzcrm.crm.action;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.service.intf.NewsService;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ApplicationAware;

public class IndexAction extends ActionSupport implements Preparable, ApplicationAware {
	
	private static final long serialVersionUID = -7088835911154117308L;

	private static final Logger LOG = Logger.getLogger(IndexAction.class);
	
	private NewsService newsService;
	private List<News> headline;
	private List<News> products;
	private Map<String, Object> application;
	
	@Override
	public void prepare() throws Exception {
		this.application.put("contact", this.newsService.contact());
		this.application.put("about", this.newsService.about());
		//this.headline = this.newsService.headlines();
		this.headline = this.newsService.allNews();
		this.products = this.newsService.products();
		LOG.debug(this.headline);
	}
	
	@Override
	public String execute() throws Exception {	
		return SUCCESS;
	}
	
	// Setters & Getters
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	public List<News> getHeadline() {
		return headline;
	}

	public void setHeadline(List<News> headline) {
		this.headline = headline;
	}
	
	public List<News> getProducts() {
		return products;
	}

	public void setProducts(List<News> products) {
		this.products = products;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;
	}
}
