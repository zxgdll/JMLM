package net.earthcoder.ganoderma.fee;

import java.util.Date;
import net.earthcoder.ganoderma.man.Human;

public final class FeeController<E extends Human> {
    
    private static final long OUT_LEVEL = 600000;

    private boolean frozen = false;
    
    private Fee<E> counselingFee = new CounselingFee<E>();
    private Fee<E> operatingExpenses = new OperatingExpenses<E>();

    public Fee<E> getCounselingFee() {
        return counselingFee;
    }

    public Fee<E> getOperatingExpenses() {
        return operatingExpenses;
    }
    
    private void addFee(Date date, E content, Fee<E> fee) {
        long sum = counselingFee.sum() + operatingExpenses.sum();
        if (sum + fee.defaultValue() < OUT_LEVEL) {
            fee.add(date, content, fee.defaultValue());
        } else if (sum + fee.defaultValue() == OUT_LEVEL) {
            fee.add(date, content, fee.defaultValue());
            frozen = true;
        } else {
            fee.add(date, content, OUT_LEVEL - sum);
            frozen = true;
        }
    }

    public void addCounselingFee(Date date, E content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, counselingFee);
        }
    }

    public void addOperatingExpenses(Date date, E content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, operatingExpenses);
        }
    }
}
