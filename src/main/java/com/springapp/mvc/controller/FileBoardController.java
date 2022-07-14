package com.springapp.mvc.controller;

import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.model.BoardFileDTO;
import com.springapp.mvc.service.IBoardFileService;
import com.springapp.mvc.service.IBoardService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

@RequestMapping("/fileBoard")
@Controller
public class FileBoardController {

    @Autowired
    IBoardFileService boardFileService;
    @Autowired
    IBoardService boardService;
//    @RequestMapping("/insertProc")
//    private String fileBoardInsertProc(@ModelAttribute BoardFileDTO boardFileDTO, @RequestPart MultipartFile
//            files, HttpServletRequest request) throws IllegalStateException, IOException, Exception {
//
//        if(files.isEmpty()) {
//            boardFileService.insertBoardFile(boardFileDTO);
//        } else {
//            String fileName = files.getOriginalFilename(); // 사용자 컴에 저장된 파일명 그대로
//            //확장자
//            String fileNameExtension = FilenameUtils.getExtension(fileName).toLowerCase();
//            File destinationFile; // DB에 저장할 파일 고유명
//            String destinationFileName;
//            //절대경로 설정 안해주면 지 맘대로 들어가버려서 절대경로 박아주었습니다.
//            String fileUrl = "file:///upload";
//            //절대 경로 설정 해줄것
//
//
//            do { //우선 실행 후
//                //고유명 생성
//                destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + fileNameExtension;
//                destinationFile = new File(fileUrl + destinationFileName); //합쳐주기
//            } while (destinationFile.exists());
//
//            destinationFile.getParentFile().mkdirs(); //디렉토리
//            files.transferTo(destinationFile);
//
//            boardFileService.insertBoardFile(boardFileDTO);
//
//            BoardFileDTO file = new BoardFileDTO();
//            file.setBoard_id(boardFileDTO.getBoard_id());
//            file.setFile_name(destinationFileName);
//            file.setFileoriginname(fileName);
//            file.setFileurl(fileUrl);
//            boardFileService.insertBoardFile(boardFileDTO);
//        }
//
//        return "forward:/board_list"; //객체 재사용
//    }

//    @GetMapping("/download/{fileId}")
//    public ResponseEntity<Resource> fileDownload(@PathVariable("fileId") Long fileId) throws IOException {
//        BoardDTO boardDTO= boardService.getBoardById(fileId);
//        Path path = Paths.get(fileDto.getFilePath());
//        Resource resource = new InputStreamResource(Files.newInputStream(path));
//        return ResponseEntity.ok()
//                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDto.getOrigFilename() + "\"")
//                .body(resource);
//    }

//    @Resource(name = "uploadPath")
//    String path;
//
//    // 이미지파일 브라우저에 출력
//    @RequestMapping("/display")
//    @ResponseBody
//    public ResponseEntity<byte[]> display(String fileName) throws Exception {
//        ResponseEntity<byte[]> result = null;
//        // display fileName
//        if (!fileName.equals("")) {
//            System.out.println("......" + path);
//            File file = new File(path + fileName);
//            HttpHeaders header = new HttpHeaders();
//            header.add("Content-Type", Files.probeContentType(file.toPath()));
//            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
//        }
//        return result;
//    }
//
//    //ckEditor file upload
//    @RequestMapping(value="/ckupload", method= RequestMethod.POST)
//    @ResponseBody
//    public HashMap<String,Object> updatePost(MultipartHttpServletRequest multi) throws Exception{
//        HashMap<String, Object> map=new HashMap<>();
//        MultipartFile file=multi.getFile("upload");
//        if(!file.isEmpty()) {
//            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
//            file.transferTo(new File(path + "ckupload/" + fileName));
//            String fileUrl = "/display?fileName=ckupload/" + fileName;
//
//            map.put("uploaded", 1);
//            map.put("fileName", fileName);
//            map.put("url", fileUrl);
//        }
//        return map;
//    }
}
