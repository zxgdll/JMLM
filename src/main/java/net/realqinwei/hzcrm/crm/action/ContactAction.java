package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.service.intf.NewsService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;

public class ContactAction extends ActionSupport {
	
	private static final long serialVersionUID = -7088835911154117308L;

	private static final Logger LOG = Logger.getLogger(ContactAction.class);
	
	private TimestampCreator timer;
	
	private NewsService newsService;
	private News contact;
	
	private void prepare() {
		this.contact = this.newsService.contact();
		LOG.debug(this.contact);
	}
	
	public String view() {
		this.prepare();
		return SUCCESS;
	}
	
	public String edit() {
		this.prepare();
		if (null == this.contact) {
			this.contact = new News();
			this.contact.setCreateTime(this.timer.getTimestamp());
			this.contact.setModifyTime(this.contact.getCreateTime());
			this.contact.setTitle("CONTACT");
			this.contact.setType(News.TYPE_CONTACT);
			
			LOG.debug(this.contact);
		}
		return SUCCESS;
	}
	
	public String update() {
		
		this.contact.setModifyTime(this.timer.getTimestamp());
		this.newsService.save(this.contact);
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
	
	public News getContact() {
		return contact;
	}

	public void setContact(News contact) {
		this.contact = contact;
	}
	
	public TimestampCreator getTimer() {
		return timer;
	}

	public void setTimer(TimestampCreator timer) {
		this.timer = timer;
	}
}
