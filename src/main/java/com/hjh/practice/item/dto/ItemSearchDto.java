package com.hjh.practice.item.dto;

import com.hjh.practice.item.constant.ItemSellStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemSearchDto {

    private String searchDateType;              // 검색 기준일

    private ItemSellStatus searchSellStatus;    // 판매 상태

    private String searchBy;                    // 검색 조건

    private String searchQuery;                 // 검색어
}