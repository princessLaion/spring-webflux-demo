package com.lrp.spring_webflux_demo.controller;

import com.lrp.spring_webflux_demo.model.Item;
import com.lrp.spring_webflux_demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public Flux<Item> getAllItems() {
        return itemService.getAllItems();
    }

    @GetMapping("/{id}")
    public Mono<Item> getItemById(@PathVariable String id) {
        return itemService.getItemById(id);
    }

    @GetMapping("/hello")
    public Mono<String> getHello() {
        return Mono.just("Hello, WebFlux!");
    }

    @PostMapping
    public Mono<Item> createItem(@RequestBody Item item) {
        return itemService.createItem(item);
    }

    @DeleteMapping("/{id}")
    public Mono<Item> deleteItemById(@PathVariable String id) {
        return itemService.deleteItemById(id);
    }
}
