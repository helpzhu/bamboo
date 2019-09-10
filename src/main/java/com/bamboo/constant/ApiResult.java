package com.bamboo.constant;

/**
 * @author XuZhu
 * @version 1.0
 * @desc
 * @date 2019/9/10 20:46
 * @since JDK1.8
 */
public enum ApiResult {
    SUCCESS("success"),FAILED("failed");

    private String value;

    ApiResult(String value) {
        this.value = value;
    }

    public String value(){
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
