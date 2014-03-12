package net.earthcoder.ganoderma.fee;

import java.util.*;

import net.earthcoder.ganoderma.BillItem;
import net.earthcoder.ganoderma.Util;
import net.earthcoder.ganoderma.man.*;

public final class CounselingFee<E extends Human> extends AbstractFee<E> {
    
    private static final long DEFAULT_FEE = 100;
    private static final long DAILY_CAP = 2000;

    @Override
    public void add(Date date, E people) {
        add(date, people, DEFAULT_FEE);
    }

    @Override
    public void add(Date date, E people, long fee) {
        BillItem<E> item = null;
        Date key = Util.trunc(date);
        if (books.containsKey(key)) {
            List<BillItem<E>> list = books.get(key);
            long dailySum = 0;
            for (BillItem<E> billItem: list) {
                dailySum += billItem.getFee();
            }
            if (dailySum >= DAILY_CAP) {
                return;
            } else if (dailySum + fee > DAILY_CAP) {
                item = new BillItem<E>(date, dailySum + fee - DAILY_CAP, people);
            } else {
                item = new BillItem<E>(date, fee, people);
            }
            list.add(item);  
        } else {
            List<BillItem<E>> list = new ArrayList<BillItem<E>>();
            item = new BillItem<E>(date, fee, people);
            list.add(item);
            books.put(key, list);
        }
        sum += item.getFee();
    }

    @Override
    public long defaultValue() {
        return DEFAULT_FEE;
    }
}
