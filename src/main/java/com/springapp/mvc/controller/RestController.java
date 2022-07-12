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

    @Autowired
    private BoardValidator boardValidator;
    @Valid
    @PostMapping("/insert")
    public String boardSubmit(@Valid @ModelAttribute("boardDTO") BoardDTO boardDTO, BindingResult result, SessionStatus status, Model model){
        //컨트롤러 실행 여부
        System.out.println("RestController : insert ");
//asdf
        //유효성 체크
        boardValidator.validate(boardDTO, result);

        // Empty 이다면 리턴. 조건은 BoardValidator에서 정의
        if (result.hasErrors()) {
            return "boardDTO";
        }

        try {
            //DB에 저장
            boardService.insertBoard(boardDTO);
            if (boardDTO == null) {
                System.out.println("insert failed");
            }
            } catch(DataAccessException e){
               System.out.println("database access problems");
           } catch(Exception e) {
               System.out.println("other");
           }
        return "redirect:/board_list.html";
    }




    @RequestMapping("/list.json")
    public HashMap<String, Object> listJSON(){
        HashMap<String, Object> map=new HashMap<>();
        map.put("list", boardService.getBoardList());
        return map;
    }

}
