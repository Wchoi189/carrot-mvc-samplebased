package com.springapp.mvc.service;

import com.springapp.mvc.model.BoardFileDTO;

import java.util.List;

public interface IBoardFileService {
    List<BoardFileDTO> getFileBoardList();
    void insertBoardFile(BoardFileDTO boardFileDTO);
    void updateBoardFile(BoardFileDTO boardFileDTO);
    void deleteBoardFile(int f_no);
    BoardFileDTO readFileBoardById(int f_no);
}
