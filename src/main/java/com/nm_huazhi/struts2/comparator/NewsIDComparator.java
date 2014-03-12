package com.nm_huazhi.struts2.comparator;

import java.util.Comparator;

import net.realqinwei.hzcrm.crm.been.News;

public final class NewsIDComparator implements Comparator<News> {

	@Override
	public int compare(News news1, News news2) {
		return news1.getId().compareTo(news2.getId());
	}
}
