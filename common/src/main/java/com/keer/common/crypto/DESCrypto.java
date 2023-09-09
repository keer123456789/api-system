package com.keer.common.crypto;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * DES加密介绍 DES是一种对称加密算法，所谓对称加密算法即：加密和解密使用相同密钥的算法。
 *
 * @author Bob
 * @date 2022-06-07
 */
public class DESCrypto implements ICrypto {

    /**
     * 加密
     *
     * @param data byte[]
     * @param key  String
     * @return byte[]
     */
    public byte[] encrypt(byte[] data, String key) throws CryptoException {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(56, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            // Cipher对象实际完成加密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象,ENCRYPT_MODE用于将 Cipher 初始化为加密模式的常量
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            // 现在，获取数据并加密
            // 正式执行加密操作
            return cipher.doFinal(data); // 按单部分操作加密或解密数据，或者结束一个多部分操作
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 解密
     *
     * @param data byte[]
     * @param key  String
     * @return byte[]
     * @throws Exception
     */
    public byte[] decrypt(byte[] data, String key) throws CryptoException {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            kgen.init(56, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            // Cipher对象实际完成解密操作
            Cipher cipher = Cipher.getInstance("DES");
            // 用密匙初始化Cipher对象
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            // 真正开始解密操作
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        DESCrypto crypto = new DESCrypto();
        String content = "测试公司A";
        System.out.println("加密前：" + content);
        byte[] encrypt = crypto.encrypt(content.getBytes(), "kssCaVkSl7gmp4vd8aMpvH3eXt0P03");
        String encodeStr = new String(Base64.encodeBase64(encrypt));
        System.out.println("加密后：" + encodeStr);
        byte[] decrypt = crypto.decrypt(Base64.decodeBase64(encodeStr), "kssCaVkSl7gmp4vd8aMpvH3eXt0P03");
        System.out.println("解密后：" + new String(decrypt));
    }
}

