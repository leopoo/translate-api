package com.leopoo.translate.enums;

/**
 * @author zbb
 * @create 2017-06-15 15:23
 */
public enum ResultState {
    SUCCESS(1), FAILD(0);

    private final int status;

    ResultState(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
