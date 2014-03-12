package net.earthcoder.ganoderma.fee;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.earthcoder.ganoderma.BillItem;
import net.earthcoder.ganoderma.man.Human;

public abstract class AbstractFee<E extends Human> implements Fee<E> {
    
    protected long sum = 0;
    protected Map<Date, List<BillItem<E>>> books = new HashMap<Date, List<BillItem<E>>>();

    public long sum() {
        return sum;
    }

    public abstract long defaultValue();
    public abstract void add(Date date, E people);
    public abstract void add(Date date, E people, long fee);
}
