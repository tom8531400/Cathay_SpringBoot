package com.example.cathay_springboot.service.Impl;

import com.example.cathay_springboot.dao.ForexDao;
import com.example.cathay_springboot.service.ForexService;
import com.example.cathay_springboot.vo.ConvertBinaryDataToObject;
import com.example.cathay_springboot.vo.ForexUsdNtdQuery;
import com.example.cathay_springboot.vo.ForexVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 「外匯業務邏輯」實作方法
 */
@Service
public class ForexServiceImpl implements ForexService {
    @Autowired
    private ForexDao forexDao;

    /**
     * 每日18:00自動觸發方法
     * 取得當日外匯成交資料，並將日期時間和USD/NTD資訊存進sql
     */
    @Override
    @Scheduled(cron = "0 00 18 * * ?")
    public void fetchAndSaveForexData() {
        // 取得外匯成交資料
        List<ForexVo> data = forexdate();
        // 取得的外匯成交資料，將date和USD/NTD資料存至SQL
        for (ForexVo o : data) {
            save(o.getDate(), o.getUsdNtd());
        }
    }

    /**
     * @param startDate 起始日期
     * @param endDate   結束日期
     * @return List<ForexUsdNtdQuery> 自訂查詢資料
     */
    @Override
    public List<ForexUsdNtdQuery> queryCurrency(String startDate, String endDate) {
        List<ForexUsdNtdQuery> data = new ArrayList<>();
        // 移除參數中 "/"
        startDate = startDate.replace("/", "");
        endDate = endDate.replace("/", "");
        // 獲取當前日期
        Date nowDate = new Date();
        // 設定日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        String date = dateFormat.format(nowDate);
        // 先判斷輸入日期是否符合規則(1.字串要等於8位  2.起始日期不能超過一年 3.結束日期需要小於等於昨天日期)
        if (startDate.length() == 8 && endDate.length() == 8 &&
                (Integer.parseInt(date) - Integer.parseInt(startDate)) <= 10000 &&
                Integer.parseInt(endDate) <= Integer.parseInt(date) - 1) {
            // 獲取 起始至結束期間貨幣的匯率 (日期區間僅限 1 年前~當下日期-1 天)
            data = forexDao.queryCurrency(startDate, endDate);
        }
        return data;
    }

    /**
     * 將取得的外匯成交資料，date和USD/NTD資料存至SQL
     *
     * @param date   日期(yyyyMMdd)
     * @param usdNtd 匯率
     */
    @Override
    public void save(String date, String usdNtd) {
        forexDao.save(date, usdNtd);
    }

    /**
     * 取得當日18:00外匯成交資料
     *
     * @return 外匯成交資料
     */
    @Override
    public List<ForexVo> forexdate() {
        // 外匯API地址（可放在配置文件中）
        String forexApi = "https://openapi.taifex.com.tw/v1/DailyForeignExchangeRates";
        // 初始化RestTemplate，以處理Restfull请求
        RestTemplate restTemplate = new RestTemplate();
        // 添加轉換器，處理二進制數據
        restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
        // 使用GET取得數據
        byte[] responseByte = restTemplate.getForObject(forexApi, byte[].class);
        // 利用convertBinaryDataToObject方法將二進制數據轉換為List<ForexVo>
        List<ForexVo> data = new ConvertBinaryDataToObject().convertBinaryDataToObject(responseByte);

        // [方法] 遍歷forexVo數據，取得Date和usdNtd，存入data
        // 實例data來存取每一個ForexVo數值
        List<ForexVo> newData = new ArrayList<>();
        // 遍歷forexVo，每次循環把date和usdNtd存入List<ForexVo> data中
        for (ForexVo o : data) {
            newData.add(new ForexVo(o.getDate(), o.getUsdNtd()));
        }
        return newData;
    }
}