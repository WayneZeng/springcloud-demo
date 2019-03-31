package eureka.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yanuo
 * @create: 20190320 11:43 PM
 */

public interface GreetingController {

  @RequestMapping("/greeting")
  String greeting();
}
