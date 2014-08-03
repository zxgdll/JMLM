package net.realqinwei.hzcrm.crm.action;

import com.opensymphony.xwork2.ActionSupport;

import net.earthcoder.jmlm.domain.BinaryNode;
import net.earthcoder.jmlm.domain.BinaryTree;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

public class TreeAction extends ActionSupport {

	private static final Logger LOG = Logger.getLogger(TreeAction.class);
	
	private TreeRepository treeRepository;
    private String content;

	public void setTreeRepository(TreeRepository treeRepository) {
		this.treeRepository = treeRepository;
	}

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

	public String show() {
        String nodeID = ServletActionContext.getRequest().getParameter("nodeID");
        BinaryTree tree = treeRepository.rebuild();
        if (StringUtils.isEmpty(nodeID)) {
            content = outputContent(tree.getRootNode());
            return "admin";
        } else {
            for (BinaryNode bn : tree.getNodes()) {
                if (bn.getContent().nodeID().equals(Integer.valueOf(nodeID))) {
                    content = outputContent(bn);
                    break;
                }
            }
            return SUCCESS;
        }

	}

    private String outputContent(BinaryNode tree) {
        StringBuilder content = new StringBuilder();
        content.append("<li>");
        content.append("<a href=\"#\">");
        content.append(tree.getContent().nodeID()).append(" ").append(tree.getContent().name());
        content.append("</a>");
        BinaryNode[] childs = tree.getChilds();
        if (null != childs[0]) {
            content.append("<ul>");
            content.append(outputContent(childs[0]));
            if (null != childs[1]) {
                content.append(outputContent(childs[1]));
            }
            content.append("</ul>");
        }
        content.append("</li>");
        return content.toString();
    }
}
