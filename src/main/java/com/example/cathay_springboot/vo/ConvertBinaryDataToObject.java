package com.example.cathay_springboot.vo;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 將二進制數據轉換為List<ForexVo>
 */
public class ConvertBinaryDataToObject {
    /**
     * @param binryData byte[]
     * @return List<ForexVo>
     */
    public List<ForexVo> convertBinaryDataToObject(byte[] binryData) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return Arrays.asList(objectMapper.readValue(binryData, ForexVo[].class));
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}