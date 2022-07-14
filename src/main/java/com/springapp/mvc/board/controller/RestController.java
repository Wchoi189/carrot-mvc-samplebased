package com.springapp.mvc.board.controller;

import com.springapp.mvc.board.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequestMapping("/board")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private IBoardService boardService;



    @RequestMapping("/list.json")
    public HashMap<String, Object> listJSON(){
        HashMap<String, Object> map=new HashMap<>();
        map.put("list", boardService.getBoardList());
        return map;
    }

}
