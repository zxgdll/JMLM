package net.realqinwei.hzcrm.crm.action;

import java.sql.Timestamp;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.service.intf.NewsService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class NewsAction extends ActionSupport {

	private static final long serialVersionUID = -9183608064713571507L;

	private static final Logger LOG = Logger.getLogger(NewsAction.class);
	
	private TimestampCreator timer;
	
	private NewsService newsService;
	private News news;
	
	public String createsave() {

		Timestamp time = this.timer.getTimestamp();
		this.news.setCreateTime(time);
		this.news.setModifyTime(time);
		this.news.setType(News.TYPE_NEWS);
		LOG.debug(this.news);
		this.newsService.save(this.news);
		return SUCCESS;
	}
	
	public String editsave() {
		//String fcontent = new String(ServletActionContext.getRequest().getParameter("news.content").getBytes());
		//LOG.warn(fcontent);
		try {
			//this.news.setContent(fcontent);
			this.news.setModifyTime(this.timer.getTimestamp());
			LOG.debug(this.news);
			this.newsService.save(this.news);
			return SUCCESS;
		} catch(Exception e) {
			e.printStackTrace();
			return INPUT;
		}
	}
	
	public String view() {
		this.prepare();
		if (null == this.news) {
			this.news = this.newsService.headline();
		}
		LOG.debug("news: " + this.news);
		return SUCCESS;
	}
	
	public String edit() {
		this.prepare();
		return SUCCESS;
	}
	
	public String delete() {
		this.prepare();
		if (null == this.news) {
			return ERROR;
		} else {
			this.newsService.delete(this.news);
			return SUCCESS;
		}
	}
	
	public String save() {
		return SUCCESS;
	}
	
	// For now, this method should not be executed.
	@Override
	public String execute() throws Exception {
		return ERROR;
	}
	
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	private void prepare() {
		String newsID = ServletActionContext.getRequest().getParameter("newsID");
		this.news = null == newsID ? null : this.newsService.findById(Integer.valueOf(newsID));
		LOG.debug("news: " + this.news);
	}
	
	public TimestampCreator getTimer() {
		return timer;
	}

	public void setTimer(TimestampCreator timer) {
		this.timer = timer;
	}
}
