package com.hjh.practice.item.service;

import org.springframework.stereotype.Service;

import com.hjh.practice.item.repository.ItemImgRepository;
import com.hjh.practice.item.repository.ItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemService {
    
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;
}
