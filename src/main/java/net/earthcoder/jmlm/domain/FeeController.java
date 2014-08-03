package net.earthcoder.jmlm.domain;

import java.util.*;

public final class FeeController {
    
    private static final long OUT_LEVEL = 600000;

    private boolean frozen = false;
    
    private Fee counselingFee = new CounselingFee();
    private Fee operatingExpenses = new OperatingExpenses();
    private Fee initialFee = new InitialFee();

    protected Map<Date, List<BillItem>> getBillList() {
        Map<Date, List<BillItem>> counselingFeeMap = counselingFee.getBillList();
        Map<Date, List<BillItem>> operatingExpensesMap = operatingExpenses.getBillList();
        for (Date date: counselingFeeMap.keySet()) {
            if (operatingExpensesMap.containsKey(date)) {
                operatingExpensesMap.get(date).addAll(counselingFeeMap.get(date));
            } else {
                operatingExpensesMap.put(date, counselingFeeMap.get(date));
            }
        }
        return operatingExpensesMap;
    }

    private void addFee(Date date, Human content, Fee fee) {
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

    public void addInitialFee(Date date, Human content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, initialFee);
        }
    }

    public void addCounselingFee(Date date, Human content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, counselingFee);
        }
    }

    public void addOperatingExpenses(Date date, Human content) {
        if (frozen) {
            return;
        } else {
            addFee(date, content, operatingExpenses);
        }
    }

    public Fee getCounselingFee() {
        return counselingFee;
    }

    public Fee getOperatingExpenses() {
        return operatingExpenses;
    }

    public Fee getInitialFee() {
        return initialFee;
    }
}
