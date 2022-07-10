package com.springapp.mvc.controller;


import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.service.IBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BoardController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());



private IBoardService boardDAO;
    public BoardController(IBoardService boardDAO) {
        this.boardDAO = boardDAO;
    }

    @GetMapping("/")
    public String boardHome (Model theModel1) {
        System.out.println("board_index.html");
        List<BoardDTO> boardList = boardDAO.getBoardList();
        theModel1.addAttribute("boardList", boardList);
        return "board_index";
    }

    @GetMapping("/boardList")
    public String boardList (Model theModel) {
        List<BoardDTO> boardList = boardDAO.getBoardList();
        theModel.addAttribute("boardList", boardList);
        System.out.println("list:" + boardList);
        return "board_list";
    }

    @RequestMapping( "/boardInsert")
    public String boardInsert(@RequestBody BoardDTO theBoard, Model theModel) {
//        boardDAO.insertBoard(theBoard);
//        System.out.println("/insert");
        return "board_insert";
    }
}
