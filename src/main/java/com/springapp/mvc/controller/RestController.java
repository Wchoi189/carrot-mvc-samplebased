package com.springapp.mvc.controller;

import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@RequestMapping("/board")
@org.springframework.web.bind.annotation.RestController
public class RestController {
    @Autowired
    private IBoardService boardService;

    @PostMapping("/insert")
    public String board_insert(final BoardDTO params){
        System.out.println("RestController : insert ");
       try {
           boolean isBoard = boardService.insertBoard(params);
            if (isBoard == false) {
                System.out.println("insert failed");
            }
            } catch(DataAccessException e){
               System.out.println("database access problems");
           } catch(Exception e) {
               System.out.println();
           }
        return "redirect:/board_list";
    }




    @RequestMapping("/list.json")
    public HashMap<String, Object> listJSON(){
        HashMap<String, Object> map=new HashMap<>();
        map.put("list", boardService.getBoardList());
        return map;
    }

}
