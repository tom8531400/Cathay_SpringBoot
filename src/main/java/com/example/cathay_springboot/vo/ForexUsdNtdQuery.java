package com.example.cathay_springboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 自訂義查詢條件類
 *
 */
@Data
public class ForexUsdNtdQuery {
    /**
     * 日期(yyyy-MM-dd)
     */
    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyyMMdd")
    private String date;

    /**
     * 美元對台幣的匯率
     */
    @JsonProperty("USD/NTD")
    private String usdNtd;
}