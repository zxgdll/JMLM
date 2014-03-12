package net.realqinwei.hzcrm.crm.been;

import java.io.Serializable;


public class Image implements Serializable {

	private static final long serialVersionUID = -2241314414891724792L;
	
	private Integer imageId;
	private String imageName;
	private String imageUrl;
	
	@Override
	public boolean equals(Object anotherObject) {
		if (null == anotherObject) {
			return false;
		} else if (this == anotherObject) {
			return true;
		} else {
			Image anotherImage = (Image) anotherObject;
			return anotherImage.imageUrl.equals(this.imageUrl) ? true : false;
		}
	}

	@Override
	public int hashCode() {
		return this.imageUrl.hashCode();
	}
	
	@Override
	public String toString() {
		return this.imageUrl;
	}
	
	public Integer getImageId() {
		
		return imageId;
	}
	
	public void setImageId(Integer imageId) {
	
		this.imageId = imageId;
	}
	
	public String getImageName() {
	
		return imageName;
	}
	
	public void setImageName(String imageName) {
	
		this.imageName = imageName;
	}
	
	public String getImageUrl() {
	
		return imageUrl;
	}
	
	public void setImageUrl(String imageUrl) {
	
		this.imageUrl = imageUrl;
	}
}
