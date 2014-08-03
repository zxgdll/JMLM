package net.earthcoder.jmlm.domain;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface Fee {

    long sum();
    void add(Date date, Human people);
    void add(Date date, Human people, long fee);
    long defaultValue();
    Map<Date, List<BillItem>> getBillList();
}
