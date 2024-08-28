package com.lrp.spring_webflux_demo.service;

import com.lrp.spring_webflux_demo.model.Item;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private static List<Item> itemList = new ArrayList<>();

    static {
        itemList.add(new Item("1", "Item One", 19.99));
        itemList.add(new Item("2", "Item Two", 29.99));
        itemList.add(new Item("3", "Item Three", 39.99));
    }

    public Flux<Item> getAllItems() {
        return Flux.fromIterable(itemList);
    }

    public Mono<Item> getItemById(String id) {
        return Mono.justOrEmpty(itemList.stream()
                .filter(item -> item.getId().equals(id))
                .findFirst());
    }

    public Mono<Item> createItem(Item item) {
        itemList.add(item);
        // In a real scenario, you'd save the item to a database
        // Here, we're just returning the item for simplicity
        return Mono.just(item);
    }

    public Mono<Item> deleteItemById(String id) {
        return getItemById(id)
                .flatMap(item -> {
                    if (itemList.remove(item)) {
                        return Mono.just(item);
                    } else {
                        return Mono.error(new IllegalArgumentException("Item not found"));
                    }
                });

    }
}

