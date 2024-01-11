package com.example.cathay_springboot.vo;

import lombok.Getter;

/**
 * 功能執行回傳結果
 */
public enum ManagerCode {
    /**
     * 查詢成功
     */
    QUERY_SUCCESS("0000", "查詢成功"),
    /**
     * 查詢失敗
     */
    QUERY_Failed("E001", "查詢失敗");

    @Getter
    private String code;
    private String message;

    ManagerCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}