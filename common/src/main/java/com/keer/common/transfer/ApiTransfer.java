package com.keer.common.transfer;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

/**
 * 每个接口的转换逻辑都要适配此接口
 */
public interface ApiTransfer {
    /**
     * 将接口返回的数据 转化为Workbook
     * 目前只支持xlsx 文件
     *
     * @param result 接口放回的字符串
     * @return
     */
    Workbook transfer(String result);
    /**
     * 将接口返回的数据,批量 转化为Workbook
     * 目前只支持xlsx 文件
     *
     * @param results 接口放回的字符串
     * @return
     */
    Workbook transfer(List<String> results);
}
