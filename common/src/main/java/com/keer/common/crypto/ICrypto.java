package com.keer.common.crypto;

import com.keer.common.exception.CryptoException;

/**
 * @author keer
 * @date 2022-06-08
 */
public interface ICrypto {

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key 加密密码
     * @return
     */
    byte[] encrypt(byte[] data, String key) throws CryptoException;

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key 解密密钥
     * @return
     */
    byte[] decrypt(byte[] data, String key) throws CryptoException;
}
