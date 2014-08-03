package net.earthcoder.jmlm.domain;

/**
 * Created by Wei on 2014/3/31.
 */
public class BinaryPlanArea {

    private long areaHistoryResult;
    private long areaCurrentResult;
    private long pointNumber;

    public long getAreaHistoryResult() {
        return areaHistoryResult;
    }

    public void setAreaHistoryResult(long areaHistoryResult) {
        this.areaHistoryResult = areaHistoryResult;
    }

    public long getAreaCurrentResult() {
        return areaCurrentResult;
    }

    public void setAreaCurrentResult(long areaCurrentResult) {
        this.areaCurrentResult = areaCurrentResult;
    }

    public long getPointNumber() {
        return pointNumber;
    }

    public void setPointNumber(long pointNumber) {
        this.pointNumber = pointNumber;
    }
}
