package com.springapp.mvc.board.service.impl;

import com.springapp.mvc.board.mapper.BoardFileMapper;
import com.springapp.mvc.board.model.BoardFileDTO;
import com.springapp.mvc.board.service.IBoardFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("file")
public class BoardFileService implements IBoardFileService {
    @Autowired
    BoardFileMapper boardFileMapper;


    @Override
    public List<BoardFileDTO> getFileBoardList() {
        List<BoardFileDTO> boardFileDTO = boardFileMapper.getBoardFileList();
        return boardFileDTO;
    }

    @Override
    public void insertBoardFile(BoardFileDTO boardFileDTO) {
        boardFileMapper.insertBoardFile(boardFileDTO);
    }



    @Override
    public void updateBoardFile(BoardFileDTO boardFileDTO) {
        boardFileMapper.updateBoardFile(boardFileDTO);
    }

    @Override
    public void deleteBoardFile(int f_no) {
        boardFileMapper.deleteBoardFileById(f_no);
    }

    @Override
    public BoardFileDTO readFileBoardById(int f_no) {
        BoardFileDTO boardFileDTO = boardFileMapper.getBoardFileById(f_no);
        return boardFileDTO;
    }
}

