package com.example.cathay_springboot.controller;

import com.example.cathay_springboot.service.ForexService;
import com.example.cathay_springboot.vo.ForexUsdNtdQuery;
import com.example.cathay_springboot.vo.ManagerCode;
import com.example.cathay_springboot.vo.RestfulResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 每日外匯資訊服務控制器
 */
@RestController
@RequestMapping("/forex")
public class ForexController {
    @Autowired
    private ForexService forexService;

    /**
     * 每日18:00自動觸發程式
     * 並將美元對台幣資訊以及日期時間存至sql
     */
    @RequestMapping(value = "/forexApi-save", method = {RequestMethod.GET}
            , produces = {MediaType.APPLICATION_JSON_VALUE})
    public void forexSave() {
        forexService.fetchAndSaveForexData();
    }

    /**
     * @param requestBody 起始和結束日期
     * @return 查詢結果和查詢狀態
     */
    @RequestMapping(value = "/forexApi-query", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestfulResponseVo<ForexUsdNtdQuery> queryCurrency(@RequestBody Map<String, String> requestBody) {
        // 提取key (value對應的日期)
        String startDate = requestBody.get("startDate");
        String endDate = requestBody.get("endDate");
        // 帶入參數回傳包裝檔
        List<ForexUsdNtdQuery> data = forexService.queryCurrency(startDate, endDate);
        // 如果data有值，就顯示QUERY_SUCCESS，沒有值就顯示QUERY_Failed
        if (data.size() != 0) {
            return new RestfulResponseVo<>(ManagerCode.QUERY_SUCCESS, ManagerCode.QUERY_SUCCESS.getCode(), data);
        } else {
            return new RestfulResponseVo<>(ManagerCode.QUERY_Failed, ManagerCode.QUERY_Failed.getCode(), data);
        }
    }
}