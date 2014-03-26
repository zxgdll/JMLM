package net.realqinwei.hzcrm.crm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import net.earthcoder.jmlm.domain.BinaryNode;
import net.earthcoder.jmlm.domain.BinaryTree;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.TreeRepository;
import net.realqinwei.hzcrm.crm.service.intf.NodeService;
import net.realqinwei.hzcrm.crm.service.intf.UserService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.SessionAware;

public class NodeAction extends ActionSupport implements SessionAware {

    private static final Logger LOG = Logger.getLogger(NodeAction.class);

    private NodeService nodeService;
    private UserService userService;
    private Node node;
    private TimestampCreator timer;
    private List<User> userList;
    private List<User> usersHaveNodes;
    private Map<Integer, List<Node>> userNodeMap;
    private TreeRepository treeRepository;
    private Map<String, Object> session;

    public String addNode() {
        initDoubleSelectContent();
        return SUCCESS;
    }

    private void initDoubleSelectContent() {
        setUserList(getUserService().getUsers());
        setUserNodeMap(new HashMap<Integer, List<Node>>());
        BinaryTree binaryTree = (BinaryTree) session.get("tree");
        List<BinaryNode> binaryNodes = binaryTree.getNodes();
        List<Node> nodes;
        for (User usr : getUserList()) {
            nodes = new ArrayList<Node>();
            for (Node node: getNodeService().findByOwner(usr)) {
                if (!nodeBeanIsFull(binaryNodes, node)) {
                    nodes.add(node);
                }
            }
            getUserNodeMap().put(usr.getId(), nodes);
        }
        setUsersHaveNodes(getUserService().getUsersHaveNodes());
    }

    private boolean nodeBeanIsFull(List<BinaryNode> binaryNodes, Node nodeBean) {
        for (BinaryNode binaryNode: binaryNodes) {
            if (nodeBean.equals(binaryNode.getContent()) && binaryNode.isFull()) {
                return true;
            }
        }
        return false;
    }

    public String save() {
        User ownerUser = getUserService().findById(getNode().getNodeUserID());
        int nodeNumber = getNodeService().findByOwner(ownerUser).size() + 1;
        String nodeName = ownerUser.getUserName() + "（" + nodeNumber +  "）";
        node.setUserName(nodeName);
        node.setUserCreateTime(getTimer().getTimestamp());
        this.getNodeService().save(node);
        session.put("tree", treeRepository.rebuild());
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

    public List<User> getUsersHaveNodes() {
        return usersHaveNodes;
    }

    public void setUsersHaveNodes(List<User> usersHaveNodes) {
        this.usersHaveNodes = usersHaveNodes;
    }

    public TreeRepository getTreeRepository() {
        return treeRepository;
    }

    public void setTreeRepository(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
