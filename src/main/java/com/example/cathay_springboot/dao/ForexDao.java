package com.example.cathay_springboot.dao;

import com.example.cathay_springboot.vo.ForexUsdNtdQuery;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 處理外匯數據在SQL中資料
 */
@Mapper
public interface ForexDao {
    /**
     * @param date   日期
     * @param usdNtd 美元對台幣的匯率
     */
    @Insert("insert into Forex_Usd_Ntd(date,usd_ntd)values(#{date},#{usdNtd})")
    void save(String date, String usdNtd);

    /**
     * @param startDate 起始日期
     * @param endDate   結束日期
     * @return 起始至結束期間貨幣的匯率 (日期區間僅限 1 年前~當下日期-1 天)
     */
    @Results({
            @Result(property = "usdNtd", column = "usd_ntd"),
    })
    @Select("select date,usd_ntd from Forex_Usd_Ntd where date between #{startDate} and #{endDate}")
    List<ForexUsdNtdQuery> queryCurrency(String startDate, String endDate);
}