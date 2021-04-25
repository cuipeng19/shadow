package com.shadow.hhkb.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cuipeng 2021/4/20 14:56
 */
@RestController
@RequestMapping("/hhkb")
public class HhkbController {

    @GetMapping("/test")
    public String test() {
        System.out.println("hhkb");
        return "hhkb";
    }

}
