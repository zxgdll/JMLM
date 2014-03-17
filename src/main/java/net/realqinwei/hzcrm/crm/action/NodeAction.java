package net.realqinwei.hzcrm.crm.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.service.intf.NodeService;
import net.realqinwei.hzcrm.crm.service.intf.UserService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;
import org.apache.log4j.Logger;

public class NodeAction extends ActionSupport {

    private static final long serialVersionUID = 4642124418655980697L;
    private static final Logger LOG = Logger.getLogger(NodeAction.class);

    private NodeService nodeService;
    private UserService userService;
    private Node node;
    private TimestampCreator timer;
    private List<User> userList;
    private Map<Integer, List<Node>> userNodeMap;

    public Map<Integer, List<Node>> getUserNodeMap() {
        return userNodeMap;
    }

    public void setUserNodeMap(Map<Integer, List<Node>> userNodeMap) {
        this.userNodeMap = userNodeMap;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    private void initDoubleSelectContent() {
        this.setUserList(this.getUserService().getUsers());
        this.setUserNodeMap(new HashMap<Integer, List<Node>>());
        for (User usr : this.getUserList()) {
            this.getUserNodeMap().put(usr.getId(), this.getNodeService().findByOwner(usr));
        }
    }

    public String addNode() {
        this.initDoubleSelectContent();
        return SUCCESS;
    }

    public String save() {

        User ownerUser = this.getUserService().findById(this.getNode().getNodeUserID());
        int nodeNumber = this.getNodeService().findByOwner(ownerUser).size() + 1;
        String nodeName = ownerUser.getUserName() + "（" + nodeNumber +  "）";

        this.node.setUserName(nodeName);
        this.node.setUserCreateTime(this.getTimer().getTimestamp());
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

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
