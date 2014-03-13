package net.realqinwei.hzcrm.crm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.BigUser;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.service.intf.BigUserService;
import net.realqinwei.hzcrm.crm.service.intf.NodeService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;
import org.apache.log4j.Logger;

public class NodeAction extends ActionSupport {

    private static final long serialVersionUID = 4642124418655980697L;
    private static final Logger LOG = Logger.getLogger(NodeAction.class);

    private NodeService nodeService;
    private BigUserService bigUserService;
    private Node node;
    private TimestampCreator timer;
    private List<BigUser> userList;
    private Map<Integer, List<Node>> userNodeMap;

    public Map<Integer, List<Node>> getUserNodeMap() {
        return userNodeMap;
    }

    public void setUserNodeMap(Map<Integer, List<Node>> userNodeMap) {
        this.userNodeMap = userNodeMap;
    }

    public List<BigUser> getUserList() {
        return userList;
    }

    public void setUserList(List<BigUser> userList) {
        this.userList = userList;
    }

    public BigUserService getBigUserService() {
        return bigUserService;
    }

    public void setBigUserService(BigUserService bigUserService) {
        this.bigUserService = bigUserService;
    }

    private void initDoubleSelectContent() {
        this.setUserList(this.bigUserService.getUsers());
        this.userNodeMap = new HashMap<Integer, List<Node>>();
        for (BigUser bigUser : this.getUserList()) {
            this.userNodeMap.put(bigUser.getId(), this.getNodeService().findByOwner(bigUser));
        }
        LOG.debug(this.userNodeMap.get(3).size());
    }

    public String addNode() {
        this.initDoubleSelectContent();
        return SUCCESS;
    }

    public String save() {
        this.getNodeService().save(this.node);
        return SUCCESS;
    }

    public NodeService getNodeService() {
        return nodeService;
    }

    public void setNodeService(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public TimestampCreator getTimer() {
        return timer;
    }

    public void setTimer(TimestampCreator timer) {
        this.timer = timer;
    }
}
