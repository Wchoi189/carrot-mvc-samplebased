package com.springapp.mvc.board.controller;


import com.springapp.mvc.board.config.BoardValidator;
import com.springapp.mvc.board.model.BoardDTO;
import com.springapp.mvc.board.service.IBoardService;
import com.springapp.mvc.board.service.impl.BoardFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IBoardService boardService;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private BoardFileService boardFileService;

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/")
    public ModelAndView boardList( Model model) {
        ModelAndView mav = new ModelAndView("board_list");
        List<BoardDTO> boardList = boardService.getBoardList();
        mav.addObject("boardList", boardList);
        mav.addObject("localDate", LocalDate.now());
        return mav;
    }

    @GetMapping("/boardinsert")
    public ModelAndView board_insert() {
        ModelAndView mav = new ModelAndView("board_insert");
        mav.addObject("boardDTO", new BoardDTO());
        System.out.println("/insert");
        return mav;
    }

    @Valid
    @PostMapping("/boardinsert")
    public String boardSubmit(@RequestParam CommonsMultipartFile file, HttpSession session, @Valid @ModelAttribute("boardDTO") BoardDTO boardDTO, BindingResult result, HttpServletRequest request) {
        //컨트롤러 실행 여부
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        System.out.println("Board controller insert method running..");
        // Empty 이다면 리턴. 조건은 BoardValidator에서 정의
        if (result.hasErrors()) {
            List<FieldError> fieldErrors = result.getFieldErrors();
            String title = boardDTO.getTitle();
            for (FieldError fieldError : fieldErrors) {
                System.out.println("Field errors : " + fieldError);
            }
            return "fail";
        }
        //RealPath
        String path = servletContext.getRealPath(request.getContextPath());
        String filename = file.getOriginalFilename();
        System.out.println("path"  + filename );
        String save_path = path + "/" + filename;


        try {
            byte barr[] = file.getBytes();
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
            Object o = bout;
            bout.write(barr);
            bout.flush();
            bout.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //유효성 체크
        boardValidator = new BoardValidator();
        boardValidator.validate(boardDTO, result);


        try {

            //DB에 저장
            System.out.println(boardDTO);
            boardDTO.setSave_path(save_path);
            boardDTO.setReg_date(new Date());
            System.out.println(boardDTO);
            boardService.insertBoard(boardDTO);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }

    @GetMapping("read")
    public String readBoardById (Model model, int board_id) {
        BoardDTO boardread = boardService.getBoardById(board_id);
        model.addAttribute("boardread", boardread );
        return "board_read";
    }
}
