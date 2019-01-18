package com.tujia.test.thread.multi;

/**
 * @author xiaopengw
 * @date 2018/10/29
 * @remark
 */
public class GetPushAfterInfo {
    /**
     * 推送成功条数
     */
    private int pushSuccessNum;
    /**
     * 推送总条数
     */
    private int pushTotalNum;

    public int getPushSuccessNum() {
        return pushSuccessNum;
    }

    public void setPushSuccessNum(int pushSuccessNum) {
        this.pushSuccessNum = pushSuccessNum;
    }

    public int getPushTotalNum() {
        return pushTotalNum;
    }

    public void setPushTotalNum(int pushTotalNum) {
        this.pushTotalNum = pushTotalNum;
    }
}
