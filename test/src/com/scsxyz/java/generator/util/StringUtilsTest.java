package com.scsxyz.java.generator.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public class StringUtilsTest {

    @Test
    public void transformCamel() throws Exception {

        Assert.assertEquals("id", StringUtils.transformCamel("id"));
        Assert.assertEquals("idName", StringUtils.transformCamel("id_name"));
        Assert.assertEquals("goodsName", StringUtils.transformCamel("goods-name"));
        Assert.assertEquals("goodsName", StringUtils.transformCamel("GoodsName"));

    }

}