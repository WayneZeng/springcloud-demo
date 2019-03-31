package com.demo.gateway.elb;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: yanuo
 * @create: 20190225 8:33 PM
 */

public class Elb {

  private static Integer serverIndex = 0;
  List<String> serverList = new ArrayList<>();

  // 加锁，保证线程安全
  public String robin() {
    synchronized (serverIndex) {
      if (serverIndex >= serverList.size()) {
        serverIndex = -1;
      }
      return serverList.get(++serverIndex);
    }
  }

  public String hash(String remoteIp) {
    Integer hashCode = remoteIp.hashCode();
    serverIndex = hashCode % serverList.size();
    return serverList.get(serverIndex);
  }

  public String random() {
    int randomIndex = new Random().nextInt(serverList.size());
    return serverList.get(randomIndex);
  }


}
