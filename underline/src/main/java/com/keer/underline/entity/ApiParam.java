package com.keer.underline.entity;

import java.util.Map;

/**
 * @Author: 张经伦
 * @Date: 2023/9/9  13:16
 * @Description:
 */
public class ApiParam {
    private boolean needSignature;
    private String bodyCryptoType;
    private Map<String,String> headers;
    private Map<String,String> queryParams;
    private String body;
}
