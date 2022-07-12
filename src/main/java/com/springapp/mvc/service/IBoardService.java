package com.springapp.mvc.service;


import com.springapp.mvc.model.BoardDTO;
import com.springapp.mvc.model.BoardFileDTO;

import java.util.List;


public interface IBoardService {
List<BoardDTO> getBoardList();
void insertBoard(BoardDTO theBoard);
BoardDTO getBoardById(int board_id);
void updateBoard(BoardDTO theBoard);
void deleteBoardById(int board_id);

}
