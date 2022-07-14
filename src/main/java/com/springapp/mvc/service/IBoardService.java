package com.springapp.mvc.service;


import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.model.BoardFileDTO;

import java.util.List;


public interface IBoardService {
List<BoardDTO> getBoardList();
void insertBoard(BoardDTO theBoard);
BoardDTO getBoardById(Integer board_id);
void updateBoard(BoardDTO theBoard);
void deleteBoardById(Integer board_id);

}
