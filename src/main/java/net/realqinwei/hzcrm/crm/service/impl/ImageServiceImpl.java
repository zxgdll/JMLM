package net.realqinwei.hzcrm.crm.service.impl;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.Image;
import net.realqinwei.hzcrm.crm.dao.intf.ImageDAO;
import net.realqinwei.hzcrm.crm.service.intf.ImageService;

public final class ImageServiceImpl implements ImageService {
	
	private ImageDAO imageDAO;

	@Override
	public void saveImage(Image image) {
		this.imageDAO.save(image);
	}

	@Override
	public List<Image> allImages() {
		return this.imageDAO.all();
	}
	
	public ImageDAO getImageDAO() {
		return imageDAO;
	}

	public void setImageDAO(ImageDAO imageDAO) {
		this.imageDAO = imageDAO;
	}

	@Override
	public void deleteImage(Image image) {
		this.imageDAO.delete(image);
	}

	@Override
	public Image findImageById(int imageId) {
		return this.imageDAO.findImageById(imageId);
	}
}
