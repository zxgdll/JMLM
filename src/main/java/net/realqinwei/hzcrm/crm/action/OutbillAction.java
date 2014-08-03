package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;
import net.earthcoder.jmlm.domain.BillItem;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OutbillAction extends ActionSupport {

	private static final Logger LOG = Logger.getLogger(OutbillAction.class);

	private TreeRepository treeRepository;
	
	@Override
	public String execute() throws Exception {
        Map<Date, List<BillItem>> map = treeRepository.rebuild().getDailyBillList();
        LOG.debug(map.size());
		ServletActionContext.getRequest().setAttribute("outbill", map);
		return SUCCESS;
	}

	public void setTreeRepository(TreeRepository treeRepository) {
		this.treeRepository = treeRepository;
	}
}
