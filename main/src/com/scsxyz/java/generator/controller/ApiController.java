package com.scsxyz.java.generator.controller;

import com.scsxyz.java.generator.build.BuildClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by Bond(China) on 2017/9/7.
 */
@Controller
@RequestMapping("api/generator")
public class ApiController {

    @Resource(name = "commonBuildClient")
    private BuildClient commonBuildClient;

}
