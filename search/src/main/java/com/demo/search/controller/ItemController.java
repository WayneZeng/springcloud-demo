package com.demo.search.controller;

import com.demo.search.entity.Item;
import com.demo.search.repository.ItemRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yanuo
 * @create: 20190413 2:56 PM
 */
@RestController
public class ItemController {

  @Autowired
  private ItemRepository itemRepository;

  //http://localhost:10012/save
  @GetMapping("save")
  public String save() {
    Item item = new Item();
    item.setId(1L);
    item.setTitle("测试商品");

    itemRepository.save(item);
    return "success";
  }

  //http://localhost:10012/get?id=1
  @GetMapping("get")
  public Optional<Item> getById(long id) {
    Optional<Item> items = itemRepository.findById(id);
    return items;
  }

}
