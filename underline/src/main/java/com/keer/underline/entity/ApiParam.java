package com.keer.underline.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: 张经伦
 * @Date: 2023/9/9  13:16
 * @Description: 请求参数
 */
@Data
public class ApiParam {
    /**
     * 签名名称
     */
    private String signatureName;
    /**
     * 请求签名方式
     */
    private String signatureType;
    /**
     * 签名key
     */
    private String signatureKey;
    /**
     * 请求体加密方式
     */
    private String bodyCryptoType;
    /**
     * 请求体加密key
     */
    private String bodyCryptoKey;
    /**
     * 请求体加密编码方式
     */
    private String bodyCryptoByteEncodeType;
    /**
     * 参数值加密key
     */
    private String paramValueCryptoKey;
    /**
     * 参数值加密后编码方式
     */
    private String paramCryptoByteEncodeType;
    /**
     * 请求头
     */
    private Map<String, String> headers;
    /**
     * 请求参数
     */
    private List<Param> queryParams;
    /**
     * 请求体
     */
    private String body;

}
