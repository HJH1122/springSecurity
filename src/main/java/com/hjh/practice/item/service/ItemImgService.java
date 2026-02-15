package com.hjh.practice.item.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import com.hjh.practice.item.entity.ItemImg;
import com.hjh.practice.item.repository.ItemImgRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;
    
    private final ItemImgRepository itemImgRepository;
    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile itemImgFile) throws IOException {
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws IOException {

        if(!itemImgFile.isEmpty()){
            ItemImg itemImg = itemImgRepository.findById(itemImgId).orElseThrow(EntityNotFoundException::new);

            if(!StringUtils.isEmpty(itemImg.getImgName())){
                fileService.deleteFile(itemImgLocation + "/" + itemImg.getImgName());
            }
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = "";
            String imgUrl = "";

            if(!StringUtils.isEmpty(oriImgName)){
                imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
                imgUrl = "/images/item/" + imgName;
            }
            itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }

    }
}
