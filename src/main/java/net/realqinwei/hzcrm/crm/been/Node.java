package net.realqinwei.hzcrm.crm.been;

import java.sql.Timestamp;

public final class Node implements java.io.Serializable, Comparable<Node> {

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
        return this.userName;
    }

    private String objectID() {
        return this.id + this.userCreateTime.toString();
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (null == anotherObject) {
            return false;
        } else if (this == anotherObject) {
            return true;
        } else {
            Node anotherUser = (Node) anotherObject;
            return anotherUser.objectID().equals(this.objectID()) ? true : false;
        }
    }

    @Override
    public int hashCode() {
        return this.objectID().hashCode();
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
}
