package net.earthcoder.jmlm.domain;

import java.util.Date;

/**
 * Created by Wei on 2014/3/23.
 */
public interface Tree {

    void addNode(Human people, Date crateDate, Integer fatherNodeID, String flag);
    void print();
}
