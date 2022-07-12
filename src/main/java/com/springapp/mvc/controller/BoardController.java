package com.springapp.mvc.controller;


import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.service.IBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Controller
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(getClass());



private IBoardService boardService;
    public BoardController(IBoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping("/")
    public ModelAndView boardList (Model model) {
        ModelAndView mav = new ModelAndView("board_list");
        List<BoardDTO> boardList = boardService.getBoardList();
        mav.addObject("boardList", boardList);
        return mav;
    }

    @GetMapping( "/boardinsert")
    public ModelAndView board_insert() {
        ModelAndView mav = new ModelAndView("board_insert");
        mav.addObject("boardDTO", new BoardDTO());
        System.out.println("/insert");
        return mav;
    }

}
