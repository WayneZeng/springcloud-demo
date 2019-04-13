package com.demo.search.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author: yanuo
 * @create: 20190413 2:52 PM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
// indexName：索引名称 可以理解为数据库名 必须为小写 不然会报org.elasticsearch.indices.InvalidIndexNameException异常
@Document(indexName = "item", type = "doc", shards = 1, replicas = 0)
public class Item implements Serializable {

  private Long id;

  private String title;

  private String category;

  private String brand;

  private Double price;

  private String images;
}
