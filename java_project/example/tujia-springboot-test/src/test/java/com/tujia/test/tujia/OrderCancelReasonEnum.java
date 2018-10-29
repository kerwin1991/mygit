package com.tujia.test.tujia;

import java.util.Arrays;

/**
 * @author xiaopengw
 * @date 2018/10/19
 * @remark
 */
public enum OrderCancelReasonEnum {

    None(0, "无"),
    UnpaidTimeout(1, "超时未支付"),
    //房东拒绝
    RefusedByHotel(2, "酒店无房"),
    CanceledByCustomer(3, "行程变更"),
    ConfirmTimeout(4, "超时未确认"),
    UnitNoInventory(5, "指定房间无房"),
    PRICE_CHANGED(6, "价格不符"),
    ACCESS_SYSTEM_ORDER_fAILED(7, "接入系统成单失败"),
    ACCESS_SYSTEM_NETWORK_EXCEPTION(8, "接入系统网络异常"),

    Other(100, "其他");

    private final Integer code;
    private final String desc;

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    OrderCancelReasonEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static OrderCancelReasonEnum getByCode(Integer code) {
        return Arrays.stream(OrderCancelReasonEnum.values()).filter(a -> a.getCode().equals(code)).findFirst().orElse(null);

    }

    public static void main(String[] args) {
        System.out.println(OrderCancelReasonEnum.getByCode(999));
    }
}
