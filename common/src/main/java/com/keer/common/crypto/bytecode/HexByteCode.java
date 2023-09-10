package com.keer.common.crypto.bytecode;

import com.keer.common.exception.ByteCodeException;
import org.apache.commons.codec.binary.Hex;

public class HexByteCode implements IByteCode {
    @Override
    public String encode(byte[] data) {
        return new String(Hex.encodeHex(data));
    }

    @Override
    public byte[] decode(String data) throws ByteCodeException {
        try {
            return Hex.decodeHex(data);
        } catch (Exception e) {
            throw new ByteCodeException(e);
        }
    }
}
