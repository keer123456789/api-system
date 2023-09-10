package com.keer.common.crypto.bytecode;

import org.apache.commons.codec.binary.Base64;

public class Base64ByteCode implements IByteCode {
    @Override
    public String encode(byte[] data) {
        return new String(Base64.encodeBase64(data));
    }

    @Override
    public byte[] decode(String data) {
        return Base64.decodeBase64(data);
    }
}
