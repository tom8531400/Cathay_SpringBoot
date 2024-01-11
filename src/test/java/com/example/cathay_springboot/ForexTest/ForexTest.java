package com.example.cathay_springboot.ForexTest;

import com.example.cathay_springboot.service.ForexService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 外匯資訊方法(測試類)
 */
@SpringBootTest
public class ForexTest {
    @Autowired
    private ForexService forexService;

    /**
     * 批次每日 18:00 呼叫 API，取得外匯成交資料，並將每日的美元/台幣
     * 欄位(USD/NTD)資料與日期(yyyy-MM-dd HH:mm:ss) insert 至 table/collection
     */
    @Test
    public void unTest() {
        forexService.fetchAndSaveForexData();
    }

    /**
     * 從 DB 取出日期區間內美元/台幣的歷史資料
     */
    @Test
    public void queryTest() {
        System.out.println(forexService.queryCurrency("2024/01/01", "2024/01/06"));
    }
}