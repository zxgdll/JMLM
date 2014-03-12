package net.realqinwei.hzcrm.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import net.realqinwei.hzcrm.crm.been.Image;
import net.realqinwei.hzcrm.crm.dao.intf.ImageDAO;

public final class ImageDAOImpl extends HibernateDaoSupport implements ImageDAO {

	@Override
	public void save(Image image) {
		this.getHibernateTemplate().save(image);
		this.getHibernateTemplate().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Image> all() {
		return (List<Image>) this.getHibernateTemplate().find("FROM Image");
	}

	@Override
	public void delete(Image image) {
		this.getHibernateTemplate().delete(image);
		this.getHibernateTemplate().flush();
	}

	@Override
	public Image findImageById(Integer imageId) {
		return null == imageId ? null : (Image) this.getHibernateTemplate().get(Image.class, imageId);
	}
}
