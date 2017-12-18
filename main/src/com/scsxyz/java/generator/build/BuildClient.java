package com.scsxyz.java.generator.build;

import com.scsxyz.java.generator.config.Config;
import com.scsxyz.java.generator.exception.ServiceException;

/**
 * Created by Bond(China) on 2017/9/7.
 */
public interface BuildClient {

    void build(Config config) throws ServiceException;
}
