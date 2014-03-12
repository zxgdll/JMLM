package net.realqinwei.hzcrm.crm.dao.intf;

import java.util.List;

import net.realqinwei.hzcrm.crm.been.Image;

public interface ImageDAO {
	
	public void save(Image image);
	public List<Image> all();
	public void delete(Image image);
	public Image findImageById(Integer imageId);
}
