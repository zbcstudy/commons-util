package com.wondertek.core.util.excel.math;

import org.junit.Test;

/**
 * @Author zbc
 * @Date 12:21-2019/3/31
 */
public class MathTest {

    /**
     * int类型的最大值是2147483647
     */
    @Test
    public void IntOutMemTest() {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Math.abs(-2147483648));

        System.out.println("---------------------");
        System.out.println(Double.POSITIVE_INFINITY);

        System.out.println(Double.MAX_VALUE);
    }
}
