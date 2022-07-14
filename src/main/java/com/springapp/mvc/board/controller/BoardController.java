package com.springapp.mvc.board.controller;


import com.springapp.mvc.board.config.BoardValidator;
import com.springapp.mvc.board.model.BoardDTO;
import com.springapp.mvc.board.service.IBoardService;
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

    //폼으로 게시글 등록. action=/boardinsert method=post
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
            //유효성 체크 실패 시 등록 실패 페이지로 이동
            return "fail";
        }
        //저장 위치 = 포로젝트 경로에 임시 만들어진 경로(carrot-mvc-samplebased/target/)
        String path = servletContext.getRealPath(request.getContextPath());
        String filename = file.getOriginalFilename();

        //저장 경로 + 파일명 체크
        System.out.println("path"  + filename );
        String save_path = path + "/" + filename;

        //실제 파일일 시스템으로 저장
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

        //유효성 체크 : Title 문자 길이 < 16
        boardValidator = new BoardValidator();
        boardValidator.validate(boardDTO, result);

        //DB에 저장
        try {
            System.out.println(boardDTO);
            boardDTO.setSave_path(save_path);

            //현제 일자로 샌성
            boardDTO.setReg_date(new Date());
            System.out.println(boardDTO);

            //쿼리문 실행해서 데이터베이스로 저장
            boardService.insertBoard(boardDTO);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return "redirect:/";
    }

    //상세정보 페이지
    @GetMapping("read")
    public String readBoardById (Model model, int board_id) {
        BoardDTO boardread = boardService.getBoardById(board_id);
        model.addAttribute("boardread", boardread );
        return "board_read";
    }
}
