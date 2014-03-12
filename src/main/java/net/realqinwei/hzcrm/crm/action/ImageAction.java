package net.realqinwei.hzcrm.crm.action;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.Image;
import net.realqinwei.hzcrm.crm.service.intf.ImageService;
import net.realqinwei.hzcrm.crm.util.FileUploader;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class ImageAction extends ActionSupport {

	private static final long serialVersionUID = 235762445920133334L;
	
	private static final Logger LOG = Logger.getLogger(ImageAction.class);
	
	private ImageService imageService;
	private FileUploader uploader;
	private String imageName;
	private File file;
	private String fileFileName;
	private List<Image> images;
	private String imageId;
	
	public String delete() {
		this.imageService.deleteImage(this.imageService.findImageById(Integer.valueOf(this.imageId)));
		return SUCCESS;
	}
	
	public String upload() {
		Image image = new Image();
		image.setImageName(this.imageName);
		if (null != this.file) {
			image.setImageUrl(this.uploadFile());
		}
		this.imageService.saveImage(image);
		return SUCCESS;
	}
	
	public String list() {
		this.images = this.imageService.allImages();
		return SUCCESS;
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
	
	// Setters & Getters
	
	public ImageService getImageService() {
		return imageService;
	}
	
	public void setImageService(ImageService imageService) {
		this.imageService = imageService;
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
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	
	public List<Image> getImages() {
		return images;
	}
	
	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public String getImageId() {
		return imageId;
	}
	
	public void setImageId(String imageId) {
		this.imageId = imageId;
	}
}
