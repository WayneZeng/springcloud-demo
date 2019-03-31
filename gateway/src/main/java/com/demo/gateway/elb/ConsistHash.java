package com.demo.gateway.elb;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: yanuo
 * @create: 20190226 5:44 PM
 */

public class ConsistHash {

  /**
   * 虚拟节点数目
   */
  private static final int VIRTUAL_NODES = 150;
  /**
   * 真实结点列表
   */
  private static List<String> realNodes = new LinkedList<>();

  /**
   * 虚拟节点，key表示虚拟节点的hash值，value表示虚拟节点的名称
   */
  private static SortedMap<Integer, String> virtualNodes =
      new TreeMap<>();
  private String[] servers;

  public ConsistHash(String[] servers) {
    this.servers = servers;
  }

  /**
   * 使用FNV1_32_HASH算法计算服务器的Hash值,这里不使用重写hashCode的方法，最终效果没区别
   */
  private static int getHash(String str) {
    final int p = 16777619;
    int hash = (int) 2166136261L;
    for (int i = 0; i < str.length(); i++) {
      hash = (hash ^ str.charAt(i)) * p;
    }
    hash += hash << 13;
    hash ^= hash >> 7;
    hash += hash << 3;
    hash ^= hash >> 17;
    hash += hash << 5;

    if (hash < 0) {
      hash = Math.abs(hash);
    }
    return hash;
  }

  /**
   * 得到应当路由到的结点
   */
  public static String getServer(String node) {
    // 得到带路由的结点的Hash值
    int hash = getHash(node);
    // 得到大于该Hash值的所有Map
    SortedMap<Integer, String> subMap =
        virtualNodes.tailMap(hash);
    // 第一个Key就是顺时针过去离node最近的那个结点
    Integer i = subMap.firstKey();
    // 返回对应的虚拟节点名称，这里字符串稍微截取一下
    String virtualNode = subMap.get(i);
    return virtualNode.substring(0, virtualNode.indexOf("&&"));
  }

  private void getNode() {
    // 原始的服务器添加到真实结点列表中
    Collections.addAll(realNodes, servers);

    // 添加虚拟节点
    for (String str : realNodes) {
      for (int i = 0; i < VIRTUAL_NODES; i++) {
        String virtualNodeName = str + "&&VN" + String.valueOf(i);
        int hash = getHash(virtualNodeName);
        virtualNodes.put(hash, virtualNodeName);
      }
    }
  }
}

