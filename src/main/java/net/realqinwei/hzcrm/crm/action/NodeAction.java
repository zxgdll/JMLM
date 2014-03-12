package net.realqinwei.hzcrm.crm.action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import net.realqinwei.hzcrm.crm.been.Node;
import net.realqinwei.hzcrm.crm.service.intf.BigUserService;
import net.realqinwei.hzcrm.crm.service.intf.NodeService;
import net.realqinwei.hzcrm.crm.util.TimestampCreator;

public class NodeAction extends ActionSupport {

    private static final long serialVersionUID = 4642124418655980697L;

    private NodeService service;
    private BigUserService bigUserService;
    private Node node;
    private TimestampCreator timer;
    private List<Node> list;

    public BigUserService getBigUserService() {
        return bigUserService;
    }

    public void setBigUserService(BigUserService bigUserServie) {
        this.bigUserService = bigUserServie;
    }
    
    public NodeService getService() {
        return service;
    }

    public void setService(NodeService service) {
        this.service = service;
    }

    public String addNode() {
        this.list = service.getNodes();
        return SUCCESS;
    }
    
    public String save() {

        this.service.save(this.node);
        return SUCCESS;
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
    
    public List<Node> getList() {
        return list;
    }

    public void setList(List<Node> list) {
        this.list = list;
    }

    public void setBigUserServie(net.realqinwei.hzcrm.crm.service.impl.BigUserServiceImpl bigUserServie) {
        //To change body of created methods use File | Settings | File Templates.
    }
}
