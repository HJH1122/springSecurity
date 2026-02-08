package com.hjh.practice.item.dto;

import com.hjh.practice.item.entity.ItemImg;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemImgDto {

    private Long id;

    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;
    
    public static ItemImgDto entityToDto(ItemImg itemImg){
        ItemImgDto itemImgDto = ItemImgDto.builder()
            .id(itemImg.getId())
            .imgName(itemImg.getImgName())
            .oriImgName(itemImg.getOriImgName())
            .imgUrl(itemImg.getImgUrl())
            .repImgYn(itemImg.getRepImgYn())
            .build();

        return itemImgDto;
    }
}
