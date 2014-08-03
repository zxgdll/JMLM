package net.earthcoder.jmlm.domain;

import java.util.*;

/**
 * Created by Wei on 2014/3/22.
 */
public final class DailyBook {

    private List<BillItem> billItemList;
    private long dailySum;

    public DailyBook() {
        billItemList = new ArrayList<BillItem>();
    }

    public void addFee(Date date, Human people, long fee) {
        billItemList.add(new BillItem(date, fee, people));
        flushSum();
    }

    protected List<BillItem> getBillItemList() {
        return billItemList;
    }

    private void flushSum() {
        dailySum = 0;
        for (BillItem billItem: billItemList) {
            dailySum += billItem.getFee();
        }
    }

    public long getDailySum() {
        return dailySum;
    }
}
