package net.realqinwei.hzcrm.crm.been;

import net.earthcoder.jmlm.domain.Human;

import java.sql.Timestamp;
import java.util.Date;

public final class Node implements java.io.Serializable, Comparable<Node>, Human {

    private Integer id;
    private String userName;
    private Timestamp userCreateTime;
    private Integer userReferID;
    private Integer nodeUserID;
    private Integer nodeLoaderID;

    public Node() {

    }

    /**
     * minimal constructor
     */
    public Node(String userName, Timestamp userCreateTime) {

        this.userName = userName;
        this.userCreateTime = userCreateTime;
    }

    /**
     * full constructor
     */
    public Node(String userName,
                Timestamp userCreateTime, Integer userReferID, Integer nodeUserID,
                Integer nodeLoaderID) {

        this(userName, userCreateTime);

        this.userReferID = userReferID;
        this.nodeUserID = nodeUserID;
        this.nodeLoaderID = nodeLoaderID;
    }

    @Override
    public String toString() {
        return "(" + id.toString() + ") " + userName;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }
        if (null == anotherObject) {
            return false;
        }
        if (getClass() != anotherObject.getClass()) {
            return false;
        }
        Node anotherUser = (Node) anotherObject;
        return id.equals(anotherUser.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Timestamp getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Timestamp userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public Integer getUserReferID() {
        return userReferID;
    }

    public void setUserReferID(Integer userReferID) {
        this.userReferID = userReferID;
    }

    public Integer getNodeUserID() {
        return nodeUserID;
    }

    public void setNodeUserID(Integer nodeUserID) {
        this.nodeUserID = nodeUserID;
    }

    public Integer getNodeLoaderID() {
        return nodeLoaderID;
    }

    public void setNodeLoaderID(Integer nodeLoaderID) {
        this.nodeLoaderID = nodeLoaderID;
    }

    @Override
    public int compareTo(Node other) {
        return this.userCreateTime.compareTo(other.getUserCreateTime());
    }

    @Override
    public String name() {
        return userName;
    }

    @Override
    public Integer nodeID() {
        return id;
    }

    @Override
    public Integer referNodeID() {
        return userReferID;
    }

    @Override
    public Integer ownerUserID() {
        return nodeUserID;
    }

    @Override
    public Integer loadNodeID() {
        return nodeLoaderID;
    }

    @Override
    public Date initDateTime() {
        return new Date(userCreateTime.getTime());
    }
}
