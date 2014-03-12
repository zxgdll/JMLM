package com.nm_huazhi.struts2.comparator;

import java.util.Comparator;

import net.realqinwei.hzcrm.crm.been.News;

public final class NewsModifytimeComparator implements Comparator<News> {

	@Override
	public int compare(News news1, News news2) {
		return -1 * news1.getModifyTime().compareTo(news2.getModifyTime());
	}
}
