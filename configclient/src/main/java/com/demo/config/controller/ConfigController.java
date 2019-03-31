package com.demo.config.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: yanuo
 * @create: 20190227 4:36 PM
 */

@Controller
@ResponseBody
public class ConfigController {

  @Value("${id}")
  private String id;

  @RequestMapping(value = "/hello")
  public String helloService() throws Exception, InterruptedException {
    return "ok" + id;
  }

}
