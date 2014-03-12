package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.service.intf.NewsService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;

public class AboutAction extends ActionSupport {
	
	private static final long serialVersionUID = -7088835911154117308L;

	private static final Logger LOG = Logger.getLogger(AboutAction.class);
	
	private TimestampCreator timer;
	private NewsService newsService;
	private News about;
	
	private void prepare() {
		this.about = this.newsService.about();
		LOG.debug(this.about);
	}
	
	public String view() {
		this.prepare();
		return SUCCESS;
	}
	
	public String edit() {
		this.prepare();
		if (null == this.about) {
			this.about = new News();
			this.about.setCreateTime(this.timer.getTimestamp());
			this.about.setModifyTime(this.about.getCreateTime());
			this.about.setTitle("ABOUT");
			this.about.setType(News.TYPE_ABOUT);
			
			LOG.debug(this.about);
		}
		return SUCCESS;
	}
	
	public String update() {
		this.about.setModifyTime(this.timer.getTimestamp());
		this.newsService.save(this.about);
		return SUCCESS;
	}
	
	/** 
	 *
	 * Setters and getters
	 *
	 */
	
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
	public News getAbout() {
		return about;
	}

	public void setAbout(News about) {
		this.about = about;
	}
	
	public TimestampCreator getTimer() {
		return timer;
	}

	public void setTimer(TimestampCreator timer) {
		this.timer = timer;
	}
}
