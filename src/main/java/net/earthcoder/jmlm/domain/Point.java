package net.earthcoder.jmlm.domain;

import java.util.Date;

/**
 * Created by Wei on 2014/3/23.
 */
public interface Point extends Comparable<Point> {

    Date getCreateDateTime();
}
