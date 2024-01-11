package com.example.cathay_springboot.service;

import com.example.cathay_springboot.vo.ForexUsdNtdQuery;
import com.example.cathay_springboot.vo.ForexVo;

import java.util.List;

/**
 * 「外匯業務邏輯」定義方法
 */
public interface ForexService {
    /**
     * 取得的外匯成交資料，date和USD/NTD資料存至SQL
     *
     * @param date   日期(yyyy-MM-dd)
     * @param usdNtd 匯率
     */
    void save(String date, String usdNtd);

    /**
     * @return 當日18:00外匯成交資料
     */
    List<ForexVo> forexdate();

    /**
     * 每日18:00自動觸發方法
     */
    void fetchAndSaveForexData();

    /**
     * @param startDate 起始日期
     * @param endDate   結束日期
     * @return 起始至結束期間貨幣的匯率 (日期區間僅限 1 年前~當下日期-1 天)
     */
    List<ForexUsdNtdQuery> queryCurrency(String startDate, String endDate);
}