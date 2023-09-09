package com.keer.jartest;

import com.keer.common.transfer.JarTest;

/**
 * @Author: 张经伦
 * @Date: 2023/9/4  17:26
 * @Description:
 */
public class JartestImpl implements JarTest {
    @Override
    public String test(String str) {
        return str+" hello world!!";
    }
}
