package net.realqinwei.hzcrm.crm.service.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.Image;

public interface ImageService {
	
	public void saveImage(Image image);
	public List<Image> allImages();
	public void deleteImage(Image image);
	public Image findImageById(int imageId);
}
