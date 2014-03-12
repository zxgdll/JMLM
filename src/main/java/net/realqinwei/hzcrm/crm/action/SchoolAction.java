package net.realqinwei.hzcrm.crm.action;

import java.sql.Timestamp;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.service.intf.NewsService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;

public class SchoolAction extends ActionSupport {

	private static final long serialVersionUID = -9183608064713571507L;

	private static final Logger LOG = Logger.getLogger(SchoolAction.class);
	
	private TimestampCreator timer;
	private NewsService newsService;
	private List<News> courses;
	private Integer courseId;
	private News course;
	
	public String list() {
		this.courses = this.newsService.courses();
		this.course = null == this.courseId ? this.courses.get(0) : this.newsService.findById(this.courseId);
		return SUCCESS;
	}
	
	public String manage() {
		return this.list();
	}
	
	public String edit() {
		LOG.debug(this.courseId);
		this.course = this.newsService.findById(this.courseId);
		LOG.debug(this.course);
		return SUCCESS;
	}
	
	public String create() {
		Timestamp time = this.timer.getTimestamp();
		this.course.setCreateTime(time);
		this.course.setModifyTime(time);
		this.course.setType(News.TYPE_SCHOOL);
		this.newsService.save(this.course);
		return SUCCESS;
	}
	
	public String delete() {
		this.course = this.newsService.findById(this.courseId);
		if (null != this.course) {
			this.newsService.delete(this.course);
			return SUCCESS;
		} else {
			return ERROR;
		}
	}
	
	public String modify() {
		Timestamp time = this.timer.getTimestamp();
		this.course.setModifyTime(time);
		this.newsService.save(this.course);
		return SUCCESS;
	}
	
	// Setters & Getters
	
	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public List<News> getCourses() {
		
		return courses;
	}
	
	public void setCourses(List<News> courses) {
	
		this.courses = courses;
	}
	
	public TimestampCreator getTimer() {
		return timer;
	}

	public void setTimer(TimestampCreator timer) {
		this.timer = timer;
	}
	
	public Integer getCourseId() {
		
		return courseId;
	}

	public void setCourseId(Integer courseId) {
	
		this.courseId = courseId;
	}
	
	public News getCourse() {
		
		return course;
	}
	
	public void setCourse(News course) {
	
		this.course = course;
	}
}
