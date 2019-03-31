package com.demo.hystrix.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.Random;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author: yanuo
 * @create: 20190227 4:36 PM
 */

@Controller
@ResponseBody
public class HystrixController {

  //请求熔断注解，当服务出现问题时候会执行fallbackMetho属性的名为helloFallBack的方法
  @HystrixCommand(fallbackMethod = "helloFallBack")
  @RequestMapping(value = "/hello")
  public String helloService() throws Exception, InterruptedException {
    if (new Random().nextBoolean() == true) {
      throw new Exception();
    }
    return "ok";
  }

  public String helloFallBack() {
    return "error";
  }

}
