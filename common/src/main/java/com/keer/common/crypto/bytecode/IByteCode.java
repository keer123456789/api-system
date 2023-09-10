package com.keer.common.crypto.bytecode;

import com.keer.common.exception.ByteCodeException;

public interface IByteCode {

    /**
     * 编码
     *
     * @param data 需要编码的内容
     * @return
     */
    String encode(byte[] data) throws ByteCodeException;

    /**
     * 解码
     *
     * @param data 待解码内容
     * @return
     */
    byte[] decode(String data) throws ByteCodeException;
}
