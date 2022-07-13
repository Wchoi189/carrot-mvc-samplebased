package com.springapp.mvc.controller;

import com.springapp.mvc.config.BoardValidator;
import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
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
