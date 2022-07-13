package com.springapp.mvc.controller;


import com.springapp.mvc.config.BoardValidator;
import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.service.IBoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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
    @Autowired
    private BoardValidator boardValidator;
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
    @Valid
    @PostMapping("/insert")
    public String boardSubmit(@Valid @ModelAttribute("boardDTO") BoardDTO boardDTO, BindingResult result, SessionStatus status, Model model){
        //컨트롤러 실행 여부
        System.out.println("RestController : insert ");

        //유효성 체크
        boardValidator = new BoardValidator();
        boardValidator.validate(boardDTO, result);

        // Empty 이다면 리턴. 조건은 BoardValidator에서 정의
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            String title = boardDTO.getTitle();

            for(FieldError fieldError : fieldErrors){
                System.out.println("Field errors : " + fieldError);
            }
            return "fail";
        }

        try {
            //DB에 저장
            System.out.println(boardDTO);
            boardDTO.setReg_date(new Date());
            System.out.println(boardDTO);
            boardService.insertBoard(boardDTO);
            if (boardDTO == null) {
                System.out.println("insert failed");
            }
        } catch(DataAccessException e){
            System.out.println("database access problems");
        } catch(Exception e) {
            System.out.println("other");
        }
        return "redirect:/";
    }



}
