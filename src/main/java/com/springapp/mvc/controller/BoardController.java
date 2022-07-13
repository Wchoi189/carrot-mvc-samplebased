package com.springapp.mvc.controller;


import com.springapp.mvc.config.BoardValidator;
import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.model.BoardFileDTO;
import com.springapp.mvc.service.IBoardService;
import com.springapp.mvc.service.impl.BoardFileService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Controller
public class BoardController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private String fileDir = "/ckupload/";


    private IBoardService boardService;
    @Autowired
    private BoardValidator boardValidator;
    @Autowired
    private BoardFileService boardFileService;
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
    @PostMapping("/boardinsert")
    public String boardSubmit(@Valid @ModelAttribute("boardDTO") BoardDTO boardDTO, BindingResult result, @RequestParam(value="input_file_btn", required = false) MultipartFile files) throws IllegalStateException, IOException, Exception {
        //컨트롤러 실행 여부
        System.out.println("Board controller insert method running..");


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

//
//            if(files.isEmpty()) {
//                System.out.println("if file is empty...");
//                boardService.insertBoard(boardDTO);
//            } else {
//                System.out.println("else method running");
//                String fileName = files.getOriginalFilename(); // 사용자 컴에 저장된 파일명 그대로
//                //확장자
//                String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
//                File destinationFile; // DB에 저장할 파일 고유명
//                String destinationFileName;
//                //절대경로 설정 안해주면 지 맘대로 들어가버려서 절대경로 박아주었습니다.
//                String fileUrl = "업로드한 파일을 저장할 절대경로를 넣어주세요";
//
//                do { //우선 실행 후
//                    //고유명 생성
//                    destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
//                    destinationFile = new File(fileUrl + destinationFileName); //합쳐주기
//                } while (destinationFile.exists());
//
//                destinationFile.getParentFile().mkdirs(); //디렉토리
//                files.transferTo(destinationFile);
//
//                boardService.insertBoard(boardDTO);
//
//                BoardFileDTO file = new BoardFileDTO();
//                file.setBoard_id(boardDTO.getBoard_id());
//                file.setFile_name(destinationFileName);
//                file.setFileoriginname(fileName);
//                file.setFileurl(fileUrl);
//                boardFileService.insertBoardFile(file);
//            }

//
//            if( !files.isEmpty() ) {   //---파일이 없으면 true를 리턴. false일 경우에만 처리함.
//                String file_name = boardDTO.getFile_name();
//                File converFile = new File(fileDir, file_name);
//                files.transferTo(converFile);  //--- 저장할 경로를 설정 해당 경로는 각자 원하는 위치로 설정하면 됩니다. 다만, 해당 경로에 접근할 수 있는 권한이 없으면 에러 발생
////                boardDTO.setSave_path(fileDir + file_name);
//                System.out.println("files is not empty..");
//            }

                //DB에 저장
            System.out.println(boardDTO);
            String file_name = boardDTO.getFile_name();
            boardDTO.setSave_path(fileDir + file_name);
            boardDTO.setReg_date(new Date());
//            if( !files.isEmpty() ) {
//
//                File converFile = new File(fileDir, file_name);
//                System.out.println("files is not empty..");
//            }
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
