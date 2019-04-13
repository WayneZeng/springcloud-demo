package com.demo.search.repository;

import com.demo.search.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: yanuo
 * @create: 20190413 3:16 PM
 */

@Repository
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

}
