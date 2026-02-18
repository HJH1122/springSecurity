package com.hjh.practice.item.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.hjh.practice.item.dto.ItemFormDto;
import com.hjh.practice.item.dto.ItemSearchDto;
import com.hjh.practice.item.entity.Item;
import com.hjh.practice.item.service.ItemService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/item")
@Slf4j
public class ItemController {

    private final ItemService itemService;
    
    @GetMapping("/new")
    public String itemForm(Model model) {

        model.addAttribute("itemFormDto", new ItemFormDto());

        return "item/itemForm";
    }
    
    @PostMapping("/new")
    public String itemNew(@Valid ItemFormDto itemFormDto, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()){
            return "item/itemForm";
        }
        if(itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품이미지는 필수입니다.");
            return "item/itemForm";
        }
        try {
            itemService.saveItem(itemFormDto, itemImgFileList);
        } catch (IOException e) {
            model.addAttribute("errorMessage", "상품 등록중 오류가 발생했습니다.");
            return "item/itemForm";
        }
        return "redirect:/";
    }

    @GetMapping("/{itemId}")
    public String itemDetail(@PathVariable("itemId") Long itemId, Model model) {

        try{
            ItemFormDto itemFormDto = itemService.getItemDetail(itemId);
            model.addAttribute("itemFormDto", itemFormDto);

            return "item/itemForm";
        } catch(EntityNotFoundException e) {
            model.addAttribute("errorMessage", "존재하지 않는 상품입니다.");
            
            return "item/itemForm";
        }
    }

    @GetMapping({"/items", "/items/{page}"})
    public String itemList(ItemSearchDto itemSearchDto, Model model, @PathVariable(value = "page")Optional<Integer> page){
        // 페이지 정보 (0번째 페이지, 10개씩, id 내림차순)
        Pageable pageable = PageRequest.of(page.isPresent()? page.get(): 0, 5, Sort.by("id").descending());
        Page<Item> items = itemService.getAdminItemPage(itemSearchDto, pageable);

        model.addAttribute("items", items);
        model.addAttribute("itemSearchDto", itemSearchDto);
        model.addAttribute("maxPage", 5);

        return "item/itemList";
    }

}
