package com.keer.common.crypto.bytecode;

public class ByteCodeFactory {
    public static IByteCode getCode(String name) {
        if (name == null) {
            return null;
        }
        switch (name) {
            case "HEX":
                return new HexByteCode();
            case "BASE64":
                return new Base64ByteCode();
            default:
                return null;
        }
    }
}
