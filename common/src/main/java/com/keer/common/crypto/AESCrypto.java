package com.keer.common.crypto;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * AES加密解密
 *
 * @author Bob
 * @date 2022-06-07
 */
public class AESCrypto implements ICrypto {

    private static final String KEY_AES = "AES";

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    @Override
    public byte[] encrypt(byte[] data, String key) throws CryptoException {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    @Override
    public byte[] decrypt(byte[] data, String key) throws CryptoException {
        return doAES(data, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 加解密
     *
     * @param data 待处理数据
     * @param key  密钥
     * @param mode 加解密mode
     * @return
     */
    private byte[] doAES(byte[] data, String key, int mode) throws CryptoException {
        try {
            //1.构造密钥生成器，指定为AES算法,不区分大小写
            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_AES);
            //2.根据ecnodeRules规则初始化密钥生成器
            //生成一个128位的随机源,根据传入的字节数组
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(128, secureRandom);
            //3.产生原始对称密钥
            SecretKey secretKey = keyGen.generateKey();
            //4.获得原始对称密钥的字节数组
            byte[] enCodeFormat = secretKey.getEncoded();
            //5.根据字节数组生成AES密钥
            SecretKeySpec keySpec = new SecretKeySpec(enCodeFormat, KEY_AES);
            //6.根据指定算法AES自成密码器
            Cipher cipher = Cipher.getInstance(KEY_AES);// 创建密码器
            //7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密解密(Decrypt_mode)操作，第二个参数为使用的KEY
            cipher.init(mode, keySpec);// 初始化
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    public static void main(String[] args) throws CryptoException, DecoderException {
        AESCrypto crypto = new AESCrypto();
        String content = "测试企业JR";
        System.out.println("加密前：" + content);
        byte[] encrypt = crypto.encrypt(content.getBytes(), "tZdZHTUqijsS0CYYuL2Tz69iEk2wqR4W");
        String encodeStr = new String(Base64.encodeBase64(encrypt));
        System.out.println("加密后：" + encodeStr);
        byte[] decrypt = crypto.decrypt(Base64.decodeBase64("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"), "tZdZHTUqijsS0CYYuL2Tz69iEk2wqR4W");
        System.out.println("解密后：" + new String(decrypt));
    }
}