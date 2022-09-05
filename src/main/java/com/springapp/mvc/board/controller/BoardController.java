package com.springapp.mvc.board.controller;


import com.springapp.mvc.board.config.BoardValidator;
import com.springapp.mvc.board.model.BoardDTO;
import com.springapp.mvc.board.dao.BoardDao;
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
    private BoardDao boardService;

    @Autowired
    private ServletContext servletContext;

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


    @PostMapping("/boardinsert")
    public String boardSubmit(@RequestParam CommonsMultipartFile file, @ModelAttribute("boardDTO") BoardDTO boardDTO, HttpServletRequest request) {
        //Number generator
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-ss");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy.MM.dd");

        String unique_id = String.format("%s", sdf.format( new Date() ));
        String date = sdf2.format(new Date());

        logger.debug("date===" + date);

        //RealPath
        String path = servletContext.getRealPath(request.getContextPath());
        String filename =  unique_id + file.getOriginalFilename();

        //Unique Identifier for filenames
        String save_path = path + filename;
        System.out.println(save_path);

        try {
            //DB에 저장
            System.out.println(boardDTO);
            boardDTO.setFile_name(filename);
            boardDTO.setSave_path(save_path);
            boardDTO.setReg_date(date);
            System.out.println(boardDTO);

            byte barr[] = file.getBytes();
            BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
            Object o = bout;
            bout.write(barr);
            bout.flush();
            bout.close();
            boardService.insertBoard(boardDTO);

        } catch (Exception e) {

            e.printStackTrace();
        }finally {

            return "redirect:/";
        }

    }

    @GetMapping("read")
    public String readBoardById (Model model, int board_id) {
        BoardDTO boardread = boardService.getBoardById(board_id);
        model.addAttribute("boardread", boardread );
        return "board_read";
    }
}
