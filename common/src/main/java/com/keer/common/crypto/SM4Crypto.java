package com.keer.common.crypto;

import com.keer.common.exception.CryptoException;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.security.Security;

/**
 * 商密4加密解密
 *
 * @author keer
 * @date 2023-04-23
 */
public class SM4Crypto implements ICrypto {

    private static final String KEY_SM4 = "SM4";
    private static final String CIPHER_ALGORITHM = "SM4/ECB/PKCS5Padding";

    @Override
    public byte[] encrypt(byte[] data, String key) throws CryptoException {
        try {
            // 加载bc模块
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            }
            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_SM4, BouncyCastleProvider.PROVIDER_NAME);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    @Override
    public byte[] decrypt(byte[] data, String key) throws CryptoException {
        try {
            // 加载bc模块
            if (Security.getProvider("BC") == null) {
                Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
            }
            KeyGenerator keyGen = KeyGenerator.getInstance(KEY_SM4, BouncyCastleProvider.PROVIDER_NAME);
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(key.getBytes("UTF-8"));
            keyGen.init(128, secureRandom);
            SecretKey secretKey = keyGen.generateKey();
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new CryptoException(e);
        }
    }

    public static void main(String[] args) throws CryptoException {
        SM4Crypto crypto = new SM4Crypto();
        String content = "测试公司A";
        System.out.println("加密前：" + content);
        byte[] encrypt = crypto.encrypt(content.getBytes(), "kssCaVkSl7gmp4vd8aMpvH3eXt0P03");
        String encodeStr = new String(Base64.encodeBase64(encrypt));
        System.out.println("加密后：" + encodeStr);
        byte[] decrypt = crypto.decrypt(Base64.decodeBase64(encodeStr), "kssCaVkSl7gmp4vd8aMpvH3eXt0P03");
        System.out.println("解密后：" + new String(decrypt));
    }
}
