package net.realqinwei.hzcrm.crm.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionSupport;

import net.earthcoder.jmlm.domain.BinaryNode;
import net.earthcoder.jmlm.domain.BinaryTree;
import net.earthcoder.jmlm.domain.Human;
import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.been.User;
import net.realqinwei.hzcrm.crm.domain.NodeRepository;
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
    private NodeRepository nodeRepository;
    private TreeRepository treeRepository;
    private Map<String, Object> session;
    private List<User> users;
    private List<User> ownersOfAllNodes;
    private List<User> ownersOfnonfullNodes;
    private Map<Integer, List<Node>> allNodesOwnersMap;
    private Map<Integer, List<Node>> nonfullNodesOwnerMap;

    public NodeAction() {
    }

    public String addNode() {
        initNewNodeOwnerSelect();
        initReferNodeSelect();
        initLoaderNodeSelect();
        return SUCCESS;
    }

    private void initNewNodeOwnerSelect() {
        users = userService.getUsers();
    }

    private void initReferNodeSelect() {
        ownersOfAllNodes = userService.getUsersHaveNodes();
        allNodesOwnersMap = new HashMap<Integer, List<Node>>();
        for (User user: ownersOfAllNodes) {
            allNodesOwnersMap.put(user.getId(), nodeService.findByOwner(user));
        }
    }

    private void initLoaderNodeSelect() {
        nonfullNodesOwnerMap = new HashMap<Integer, List<Node>>();
        List<User> allUsers = userService.getUsers();
        BinaryTree binaryTree = (BinaryTree) session.get("tree");
        List<BinaryNode> binaryNodes = binaryTree.getNodes();
        List<Node> nodes;
        for (User user : allUsers) {
            nodes = new ArrayList<Node>();
            for (Node node: nodeService.findByOwner(user)) {
                if (!nodeBeanIsFull(binaryNodes, node)) {
                    nodes.add(node);
                }
            }
            if (nodes.size() != 0) {
                nonfullNodesOwnerMap.put(user.getId(), nodes);
            }
        }
        ownersOfnonfullNodes = new ArrayList<User>();
        for (Integer userID : nonfullNodesOwnerMap.keySet()) {
            ownersOfnonfullNodes.add(userService.findById(userID));
        }
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

    public Map<Integer, List<Node>> getAllNodesOwnersMap() {
        return allNodesOwnersMap;
    }

    public void setAllNodesOwnersMap(Map<Integer, List<Node>> allNodesOwnersMap) {
        this.allNodesOwnersMap = allNodesOwnersMap;
    }

    public TreeRepository getTreeRepository() {
        return treeRepository;
    }

    public void setTreeRepository(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Map<Integer, List<Node>> getNonfullNodesOwnerMap() {
        return nonfullNodesOwnerMap;
    }

    public void setNonfullNodesOwnerMap(Map<Integer, List<Node>> nonfullNodesOwnerMap) {
        this.nonfullNodesOwnerMap = nonfullNodesOwnerMap;
    }

    public List<User> getOwnersOfAllNodes() {
        return ownersOfAllNodes;
    }

    public void setOwnersOfAllNodes(List<User> ownersOfAllNodes) {
        this.ownersOfAllNodes = ownersOfAllNodes;
    }

    public NodeRepository getNodeRepository() {
        return nodeRepository;
    }

    public void setNodeRepository(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<User> getOwnersOfnonfullNodes() {
        return ownersOfnonfullNodes;
    }

    public void setOwnersOfnonfullNodes(List<User> ownersOfnonfullNodes) {
        this.ownersOfnonfullNodes = ownersOfnonfullNodes;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session = session;
    }
}
