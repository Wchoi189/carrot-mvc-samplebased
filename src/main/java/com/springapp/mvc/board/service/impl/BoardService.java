package com.springapp.mvc.board.service.impl;



import com.springapp.mvc.board.mapper.BoardMapperJava;
import com.springapp.mvc.board.model.BoardDTO;
import com.springapp.mvc.board.service.IBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletConfigAware;

import javax.servlet.ServletConfig;
import java.util.List;
@Service("boardService")
//@Repository
public class BoardService implements IBoardService, ServletConfigAware {

    @Autowired
    BoardMapperJava mapper;

    @Override
    public List<BoardDTO> getBoardList() {
        List<BoardDTO> theBoard = mapper.getBoardList();
        return theBoard;
    }

    @Override
    public BoardDTO getBoardById(Integer board_id) {
        BoardDTO boardDTO = mapper.getBoardById(board_id);
        return boardDTO;
    }

    @Override
    public void deleteBoardById(Integer board_id) {
        mapper.deleteBoardById(board_id);
    }

    @Override
    public void updateBoard(BoardDTO theBoard) {
        mapper.updateBoard(theBoard);
    }

    @Override
    public void insertBoard(BoardDTO theBoard) {
        mapper.insertBoard(theBoard);

    }

    @Override
    public void setServletConfig(ServletConfig servletConfig) {

    }
}