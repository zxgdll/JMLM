package net.realqinwei.hzcrm.crm.been;

import java.io.Serializable;
import java.sql.Timestamp;

public final class News implements Serializable, Comparable<News> {

	private static final long serialVersionUID = -7829131466971951597L;

	public static final int TYPE_NEWS = 1;
	public static final int TYPE_ABOUT = 2;
	public static final int TYPE_CONTACT = 3;
	public static final int TYPE_PRODUCT = 4;
	public static final int TYPE_SCHOOL = 5;

	private Integer id;

	private String title;
	private String content;
	private Timestamp createTime;
	private Timestamp modifyTime;
	private Integer type;
	private String image;
	
	public String getSample() {
		StringBuilder str = new StringBuilder(this.content.substring(0, 100));
		str.append(" ...");
		return str.toString();
	}

	private String objectID() {
		return this.modifyTime.toString();
	}

	@Override
	public String toString() {
		return "[" + this.id + ", " + this.title + ", " + this.content + ", "
				+ this.createTime + ", " + this.modifyTime + ", " + this.type
				+ ", " + this.image + "]";
	}

	@Override
	public boolean equals(Object anotherObject) {
		if (null == anotherObject) {
			return false;
		} else if (this == anotherObject) {
			return true;
		} else {
			News anotherUser = (News) anotherObject;
			return anotherUser.objectID().equals(this.objectID()) ? true
					: false;
		}
	}

	@Override
	public int hashCode() {
		return this.objectID().hashCode();
	}

	public News() {

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Timestamp modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	@Override
	public int compareTo(News other) {
		return this.modifyTime.compareTo(other.getModifyTime());
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
