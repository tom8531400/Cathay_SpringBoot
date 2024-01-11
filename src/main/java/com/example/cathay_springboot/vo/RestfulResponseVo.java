package com.example.cathay_springboot.vo;

import lombok.Data;

import java.util.List;

/**
 * RestfulResponse包裝類
 *
 * @param <T> 自訂義class
 */
@Data
public class RestfulResponseVo<T> {
    /**
     * sql查詢的資料
     */
    private List<T> data;
    /**
     * 傳送狀態訊息
     */
    private ManagerCode manager;
    /**
     * 傳送狀態代碼
     */
    private String code;

    public RestfulResponseVo() {
    }

    public RestfulResponseVo(ManagerCode manager, String code,List<T> data) {
        this.data = data;
        this.manager = manager;
        this.code = code;
    }
}
