package com.hjh.practice.item.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hjh.practice.item.dto.ItemSearchDto;
import com.hjh.practice.item.dto.MainItemDto;
import com.hjh.practice.item.entity.Item;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    // 메인 페이지에 보여줄 상품 리스트 조회
    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}

