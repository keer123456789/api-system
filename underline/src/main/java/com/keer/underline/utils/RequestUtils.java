package com.keer.underline.utils;

import com.keer.common.crypto.CryptoFactory;
import com.keer.common.crypto.ICrypto;
import com.keer.common.crypto.bytecode.ByteCodeFactory;
import com.keer.common.crypto.bytecode.IByteCode;
import com.keer.common.exception.ByteCodeException;
import com.keer.common.exception.CryptoException;
import com.keer.common.exception.RequestBuilderException;
import com.keer.underline.entity.ApiParam;
import com.keer.underline.entity.Param;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * 构造请求体
 */
public class RequestUtils {
    /**
     * 构造http get 请求
     *
     * @param url
     * @param apiParam
     * @return
     */
    public static HttpGet buildGet(String url, ApiParam apiParam) throws RequestBuilderException, CryptoException, ByteCodeException {
        if (StringUtils.isEmpty(url)) {
            throw new RequestBuilderException("接口地址为空");
        }
        URIBuilder uriBuilder = buildUrl(url, apiParam);
        HttpGet httpGet = null;
        try {
            httpGet = new HttpGet(uriBuilder.build());
        } catch (Exception e) {
            throw new RequestBuilderException("构建uri异常");
        }
        Map<String, String> headers = apiParam.getHeaders();
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpGet.setHeader(key, headers.get(key));
            }
        }
        return httpGet;
    }


    /**
     * 构造http post 请求
     *
     * @param url
     * @param apiParam
     * @return
     */
    public static HttpPost buildPost(String url, ApiParam apiParam) throws RequestBuilderException, CryptoException, ByteCodeException {
        if (StringUtils.isEmpty(url)) {
            throw new RequestBuilderException("接口地址为空");
        }
        URIBuilder uriBuilder = buildUrl(url, apiParam);
        HttpPost httpPost = null;
        try {
            httpPost = new HttpPost(uriBuilder.build());
        } catch (Exception e) {
            throw new RequestBuilderException("构建uri异常");
        }
        Map<String, String> headers = apiParam.getHeaders();
        if (headers != null) {
            for (String key : headers.keySet()) {
                httpPost.setHeader(key, headers.get(key));
            }
        }
        String body = crypto(apiParam.getBodyCryptoType(), apiParam.getBodyCryptoKey(), apiParam.getBody(), apiParam.getBodyCryptoByteEncodeType());
        HttpEntity entity = new StringEntity(body, "UTF-8");
        httpPost.setEntity(entity);
        return httpPost;
    }

    /**
     * 构建url，支持参数加密
     *
     * @param url
     * @param apiParam
     * @return
     * @throws RequestBuilderException
     * @throws CryptoException
     */
    private static URIBuilder buildUrl(String url, ApiParam apiParam) throws RequestBuilderException, CryptoException, ByteCodeException {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder(url);
        } catch (URISyntaxException e) {
            throw new RequestBuilderException(e.getMessage());
        }
        if (apiParam.getQueryParams().isEmpty()) {
            return uriBuilder;
        }
        List<Param> params = apiParam.getQueryParams();
        if (StringUtils.isEmpty(apiParam.getParamValueCryptoKey())) {
            throw new RequestBuilderException("参数加密key不存在");
        }
        for (Param param : params) {
            uriBuilder.addParameter(param.getName(), param.getName() + ":" + crypto(param.getCryptoType(), param.getValue(), apiParam.getParamValueCryptoKey(), apiParam.getParamCryptoByteEncodeType()));
        }
        return uriBuilder;
    }

    /**
     * 字符串加密方式
     *
     * @param type           加密方式
     * @param value          待加密的字符串
     * @param key            加密密钥
     * @param byteEncodeType 加密后的字节数组编码方式，base64，hex
     * @return
     * @throws RequestBuilderException
     * @throws CryptoException
     */
    private static String crypto(String type, String value, String key, String byteEncodeType) throws RequestBuilderException, CryptoException, ByteCodeException {
        ICrypto crypto = CryptoFactory.getCrypto(type);
        if (crypto == null) {
            throw new RequestBuilderException("参数加密方式：" + type + " 不存在");
        }
        byte[] encrypt = crypto.encrypt(value.getBytes(), key);
        IByteCode code = ByteCodeFactory.getCode(byteEncodeType);
        if (code == null) {
            throw new RequestBuilderException("编码方式不存在");
        }
        return code.encode(encrypt);

    }
}
