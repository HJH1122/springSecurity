package com.hjh.practice.item.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hjh.practice.item.dto.ItemFormDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/item")
@Slf4j
public class ItemController {
    
    @GetMapping("/new")
    public String itemForm(Model model) {

        model.addAttribute("itemFormDto", new ItemFormDto());

        return "item/itemForm";
    }
    

}
