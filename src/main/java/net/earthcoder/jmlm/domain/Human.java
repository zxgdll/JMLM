package net.earthcoder.jmlm.domain;

import java.util.Date;

public interface Human {
    
    String name();
    Integer nodeID();
    Integer referNodeID();
    Integer ownerUserID();
    Integer loadNodeID();
    Date initDateTime();
}
