package com.scsxyz.java.generator.exception;

/**
 * Created by Bond(China) on 2017/11/4.
 */
public class ServiceException extends Exception {
    public ServiceException(String msg, Exception e) {
        super(msg, e);
    }
}
