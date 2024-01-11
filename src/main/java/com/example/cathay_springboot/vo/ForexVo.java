package com.example.cathay_springboot.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 每日外匯成交資料
 */
@Data
public class ForexVo {
    /**
     * 編號
     */
    private Integer id;

    /**
     * 日期(yyyy-MM-dd)
     */
    @JsonProperty("Date")
    @JsonFormat(pattern = "yyyyMMdd")
    private String date;

    /**
     * 起始日期(yyyy-MM-dd)
     */
    @JsonFormat(pattern = "yyyyMMdd")
    private String startDate;

    /**
     * 結束日期(yyyy-MM-dd)
     */
    @JsonFormat(pattern = "yyyyMMdd")
    private String endDate;

    /**
     * 美元對台幣的匯率
     */
    @JsonProperty("USD/NTD")
    private String usdNtd;

    /**
     * 人民幣對台幣的匯率
     */
    @JsonProperty("RMB/NTD")
    private String rmbNtd;

    /**
     * 歐元對美元的匯率
     */
    @JsonProperty("EUR/USD")
    private String eurUsd;

    /**
     * 美元對日元的匯率
     */
    @JsonProperty("USD/JPY")
    private String usdJpy;

    /**
     * 美元對日元的匯率
     */
    @JsonProperty("GBP/USD")
    private String gbpUsd;

    /**
     * 澳元對美元的匯率
     */
    @JsonProperty("AUD/USD")
    private String audUsd;

    /**
     * 美元對港幣的匯率
     */
    @JsonProperty("USD/HKD")
    private String usdHkd;

    /**
     * 美元對人民幣的匯率
     */
    @JsonProperty("USD/RMB")
    private String usdRmb;

    /**
     * 美元對南非蘭特的匯率
     */
    @JsonProperty("USD/ZAR")
    private String usdZar;

    /**
     * 紐西蘭元對美元的匯率
     */
    @JsonProperty("NZD/USD")
    private String nzdUsd;

    public ForexVo() {
    }

    public ForexVo(String date, String usdNtd) {
        this.date = date;
        this.usdNtd = usdNtd;
    }

    public ForexVo(String date, String startDate, String endDate) {
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}