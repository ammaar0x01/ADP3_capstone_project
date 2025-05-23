package com.college.controller;

import com.college.service.HousekeeperService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {


    @GetMapping(path={"/", "/root"})
//    @GetMapping("/")
    public String root(){
        return "<i>Hola mi amigos</i>" +
                "<br /><br />" +
                "<a href='/temp'>Temp</a><br />" +
                "<a href='/admin'>Admin</a><br />";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin page";
    }

    @GetMapping("/temp")
    public String temp(){
        return "<a href='https://google.com' target='_blank'>Google</a><br />" +
                "<a href='/'>Root</a><br />";
    }


}
