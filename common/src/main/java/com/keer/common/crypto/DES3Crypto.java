package com.keer.common.crypto;

import com.keer.common.exception.CryptoException;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;

/**
 * 3DES 加密/解密
 *
 * @author keer
 * @date 2022-06-07
 */
public class DES3Crypto implements ICrypto {
    /**
     * 加密算法
     */
    private static final String KEY_ALGORITHM = "DESede";
    private static final String CIPHER_ALGORITHM = "DESede/ECB/PKCS5Padding";

    /**
     * 3DES 加密
     *
     * @param data 需要加密的字符串
     * @param key  秘钥
     * @return 返回加密后的结果
     */
    public byte[] encrypt(byte[] data, String key) throws CryptoException {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(168, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    /**
     * 3DES 解密
     *
     * @param data 需要解密的密文
     * @param key  秘钥
     * @return 返回解密后的结果
     */
    public byte[] decrypt(byte[] data, String key) throws CryptoException {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(168, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    public static void main(String[] args) throws CryptoException {
        DES3Crypto crypto = new DES3Crypto();
        String content = "测试公司A";
        System.out.println("加密前：" + content);
        byte[] encrypt = crypto.encrypt(content.getBytes(), "kssCaVkSl7gmp4vd8aMpvH3eXt0P03");
        String encodeStr = new String(Base64.encodeBase64(encrypt));
        System.out.println("加密后：" + encodeStr);
        byte[] decrypt = crypto.decrypt(Base64.decodeBase64(encodeStr), "kssCaVkSl7gmp4vd8aMpvH3eXt0P03");
        System.out.println("解密后：" + new String(decrypt));
    }
}
