package net.earthcoder.jmlm.domain;

import java.util.List;

public interface PointRepository {
    public List<Point> findAll();
    public Point findById(int id);
}
