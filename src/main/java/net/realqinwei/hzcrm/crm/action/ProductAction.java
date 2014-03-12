package net.realqinwei.hzcrm.crm.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.News;
import net.realqinwei.hzcrm.crm.service.intf.NewsService;
import net.realqinwei.hzcrm.crm.util.FileUploader;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public final class ProductAction extends ActionSupport {

	private static final long serialVersionUID = -7088835911154117308L;

	private static final Logger LOG = Logger.getLogger(ProductAction.class);

	private TimestampCreator timer;
	private FileUploader uploader;
	private NewsService newsService;

	private List<News> products;
	private News product;
	private File file;
	private String fileFileName;
	
	private void prepareFromRepository() {
		this.products = this.newsService.products();
		LOG.debug(this.products);
	}
	
	private void prepareFromID() {
		String newsID = ServletActionContext.getRequest().getParameter("newsID");
		if (null != newsID) {
			this.product = this.newsService.findById(Integer.valueOf(newsID));
		}
	}
	
	private String uploadFile() {
		
		HttpServletRequest request = ServletActionContext.getRequest();
		String ROOT = request.getSession().getServletContext()
				.getRealPath(FileUploader.UPLOAD_DIR);
		
		LOG.warn(ROOT);
		LOG.warn(this.file);
		LOG.warn(this.fileFileName);

		this.uploader.upload(ROOT, this.file, this.fileFileName);

		String path = request.getScheme() + "://" + request.getServerName()
				+ ":" + request.getServerPort() + request.getContextPath()
				+ FileUploader.UPLOAD_DIR + "/" + this.fileFileName;

		return path;
	}
	
	public String show() {
		prepareFromID();
		return SUCCESS;
	}

	public String delete() {
		
		this.prepareFromID();
		this.newsService.delete(this.product);
		
		return SUCCESS;
	}

	public String create() {
		
		LOG.debug(this.file);
		
		this.product.setCreateTime(this.timer.getTimestamp());
		this.product.setModifyTime(this.product.getCreateTime());
		this.product.setType(News.TYPE_PRODUCT);
		if (null != this.file) {
			this.product.setImage(this.uploadFile());
		}
		LOG.debug(this.product);

		this.newsService.save(this.product);

		return SUCCESS;
	}

	public String list() {
		this.prepareFromRepository();
		return SUCCESS;
	}

	public String view() {
		this.prepareFromRepository();
		return SUCCESS;
	}

	public String edit() {
		
		this.prepareFromID();
		return SUCCESS;
	}

	public String update() {
		
		this.product.setModifyTime(this.timer.getTimestamp());
		if (null != this.file) {
			this.product.setImage(this.uploadFile());
		}
		this.newsService.save(this.product);
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

	public List<News> getProducts() {
		return products;
	}

	public void setProducts(List<News> products) {
		this.products = products;
	}

	public News getProduct() {
		return product;
	}

	public void setProduct(News product) {
		this.product = product;
	}

	public TimestampCreator getTimer() {
		return timer;
	}

	public void setTimer(TimestampCreator timer) {
		this.timer = timer;
	}

	public FileUploader getUploader() {
		return uploader;
	}

	public void setUploader(FileUploader uploader) {
		this.uploader = uploader;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
}
