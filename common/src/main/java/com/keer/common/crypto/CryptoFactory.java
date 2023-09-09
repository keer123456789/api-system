package com.keer.common.crypto;

/**
 * @author Bob
 * @date 2022-06-08
 */
public abstract class CryptoFactory {

    public static ICrypto getCrypto(String name) {
        if(name == null) {
            return null;
        }
        switch (name) {
            case "AES":
                return new AESCrypto();
            case "DES":
                return new DESCrypto();
            case "3DES":
                return new DES3Crypto();
            case "SM4":
                return new SM4Crypto();
            default:
                return null;
        }
    }
}
